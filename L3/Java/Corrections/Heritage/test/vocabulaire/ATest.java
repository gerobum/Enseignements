/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package vocabulaire;

import caseinedev.IntrospectionUtilities;
import java.lang.reflect.*;
import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yvan
 */
public class ATest {

    @Test
    public void p0000_checkClassA() {
        System.out.println("check class A");
        assertTrue(" (Héritage)", A.class.getSuperclass().equals(Object.class));
        Class<?> x = A.class;
        assertTrue("Revoir  (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
        assertTrue("Revoir  (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
        assertTrue("Revoir  (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
        assertTrue("Revoir  (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
    }

    @Test
    public void p0000_checkFielda() {
        System.out.println("Check attribut a");
        try {
            Field x = A.class.getDeclaredField("a");
            assertTrue("Revoir  (Modificateurs)", Modifier.isFinal(2) == Modifier.isFinal(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isPrivate(2) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isProtected(2) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isPublic(2) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isStatic(2) == Modifier.isStatic(x.getModifiers()));
            assertTrue("", x.getType().equals(int.class));
        } catch (NoSuchFieldException | SecurityException ex) {
            fail("");
        }
    }

    @Test
    public void p0000_checkFieldB() {
        System.out.println("Check attribut B");
        try {
            Field x = A.class.getDeclaredField("B");
            assertTrue("Revoir  (Modificateurs)", Modifier.isFinal(20) == Modifier.isFinal(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isPrivate(20) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isProtected(20) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isPublic(20) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isStatic(20) == Modifier.isStatic(x.getModifiers()));
            assertTrue("", x.getType().equals(char.class));
        } catch (NoSuchFieldException | SecurityException ex) {
            fail("");
        }
    }

    @Test
    public void p0000_checkFieldC() {
        System.out.println("Check attribut C");
        try {
            Field x = A.class.getDeclaredField("C");
            assertTrue("Revoir  (Modificateurs)", Modifier.isFinal(24) == Modifier.isFinal(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isPrivate(24) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isProtected(24) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isPublic(24) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isStatic(24) == Modifier.isStatic(x.getModifiers()));
            assertTrue("", x.getType().equals(String.class));
        } catch (NoSuchFieldException | SecurityException ex) {
            fail("");
        }
    }

    @Test
    public void p0000_checkConstructorA() {
        System.out.println("Constructeur de vocabulaire.A");
        try {
            Constructor x = A.class.getDeclaredConstructor();
            assertTrue("Revoir  (Modificateurs)", Modifier.isFinal(1) == Modifier.isFinal(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Constructeur de vocabulaire.A");
        }
    }

    @Test
    public void checkMethodsetAint() {
        System.out.println("setA");
        try {
            Method x = A.class.getDeclaredMethod("setA",
                    int.class);
            assertTrue("Revoir setA (Modificateurs)", Modifier.isFinal(1) == Modifier.isFinal(x.getModifiers()));
            assertTrue("Revoir setA (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir setA (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir setA (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir setA (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir setA (Retour)", x.getReturnType().equals(void.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir setA");
        }
    }

    @Test
    public void checkMethodgetB() {
        System.out.println("getB");
        try {
            Method x = A.class.getDeclaredMethod("getB"
            );
            assertTrue("Revoir getB (Modificateurs)", Modifier.isFinal(1) == Modifier.isFinal(x.getModifiers()));
            assertTrue("Revoir getB (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir getB (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir getB (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir getB (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir getB (Retour)", x.getReturnType().equals(char.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir getB");
        }
    }

    @Test
    public void checkMethodgetC() {
        System.out.println("getC");
        try {
            Method x = A.class.getDeclaredMethod("getC"
            );
            assertTrue("Revoir getC (Modificateurs)", Modifier.isFinal(9) == Modifier.isFinal(x.getModifiers()));
            assertTrue("Revoir getC (Modificateurs)", Modifier.isPrivate(9) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir getC (Modificateurs)", Modifier.isProtected(9) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir getC (Modificateurs)", Modifier.isPublic(9) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir getC (Modificateurs)", Modifier.isStatic(9) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir getC (Retour)", x.getReturnType().equals(String.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir getC");
        }
    }

    @Test
    public void checkMethodgetA() {
        System.out.println("getA");
        try {
            Method x = A.class.getDeclaredMethod("getA"
            );
            assertTrue("Revoir getA (Modificateurs)", Modifier.isFinal(1) == Modifier.isFinal(x.getModifiers()));
            assertTrue("Revoir getA (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir getA (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir getA (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir getA (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir getA (Retour)", x.getReturnType().equals(int.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir getA");
        }
    }

    @Test
    public void p00000_checkSettera() {
        System.out.println("Check setter a");
        try {
            Method x = A.class.getDeclaredMethod("setA", int.class);
            assertTrue("Revoir setA", x.getReturnType().equals(void.class));
            Object o = IntrospectionUtilities.randomValue(A.class);
            for (int i = 0; i < 100; ++i) {
                int v = (int) IntrospectionUtilities.randomValue(int.class);
                IntrospectionUtilities.getFromMethodTA(
                        A.class,
                        o,
                        "setA",
                        int.class,
                        v
                );
                assertTrue("Revoir setA", IntrospectionUtilities.getAttribut(
                        A.class,
                        o,
                        "a"
                ).equals(v));
            }
        } catch (SecurityException | NoSuchMethodException | NoSuchFieldException ex) {
            fail("Revoir setA");
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            fail("Il manque un constructeur pour tester setA");
        }
    }
}
