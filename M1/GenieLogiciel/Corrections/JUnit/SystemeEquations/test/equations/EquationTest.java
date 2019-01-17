/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equations;

import java.util.Random;
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
public class EquationTest {
    private Equation e;
    private static final Random RANDOM = new Random();
    
    public EquationTest() {
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
     * Test of set method, of class Equation.
     */
    @Test
    public void testSetGet() {
        int nbeq = 1 + RANDOM.nextInt(100);
        e = new Equation(nbeq);
        for(int i = 0; i < 1000; ++i) {
            int c = RANDOM.nextInt(nbeq+1);
            double v = RANDOM.nextDouble();
            e.set(c, v);
            assertEquals(v, e.get(c), 0.0);
        }

    }

    /**
     * Test of set method, of class Equation.
     */
    @Test
    public void testSet() {
        System.out.println("set");
    }

    /**
     * Test of get method, of class Equation.
     */
    @Test
    public void testGet() {
        System.out.println("get");
    }

    /**
     * Test of mettreA1 method, of class Equation.
     */
    @Test
    public void testMettreA1() {
        System.out.println("mettreA1");

    }

    /**
     * Test of hashCode method, of class Equation.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");

    }

    /**
     * Test of equals method, of class Equation.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");

        Equation e1 = new Equation(3);
        Equation e2 = new Equation(4);
        for(int i = 0; i <= 3; i++) {
            e1.set(i, 1);
        }
        for(int i = 0; i <= 3; i++) {
            e2.set(i, 1);
        }
        assertFalse(e1.equals(e2));
        
        
        e1 = new Equation(3);
        e2 = new Equation(3);
        for(int i = 0; i <= 3; i++) {
            e1.set(i, 1);
        }
        for(int i = 0; i <= 3; i++) {
            e2.set(i, 1);
        }
        
        assertTrue(e1.equals(e2));
        
        for(int i = 0; i <= 3; i++) {
            e1.set(i, (RANDOM.nextDouble()-0.5)*(10+RANDOM.nextInt(20)));
        }
        double c = (RANDOM.nextDouble()-0.5)*(10+RANDOM.nextInt(20));
        for(int i = 0; i <= 3; i++) {
            e2.set(i, e1.get(i)*c);
        }
        
        assertTrue(e1.equals(e2));
        
        e1.set(0, e1.get(0)+1);
        
        assertFalse(e1.equals(e2));
    }
}
