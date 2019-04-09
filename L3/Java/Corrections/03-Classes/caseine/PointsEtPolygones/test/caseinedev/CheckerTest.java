/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package caseinedev;

import static caseinedev.IntrospectionUtilities.isMutable;
import checker.Checker;
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
public class CheckerTest {
    
    public CheckerTest() {
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
     * Test of check method, of class Checker.
     */
    @Test
    public void testCheck() {
        System.out.println("check");
    }
    
    public static class M1 {
        int a;
        private int b;
    }
    
    public static class M2 {
        final M1 b = new M1();
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

    /**
     * Test of isMutable method, of class Checker.
     */
    @Test
    public void testIsMutable() {
        System.out.println("isMutable");
        assertTrue("Devrait être mutable", isMutable(M1.class));
        assertTrue("Devrait être mutable", isMutable(M2.class));
        assertFalse("Devrait être immuable", isMutable(I1.class));
        assertFalse("Devrait être immuable", isMutable(I2.class));
        assertFalse("Devrait être immuable", isMutable(I3.class));
        assertTrue("Devrait être immuable", isMutable(M4.class));
    }

    /**
     * Test of checkClass method, of class Checker.
     */
    @Test
    public void testCheckClass() {
        System.out.println("checkClass");
    }

    /**
     * Test of checkConstructors method, of class Checker.
     */
    @Test
    public void testCheckConstructors() {
        System.out.println("checkConstructors");
    }

    /**
     * Test of checkMethods method, of class Checker.
     */
    @Test
    public void testCheckMethods() {
        System.out.println("checkMethods");
    }

    /**
     * Test of checkFields method, of class Checker.
     */
    @Test
    public void testCheckFields() {
        System.out.println("checkFields");
    }

    /**
     * Test of toString method, of class Checker.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
    }

    /**
     * Test of main method, of class Checker.
     */
    @Test
    public void testMain() {
        System.out.println("main");
    }
    
}
