/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package caseine.cf.fractions;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author yvan
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FractionTest {

    public FractionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of toString method, of class Fraction.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
    }

    /**
     * Test of hashCode method, of class Fraction.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
    }

    /**
     * Test of equals method, of class Fraction.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
    }

    /**
     * Test of opposé method, of class Fraction.
     */
    @Test
    public void testOpposé() {
        System.out.println("oppos\u00e9");
    }

    /**
     * Test of inverse method, of class Fraction.
     */
    @Test
    public void testInverse() throws Exception {
        System.out.println("inverse");
    }

    /**
     * Test of plus method, of class Fraction.
     */
    @Test
    public void testPlus() {
        System.out.println("plus");
    }

    /**
     * Test of moins method, of class Fraction.
     */
    @Test
    public void testMoins() {
        System.out.println("moins");
    }

    /**
     * Test of diviséPar method, of class Fraction.
     */
    @Test
    public void testDiviséPar() throws Exception {
        System.out.println("divis\u00e9Par");
    }

    /**
     * Test of fois method, of class Fraction.
     */
    @Test
    public void testFois() {
        System.out.println("fois");
    }

    @Test
    public void p0300_checkMethodequalsObject() {
        System.out.println("equals");
        try {
            Method x = caseine.rf.fractions.Fraction.class.getDeclaredMethod("equals",
                    Object.class);
            assertTrue("Revoir equals (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir equals (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir equals (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir equals (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir equals (Retour)", x.getReturnType().equals(boolean.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir equals");
        }
    }

    @Test
    public void p0100_checkMethodtoString() {
        System.out.println("toString");
        try {
            Method x = caseine.rf.fractions.Fraction.class.getDeclaredMethod("toString"
            );
            assertTrue("Revoir toString (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir toString (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir toString (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir toString (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir toString (Retour)", x.getReturnType().equals(String.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir toString");
        }
    }

    @Test
    public void p0200_checkMethodhashCode() {
        System.out.println("hashCode");
        try {
            Method x = caseine.rf.fractions.Fraction.class.getDeclaredMethod("hashCode"
            );
            assertTrue("Revoir hashCode (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir hashCode (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir hashCode (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir hashCode (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir hashCode (Retour)", x.getReturnType().equals(int.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir hashCode");
        }
    }

    @Test
    public void p1100_checkFielda() {
        System.out.println("Check attribut a");
        try {
            Field x = Polynome.class.getDeclaredField("a");
            assertTrue("Revoir  (Modificateurs)", Modifier.isPrivate(18) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isProtected(18) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isPublic(18) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isStatic(18) == Modifier.isStatic(x.getModifiers()));
            assertTrue("", x.getType().equals(caseine.rf.fractions.Fraction[].class));
        } catch (NoSuchFieldException | SecurityException ex) {
            fail("");
        }
    }

    @Test
    public void p1200_checkConstructorPolynomeFractionArray() {
        System.out.println("Présence Constructeur de caseine.rf.fractions.Polynome");
        try {
            Constructor x = caseine.rf.fractions.Polynome.class.getDeclaredConstructor(
                    caseine.rf.fractions.Fraction[].class
            );
            assertTrue("Revoir  (Modificateurs)", Modifier.isPrivate(129) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isProtected(129) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isPublic(129) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isStatic(129) == Modifier.isStatic(x.getModifiers()));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Constructeur de caseine.rf.fractions.Polynome");
        }
    }

    @Test
    public void p1400_checkMethodgetDegre() {
        System.out.println("getDegre");
        try {
            Method x = caseine.rf.fractions.Polynome.class.getDeclaredMethod("getDegre"
            );
            assertTrue("Revoir getDegre (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir getDegre (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir getDegre (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir getDegre (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir getDegre (Retour)", x.getReturnType().equals(int.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir getDegre");
        }
    }

    @Test
    public void p1500_checkMethodevaluerFraction() {
        System.out.println("evaluer");
        try {
            Method x = caseine.rf.fractions.Polynome.class.getDeclaredMethod("evaluer",
                     caseine.rf.fractions.Fraction.class);
            assertTrue("Revoir evaluer (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir evaluer (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir evaluer (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir evaluer (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir evaluer (Retour)", x.getReturnType().equals(Fraction.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir evaluer");
        }
    }

    @Test
    public void p1300_checkMethodgetint() {
        System.out.println("get");
        try {
            Method x = caseine.rf.fractions.Polynome.class.getDeclaredMethod("get",
                     int.class);
            assertTrue("Revoir get (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir get (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir get (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir get (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir get (Retour)", x.getReturnType().equals(caseine.rf.fractions.Fraction.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir get");
        }
    }

    @Test
    public void p1800_checkMethodequalsObject() {
        System.out.println("equals");
        try {
            Method x = caseine.rf.fractions.Polynome.class.getDeclaredMethod("equals",
                     Object.class);
            assertTrue("Revoir equals (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir equals (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir equals (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir equals (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir equals (Retour)", x.getReturnType().equals(boolean.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir equals");
        }
    }

    @Test
    public void p1600_checkMethodtoString() {
        System.out.println("toString");
        try {
            Method x = caseine.rf.fractions.Polynome.class.getDeclaredMethod("toString"
            );
            assertTrue("Revoir toString (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir toString (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir toString (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir toString (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir toString (Retour)", x.getReturnType().equals(String.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir toString");
        }
    }

    @Test
    public void p1700_checkMethodhashCode() {
        System.out.println("hashCode");
        try {
            Method x = caseine.rf.fractions.Polynome.class.getDeclaredMethod("hashCode"
            );
            assertTrue("Revoir hashCode (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir hashCode (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir hashCode (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir hashCode (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir hashCode (Retour)", x.getReturnType().equals(int.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir hashCode");
        }
    }
}
