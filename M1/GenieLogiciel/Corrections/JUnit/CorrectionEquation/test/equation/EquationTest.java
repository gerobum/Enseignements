/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package equation;

import exceptions.NulCoefException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private static final Random random = new Random();

    @Test
    public void testSomeMethod() {
        int a = 0, b, c;
        int a0 = 0, n0 = 0, n1 = 0, n2 = 0;
        for (int i = 0; i < 1000000; i++) {
            try {
                a = 50 - random.nextInt(100);
                b = 50 - random.nextInt(100);
                c = 50 - random.nextInt(100);
                Equation e = new Equation(a, b, c);
                if (a == 0.0) fail();
                if (e.nbRacines == 1) {
                    assertEquals(e.toString(), 0, a * e.x1 * e.x1 + b * e.x1 + c, 1e-10);
                    assertEquals(e.toString(), 0, a * e.x2 * e.x2 + b * e.x2 + c, 1e-10);
                    ++n1;
                } else if (e.nbRacines == 2) {
                    assertEquals(e.toString(), 0, a * e.x1 * e.x1 + b * e.x1 + c, 1e-10);
                    assertEquals(e.toString(), 0, a * e.x2 * e.x2 + b * e.x2 + c, 1e-10);
                    ++n2;
                } else {
                    assertTrue(b * b - 4 * a * c < 0.0);
                    ++n0;
                }
            } catch (NulCoefException ex) {
                assertEquals(0, a);
                ++a0;
            }
        }
        assertTrue("Relancer le test pour avoir au moins un cas de coefficient a nul", a0 > 0);
        assertTrue("Relancer le test pour avoir au moins un cas sans racine", n0 > 0);
        assertTrue("Relancer le test pour avoir au moins un cas avec une racine double", n1 > 0);
        assertTrue("Relancer le test pour avoir au moins un cas ? deux racines", n2 > 0);

        System.out.println(a0 + ", " + n0 + ", " + n1 + ", " + n2);
    }
}
