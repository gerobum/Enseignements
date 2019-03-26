/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equation;

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

    private static Random r;

    public EquationTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        r = new Random();
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
     * Test of toString method, of class Equation.
     */
    @Test
    public void testToString() {

    }

    /**
     * Test of main method, of class Equation.
     */
    @Test
    public void testMain() throws Exception {

    }

    @Test
    public void testEquation() {
        System.out.println("Test équation");
        Equation e = new Equation(1, 0, 0);
        //assertEquals(Stria = r.nextInt(1000)-500;ng.format("%s devrait n'avoir qu'une racine", e.toString()), 1, e.nbRacines);
        assertEquals(0.0, e.x1, 1e-10);
        assertEquals(Double.NaN, e.x2, 0.0);
        int nb0, nb1, nb2;
        
        nb0 = nb1 = nb2 = 0;

        for (int i = 0; i < 100000 || (nb1 < 10 || nb2 < 10 || nb0 < 10); i++) {
            int a = r.nextInt(1000)-500;
            while(a == 0)
                a = r.nextInt(1000)-500;
            int b = r.nextInt(1000)-500;
            int c = r.nextInt(1000)-500;
            e = new Equation(a, b, c);
            if (e.nbRacines == 1) {
                nb1++;
                
                assertEquals(e.toString(), 0, a * e.x1 * e.x1 + b * e.x1 + c, 1e-10);
                assertEquals(Double.NaN, e.x2, 1e-10);
            } else if (e.nbRacines == 2) {
                nb2++;
                assertEquals(e.toString(), 0, a * e.x1 * e.x1 + b * e.x1 + c, 1e-10);
                assertEquals(e.toString(), 0, a * e.x2 * e.x2 + b * e.x2 + c, 1e-10);
            } else {
                nb0++;
                assertEquals(e.toString(), Double.NaN, e.x1, 1e-10);
                assertEquals(e.toString(), Double.NaN, e.x2, 1e-10);
            }
        }
        
        System.out.println(nb0 + " " + nb1 + " " + nb2);

    }

}
