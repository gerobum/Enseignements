package checker;

import tags.ToCheck;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Formatter;
import tags.CheckModifier;

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

    }


    public void checkClass(ToCheck toCheck) {
        f("\n@Test\n");
        f("public void checkClass%s() {\n", C.getSimpleName());
        f("   System.out.println(\"check class %s\");\n", C.getSimpleName());
        f("   assertTrue(\"%s\", %s.class.getSuperclass().equals(%s.class));\n",
                toCheck.value() + " (Héritage)",
                C.getSimpleName(),
                C.getSuperclass().getSimpleName());
        checkModifiers(toCheck.value(), C.getModifiers(), toCheck.modifiers());
        /*f("   assertTrue(\"%s\", %s.class.getModifiers() == %s);\n",
                toCheck.value() + " (Modificateurs)",
                C.getSimpleName(),
                C.getModifiers()
        );*/
        
        f("}\n");
    }

    private void checkConstructor(Constructor<?> c, ToCheck toCheck) {
        f("\n@Test\n");
        StringBuilder sb = Arrays.stream(c.getParameterTypes())
                .map(x -> x.getSimpleName().replaceAll("\\[\\]", "Array"))
                .collect(
                        StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append);
        f("public void checkConstructor%s%s() {\n", C.getSimpleName(), sb.toString());
        f("   System.out.println(\"%s\");\n", toCheck.value());
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
        f("        fail(\"%s\");\n", toCheck.value());
        f("   }\n");
        f("}\n");
    }

    public void checkConstructors() {
        SB.append('\n');
        Arrays.stream(C.getDeclaredConstructors())
                .filter(c -> c.getAnnotation(ToCheck.class) != null)
                .forEach((c) -> {
                    checkConstructor(c, c.getAnnotation(ToCheck.class));
                });
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

        f("public void checkField%s() {\n", f.getName());
        f("   System.out.println(\"Check attribut %s\");\n", f.getName());
        f("   try {\n");
        f("        Field x = %s.class.getDeclaredField(\"%s\");\n", C.getSimpleName(), f.getName());
        //f("        assertTrue(\"%s\", f.getModifiers() == %d);\n", msg, f.getModifiers());
        
        checkModifiers(toCheck.value(), f.getModifiers(), toCheck.modifiers());
        f("        assertTrue(\"%s\", x.getType().equals(%s.class));\n", toCheck.value(), f.getType().getSimpleName());
        f("   } catch (NoSuchFieldException | SecurityException ex) {\n");
        f("        fail(\"%s\");\n", toCheck.value());
        f("   }\n");
        f("}\n");
    }
    
    /**
     * Affiche (grâce à f) tous les tests relatives aux modificateurs.
     * @param msg
     * @param modifier
     * @param modifiers 
     */
    private void checkModifiers(String msg, long modifier, CheckModifier[] modifiers) {
        //f("        assertTrue(\"Revoir %s (Modificateurs)\", c.getModifiers() == %d);\n", msg, m.getModifiers());
        //StringBuilder sb = new StringBuilder();
        //if (modifiersToCompare.checkIsABSTRACT())
        //    f("        assertTrue(\"Revoir %s (Modificateurs)\", Modifier.isAbstract(%d) == Modifier.isAbstract(x.getModifiers()));\n", msg, modifier);
        for(CheckModifier cm : modifiers) 
            f("        " + cm.toString(), msg, modifier);
        //return sb.toString();
    }
    
    private void checkSimpleSetter() {
        
    }

    private void checkMethod(Method m, ToCheck toCheck) {
        String msg = toCheck.value();
        msg = msg.isEmpty() ? m.toString() : msg;
        f("\n@Test\n");
        StringBuilder sb = Arrays.stream(m.getParameterTypes())
                .map(x -> x.getSimpleName().replaceAll("\\[\\]", "Array"))
                .collect(
                        StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append);
        f("public void checkMethod%s%s() {\n", m.getName(), sb.toString());
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
    public static void main(String[] args) {
        //System.out.println("OK");
        Checker checker = new Checker(Checker.class);
        System.out.println(checker);
        //CheckModifier cm;
        
        for(CheckModifier cm : CheckModifier.values()) {
            System.out.println(cm);
        }
        
        System.out.println(CheckModifier.isFinal.name());
        
    }
}
