package checker;

import tags.ToCheck;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Formatter;
import tags.CheckModifier;
import tags.GetterToCheck;
import tags.SetterToCheck;
import tags.ToCompare;
import tests.A;

/**
 *
 * @author yvan
 */
@ToCheck("Class")
public class Checker {

    @ToCheck("Attribut")
    private StringBuilder SB;
    private Formatter F;
    private final Class<?> C;

    @ToCheck("Constructeur")
    public Checker(Class<?> c) {
        SB = new StringBuilder();
        F = new Formatter(SB);
        C = c;
        check();
    }

    @ToCheck("Méthode")
    private void f(String format, Object... o) {
        F.format(format, o);
    }

    public final void check() {
        SB = new StringBuilder();
        F = new Formatter(SB);
        ToCheck annotation = C.getAnnotation(ToCheck.class);
        if (annotation != null) {
            checkClass(annotation);
        }
        // --- Attributs 
        checkFields();
        // --- Constructeurs 
        checkConstructors();
        // --- Méthodes 
        checkMethods();
        // --- Setters 
        checkSetters();
        // --- Getters 
        checkGetters();
        // --- Méthodes 
        testMethods();
    }

    public void checkClass(ToCheck toCheck) {
        f("\n@Test\n");
        f("public void p%06d000_checkClass%s() {\n", toCheck.priority(), C.getSimpleName());
        f("   System.out.println(\"check class %s\");\n", C.getSimpleName());
        f("   assertTrue(\"%s\", %s.class.getSuperclass().equals(%s.class));\n",
                toCheck.value() + " (Héritage)",
                C.getSimpleName(),
                C.getSuperclass().getSimpleName());
        f("   Class<?> x = %s.class;\n", C.getSimpleName());
        checkModifiers(toCheck.value(), C.getModifiers(), toCheck.modifiers());
        /*f("   assertTrue(\"%s\", %s.class.getModifiers() == %s);\n",
                toCheck.value() + " (Modificateurs)",
                C.getSimpleName(),
                C.getModifiers()
        );*/

        f("}\n");
    }

    private void checkConstructor(Constructor<?> c, ToCheck toCheck) {
        String msg = toCheck.value().isEmpty() ? "Constructeur de " + c.getName() : toCheck.value();
        f("\n@Test\n");
        StringBuilder sb = Arrays.stream(c.getParameterTypes())
                .map(x -> x.getSimpleName().replaceAll("\\[\\]", "Array"))
                .collect(
                        StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append);
        f("public void p%06d000_checkConstructor%s%s() {\n", toCheck.priority(), C.getSimpleName(), sb.toString());
        f("   System.out.println(\"Présence %s\");\n", msg);
        f("   try {\n");
        f("        Constructor x = %s.class.getDeclaredConstructor(\n", C.getSimpleName());
        if (c.getParameterCount() > 0) {
            Class<?>[] tparam = c.getParameterTypes();
            f("           %s.class", tparam[0].getSimpleName());
            for (int i = 1; i < tparam.length; ++i) {
                f(",\n           %s.class", tparam[i].getSimpleName());
            }
        }
        f("\n        );\n");
        //f("        assertTrue(\"\", c.getModifiers() == %d);\n", c.getModifiers());
        checkModifiers(toCheck.value(), c.getModifiers(), toCheck.modifiers());

        f("   } catch (NoSuchMethodException | SecurityException ex) {\n");
        f("        fail(\"%s\");\n", msg);
        f("   }\n");
        f("}\n");

        ToCompare toCompare = c.getAnnotation(ToCompare.class);
        if (toCompare != null) {
            compareConstructor(c, toCheck, toCompare);
        }
    }

    public void checkConstructors() {
        SB.append('\n');
        Arrays.stream(C.getDeclaredConstructors())
                .filter(c -> c.getAnnotation(ToCheck.class) != null)
                .forEach((c) -> {
                    checkConstructor(c, c.getAnnotation(ToCheck.class));
                });
    }

    private void compareConstructor(Constructor<?> c, ToCheck toCheck, ToCompare toCompare) {
        String msg = toCheck.value().isEmpty() ? "Constructeur de " + c.getName() : toCheck.value();
        f("\n@Test\n");
        StringBuilder sb = Arrays.stream(c.getParameterTypes())
                .map(x -> x.getSimpleName().replaceAll("\\[\\]", "Array"))
                .collect(
                        StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append);
        f("public void p%06d000_testConstructor%s%s() {\n", toCompare.priority(), C.getSimpleName(), sb.toString());
        f("   System.out.println(\"Test %s\");\n", msg);
        f("   try {\n");
        f("        Constructor x = %s.class.getDeclaredConstructor(\n", C.getSimpleName());
        if (c.getParameterCount() > 0) {
            Class<?>[] tparam = c.getParameterTypes();
            f("           %s.class", tparam[0].getSimpleName());
            for (int i = 1; i < tparam.length; ++i) {
                f(",\n           %s.class", tparam[i].getSimpleName());
            }
        }
        f("\n        );\n");
        //f("        assertTrue(\"\", c.getModifiers() == %d);\n", c.getModifiers());
        checkModifiers(toCheck.value(), c.getModifiers(), toCheck.modifiers());

        f("   } catch (NoSuchMethodException | SecurityException ex) {\n");
        f("        fail(\"%s\");\n", msg);
        f("   }\n");
        f("}\n");
    }

    public void testMethods() {
        SB.append('\n');
        Arrays.stream(C.getDeclaredMethods())
                .filter(m -> m.getAnnotation(ToCompare.class) != null)
                .forEach(m -> {
                    ToCompare toCompare = m.getAnnotation(ToCompare.class);
                    testMethod(m, toCompare);
                });
    }

    private void testMethod(Method m, ToCompare toCompare) {
        f("\n@Test\n");        
        f("public void p%06d000_testMethod%s%s() {\n", toCompare.priority(), C.getSimpleName(), m.getName());
        f("   System.out.println(\"Test Method %s.%s\");\n", C.getSimpleName(), m.getName());
        f("   Class<?> classRef = %s.class;\n", toCompare.value().getName());
        f("   Class<?> classToTest = %s.class;\n", C.getSimpleName());
        f("   for (int i = 0; i < 100; ++i) {\n");
        f("      try {\n");
        f("         StringBuilder msg = new StringBuilder(\"revoir %s.%s() --> \");\n", C.getSimpleName(), m.getName());
        f("         boolean result = IntrospectionUtilities.sameResult(msg, classRef, classToTest, \"%s\");\n", m.getName());
        f("         assertTrue(msg.toString(), result);\n");
        f("      } catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException | IllegalAccessException | InstantiationException ex) {\n");        
        f("         fail(\"revoir %s.%s()\");\n", C.getSimpleName(), m.getName());
        f("      }\n");     
        f("   }\n");      
        f("}\n");             
    }

    public void checkFields() {
        SB.append('\n');

        Arrays.stream(C.getDeclaredFields())
                .filter(x -> x.getAnnotation(ToCheck.class) != null)
                .forEach(c -> {
                    checkField(c, c.getAnnotation(ToCheck.class));
                });

    }

    private void checkField(Field f, ToCheck toCheck) {
        f("\n@Test\n");

        f("public void p%06d000_checkField%s%s() {\n", toCheck.priority(), C.getSimpleName(), f.getName());
        f("   System.out.println(\"Check attribut %s\");\n", f.getName());
        f("   try {\n");
        f("        Field x = %s.class.getDeclaredField(\"%s\");\n", C.getSimpleName(), f.getName());
        checkModifiers(toCheck.value(), f.getModifiers(), toCheck.modifiers());
        f("        assertTrue(\"%s\", x.getType().equals(%s.class));\n", toCheck.value(), f.getType().getSimpleName());
        f("   } catch (NoSuchFieldException | SecurityException ex) {\n");
        f("        fail(\"%s\");\n", toCheck.value());
        f("   }\n");
        f("}\n");
    }

    public void checkSetters() {
        SB.append('\n');

        Arrays.stream(C.getDeclaredFields())
                .filter(x -> x.getAnnotation(SetterToCheck.class) != null)
                .forEach(f -> {
                    checkSetter(f, f.getAnnotation(SetterToCheck.class));
                });

    }

    private void checkSetter(Field f, SetterToCheck toCheck) {
        f("\n@Test\n");
        f("public void p%06d000_checkSetter%s%s() {\n", toCheck.priority(), C.getSimpleName(), f.getName());
        f("   System.out.println(\"Check setter %s\");\n", f.getName());
        f("   try {\n");
        f("        Method x = %s.class.getDeclaredMethod(\"set%s\", %s.class);\n",
                C.getSimpleName(),
                initial(f.getName()),
                f.getType().getSimpleName());
        f("        assertTrue(\"Revoir set%s\", x.getReturnType().equals(void.class));\n",
                initial(f.getName()));
        f("        Object o = IntrospectionUtilities.randomValue(%s.class);\n",
                C.getSimpleName());
        f("        for(int i = 0; i < 100; ++i) {\n");
        f("            %1$s v = (%1$s) IntrospectionUtilities.randomValue(%1$s.class);\n",
                f.getType().getSimpleName());
        f("            IntrospectionUtilities.getFromMethodTA(\n");
        f("                 %s.class,\n", C.getSimpleName());
        f("                 o,\n");
        f("                 \"set%s\",\n", initial(f.getName()));
        f("                 %s.class,\n", f.getType().getSimpleName());
        f("                 v\n");
        f("            );\n");
        f("            assertTrue(\"Revoir set%s\", ", initial(f.getName()));
        f("                                        IntrospectionUtilities.getAttribut(\n");
        f("                                           %s.class, \n", C.getSimpleName());
        f("                                           o, \n");
        f("                                           \"%s\"\n", f.getName());
        f("                                        ).equals(v));\n");
        f("        }\n");
        f("   } catch (SecurityException | NoSuchMethodException | NoSuchFieldException ex) {\n");
        f("        fail(\"Revoir set%s\");\n", initial(f.getName()));
        f("   } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {\n");
        f("        fail(\"Il manque un constructeur pour tester set%s\");\n", initial(f.getName()));
        f("   }\n");
        f("}\n");
    }

    public void checkGetters() {
        SB.append('\n');

        Arrays.stream(C.getDeclaredFields())
                .filter(x -> x.getAnnotation(SetterToCheck.class) != null)
                .forEach(f -> {
                    checkGetter(f, f.getAnnotation(GetterToCheck.class));
                });

    }

    private void checkGetter(Field f, GetterToCheck toCheck) {
        /*           
            for (int i = 0; i < 100; ++i) {
                Object o = IntrospectionUtilities.randomValue(Point.class);
                Object attr = IntrospectionUtilities.getAttribut(Point.class, o, "x");
                Object getAttr = IntrospectionUtilities.getFromMethod(Point.class, o, "getX");
                assertTrue("Revoir getX", IntrospectionUtilities.equals(attr, getAttr));
            }
        } catch (SecurityException | NoSuchMethodException | NoSuchFieldException ex) {
            fail("Revoir getX");
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            fail("Il manque un constructeur pour tester getX");
        }
    }*/
        String className = C.getSimpleName() + ".class";
        String attributName = f.getName();
        String getterName = "get" + initial(f.getName());

        f("\n@Test\n");
        f("public void p%06d000_checkGetter%s%s() {\n", toCheck.priority(), C.getSimpleName(), initial(f.getName()));
        f("   System.out.println(\"Check getter %s\");\n", attributName);
        f("   try {\n");
        f("     for(int i = 0; i < 100; ++i) {\n");
        f("         Object o = IntrospectionUtilities.randomValue(%s); \n", className);
        f("         Object attr = IntrospectionUtilities.getAttribut(%s, o, \"%s\");\n", className, attributName);
        f("         Object getAttr = IntrospectionUtilities.getFromMethod(%s, o, \"%s\");\n", className, getterName);
        f("         assertTrue(\"Revoir %s\", IntrospectionUtilities.equals(attr, getAttr));\n", getterName);
        f("     }\n");
        f("   } catch (SecurityException | NoSuchMethodException | NoSuchFieldException ex) {\n");
        f("        fail(\"Revoir %s\");\n", getterName);
        f("   } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {\n");
        f("        fail(\"Il manque un constructeur pour tester %s\");\n", getterName);
        f("   }\n");
        f("}\n");
    }

    /**
     * Affiche (grâce à f) tous les tests relatives aux modificateurs.
     *
     * @param msg
     * @param modifier
     * @param modifiers
     */
    private void checkModifiers(String msg, long modifier, CheckModifier[] modifiers) {
        for (CheckModifier cm : modifiers) {
            f("        " + cm.toString(), msg, modifier);
        }
    }

    public void checkMethods() {
        SB.append('\n');
        Arrays.stream(C.getDeclaredMethods())
                .filter(m -> m.getAnnotation(ToCheck.class) != null)
                .forEach(m -> {
                    ToCheck toCheck = m.getAnnotation(ToCheck.class);
                    checkMethod(m, toCheck);
                });
    }

    private void checkMethod(Method m, ToCheck toCheck) {
        String msg = toCheck.value();
        msg = msg.isEmpty() ? m.getName() : msg;
        f("\n@Test\n");
        StringBuilder sb = Arrays.stream(m.getParameterTypes())
                .map(x -> x.getSimpleName().replaceAll("\\[\\]", "Array"))
                .collect(
                        StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append);
        f("public void p%06d000_checkMethod%s%s%s() {\n", toCheck.priority(), C.getSimpleName(), m.getName(), sb.toString());
        f("   System.out.println(\"%s\");\n", msg);
        f("   try {\n");
        f("        Method x = %s.class.getDeclaredMethod(\"%s\"\n", C.getSimpleName(), m.getName());
        if (m.getParameterCount() > 0) {
            Class<?>[] tparam = m.getParameterTypes();
            f("          , %s.class", tparam[0].getSimpleName());
            for (int i = 1; i < tparam.length; ++i) {
                f(",\n           %s.class", tparam[i].getSimpleName());
            }
        }
        f("        );\n");
        checkModifiers(msg, m.getModifiers(), toCheck.modifiers());
        //f("        assertTrue(\"Revoir %s (Modificateurs)\", c.getModifiers() == %d);\n", msg, m.getModifiers());
        f("        assertTrue(\"Revoir %s (Retour)\", x.getReturnType().equals(%s.class));\n", msg, m.getReturnType().getSimpleName());
        f("   } catch (NoSuchMethodException | SecurityException ex) {\n");
        f("        fail(\"Revoir %s\");\n", msg);
        f("   }\n");
        f("}\n");
    }

    @Override
    public String toString() {
        return SB.toString();
    }

    public static class M1 {

        int a;
        private int b;

        @Override
        public String toString() {
            return "M1{" + "a=" + a + ", b=" + b + '}';
        }

    }

    public static class M2 {

        private M1 a = new M1();

        final M1 b = new M1();

        private double c;

        @Override
        public String toString() {
            return "M2{" + "a=" + a + ", b=" + b + ", c=" + c + '}';
        }

    }

    public static class I1 {

        private int c;
    }

    public static class I2 {

        final int d = 0;
    }

    public static class I3 {

        final I2 e = new I2();
    }

    public static class M4 {

        private int f;

        public int getF() {
            return f;
        }

        public void setF(int f) {
            this.f = f;
        }

    }

    private String initial(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public static void main(String[] args) {
        Checker chk = new Checker(A.class);
        System.out.println(chk);
    }
}
