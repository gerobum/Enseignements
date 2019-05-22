package checkerold;

import caseinedevold.IntrospectionUtilities;
import cf.pti_pgm.geometrie.Point;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import static caseinedevold.IntrospectionUtilities.*;
import java.awt.Color;

/**
 *
 * @author yvan
 */
public class Checker {

    private StringBuilder SB;
    private Formatter F;
    private final Class<?> C;

    public Checker(Class<?> c) {
        SB = new StringBuilder();
        F = new Formatter(SB);
        C = c;
        check();
    }

    private void f(String format, Object... o) {
        F.format(format, o);
    }

    public final void check() {
        SB = new StringBuilder();
        F = new Formatter(SB);
        ToCheck annotation = C.getAnnotation(ToCheck.class);
        if (annotation != null) {
            checkClass(annotation.value());
        }
        // --- Attributs 
        checkFields();
        // --- Constructeurs 
        checkConstructors();
        // --- Méthodes 
        checkMethods();

    }


    public void checkClass(String msg) {
        f("\n@Test\n");
        f("public void checkClass%s() {\n", C.getSimpleName());
        f("   System.out.println(\"check class %s\");\n", C.getSimpleName());
        f("   assertTrue(\"%s\", %s.class.getSuperclass().equals(%s.class));\n",
                msg + " (Héritage)",
                C.getSimpleName(),
                C.getSuperclass().getSimpleName());

        f("   assertTrue(\"%s\", %s.class.getModifiers() == %s);\n",
                msg + " (Modificateurs)",
                C.getSimpleName(),
                C.getModifiers()
        );
        f("}\n");
    }

    private void checkConstructor(Constructor<?> c, String msg) {
        f("\n@Test\n");
        StringBuilder sb = Arrays.stream(c.getParameterTypes())
                .map(x -> x.getSimpleName().replaceAll("\\[\\]", "Array"))
                .collect(
                        StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append);
        f("public void checkConstructor%s%s() {\n", C.getSimpleName(), sb.toString());
        f("   System.out.println(\"%s\");\n", msg);
        f("   try {\n");
        f("        Constructor c = %s.class.getDeclaredConstructor(\n", C.getSimpleName());
        if (c.getParameterCount() > 0) {
            Class<?>[] tparam = c.getParameterTypes();
            f("           %s.class", tparam[0].getSimpleName());
            for (int i = 1; i < tparam.length; ++i) {
                f(",\n           %s.class", tparam[i].getSimpleName());
            }
        }
        f("\n        );\n");
        f("        assertTrue(\"\", c.getModifiers() == %d);\n", c.getModifiers());
        f("   } catch (NoSuchMethodException | SecurityException ex) {\n");
        f("        fail(\"%s\");\n", msg);
        f("   }\n");
        f("}\n");
    }

    /*    @Test
    public void testPresenceConstructor() {
        System.out.println("Présence constructeur");
        try {
            Constructor c = Polygone.class.getConstructor(
                    Point.class,
                    Point.class,
                    Point.class,
                    tpoints);
            assertTrue("Vérifiez les modificateurs d'accès du constructeur",
                    Modifier.isPublic(c.getModifiers())
            );
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Vérifiez la présence d'un constructeur qui permet de créer des polygones d'au moins"
                    + " trois points et éventuellement davantage.");
        }
    }*/
    public void checkConstructors() {
        SB.append('\n');
        Arrays.stream(C.getDeclaredConstructors())
                .filter(c -> c.getAnnotation(ToCheck.class) != null)
                .forEach((c) -> {
                    checkConstructor(c, c.getAnnotation(ToCheck.class).value());
                });
    }

    public void checkMethods() {
        SB.append('\n');
        Arrays.stream(C.getDeclaredMethods())
                .filter(m -> m.getAnnotation(ToCheck.class) != null)
                .forEach(m -> {
                    checkMethod(m, m.getAnnotation(ToCheck.class).value());
                });
    }

    public void checkFields() {
        SB.append('\n');

        Arrays.stream(C.getDeclaredFields())
                .filter(x -> x.getAnnotation(ToCheck.class) != null)
                .forEach(c -> {
                    checkField(c, c.getAnnotation(ToCheck.class).value());
                });

    }

    private void checkField(Field f, String msg) {
        f("\n@Test\n");

        f("public void checkField%s() {\n", f.getName());
        f("   System.out.println(\"Check attribut %s\");\n", f.getName());
        f("   try {\n");
        f("        Field f = %s.class.getDeclaredField(\"%s\");\n", C.getSimpleName(), f.getName());
        f("        assertTrue(\"%s\", f.getModifiers() == %d);\n", msg, f.getModifiers());
        f("        assertTrue(\"%s\", f.getType().equals(%s.class));\n", msg, f.getType().getSimpleName());
        f("   } catch (NoSuchFieldException | SecurityException ex) {\n");
        f("        fail(\"%s\");\n", msg);
        f("   }\n");
        f("}\n");
    }

    private void checkMethod(Method m, String msg) {
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
        f("        Method c = %s.class.getDeclaredMethod(\"%s\"\n", C.getSimpleName(), m.getName());
        if (m.getParameterCount() > 0) {
            Class<?>[] tparam = m.getParameterTypes();
            f("          , %s.class", tparam[0].getSimpleName());
            for (int i = 1; i < tparam.length; ++i) {
                f(",\n           %s.class", tparam[i].getSimpleName());
            }
        }
        f("        );\n");
        f("        assertTrue(\"Revoir %s (Modificateurs)\", c.getModifiers() == %d);\n", msg, m.getModifiers());
        f("        assertTrue(\"Revoir %s (Retour)\", c.getReturnType().equals(%s.class));\n", msg, m.getReturnType().getSimpleName());
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

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        Checker ckr = new Checker(Point.class);
        //System.out.println(ckr);
        
        System.out.println(randomValue(double.class));
        System.out.println(randomValue(int.class));
        System.out.println(randomValue(void.class));
        System.out.println(randomValue(M2.class));
        
        
        System.out.println(randomValue(Integer.class));
        System.out.println(randomValue(Double.class));
        System.out.println(randomValue(Character.class));
        
        
        Object o1 = randomValue(M2.class);
        System.out.println(o1);
        Object o2 = IntrospectionUtilities.clone(o1);
        System.out.println(IntrospectionUtilities.equals(o1, o2));
        //System.out.println(checkIfMutable(M2.class));
        //System.out.println(checkIfMutable(M4.class));
        
        ckr = new Checker(cf.pti_pgm.geometrie.Point.class);
        System.out.println(ckr);
    }
}
