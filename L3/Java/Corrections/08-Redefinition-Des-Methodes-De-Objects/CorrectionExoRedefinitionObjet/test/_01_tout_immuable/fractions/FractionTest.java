/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package _01_tout_immuable.fractions;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yvan
 */
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
        Fraction instance = new Fraction();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Fraction.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Fraction instance = new Fraction();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Fraction.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Fraction instance = new Fraction();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of opposé method, of class Fraction.
     */
    @Test
    public void testOpposé() {
        System.out.println("oppos\u00e9");
        Fraction instance = new Fraction();
        Fraction expResult = null;
        Fraction result = instance.opposé();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inverse method, of class Fraction.
     */
    @Test
    public void testInverse() throws Exception {
        System.out.println("inverse");
        Fraction instance = new Fraction();
        Fraction expResult = null;
        Fraction result = instance.inverse();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of plus method, of class Fraction.
     */
    @Test
    public void testPlus() {
        System.out.println("plus");
        Fraction f = null;
        Fraction instance = new Fraction();
        Fraction expResult = null;
        Fraction result = instance.plus(f);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of moins method, of class Fraction.
     */
    @Test
    public void testMoins() {
        System.out.println("moins");
        Fraction f = null;
        Fraction instance = new Fraction();
        Fraction expResult = null;
        Fraction result = instance.moins(f);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of diviséPar method, of class Fraction.
     */
    @Test
    public void testDiviséPar() throws Exception {
        System.out.println("divis\u00e9Par");
        Fraction f = null;
        Fraction instance = new Fraction();
        Fraction expResult = null;
        Fraction result = instance.diviséPar(f);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fois method, of class Fraction.
     */
    @Test
    public void testFois() {
        System.out.println("fois");
        Fraction f = null;
        Fraction instance = new Fraction();
        Fraction expResult = null;
        Fraction result = instance.fois(f);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
