
package equation;

import exceptions.NulCoefException;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yvan
 */
public class EquationTest {

    private static final Random RANDOM = new Random();
    // Vous pouvez tester les limites en diminuant EPSILON
    private static final double EPSILON = 1e-7;
    
    private int getCoef(final int LIM) {
        // La probabilité d'avoir un discriminant nul est d'autant plus faible
        // que la plage est grande. Si vous augmentez trop cette plage, 
        return LIM - RANDOM.nextInt(2*LIM+1);
    }

    @Test
    public void testSomeMethod() {
        int a = 0, b, c;
        int a0 = 0, n0 = 0, n1 = 0, n2 = 0;
        // La probabilité d'avoir un discriminant nul est faible.
        for (int i = 0; i < 1000000 || a0 < 10 || n0 < 10 || n1 < 5 || n2 < 10; i++) {
            try {
                a = getCoef(1000);
                b = getCoef(1000);
                c = getCoef(1000);
                Equation e = new Equation(a, b, c);
                assertTrue("a devrait ?tre différent de 0", a != 0);
                switch (e.nbRacines) {
                    case 1:
                        assertEquals(e.toString() + " x1 : " + e.x1, 0, a * e.x1 * e.x1 + b * e.x1 + c, EPSILON);
                        ++n1;
                        break;
                    case 2:
                        assertEquals(e.toString() + " x1 : " + e.x1, 0, a * e.x1 * e.x1 + b * e.x1 + c, EPSILON);
                        assertEquals(e.toString() + " x2 : " + e.x2, 0, a * e.x2 * e.x2 + b * e.x2 + c, EPSILON);
                        ++n2;
                        break;
                    default:
                        assertTrue(b * b - 4 * a * c < 0.0);
                        ++n0;
                        break;
                }
            } catch (NulCoefException ex) {
                assertEquals(0, a);
                ++a0;
            }
        }
        
        
        System.out.println(a0 + ", " + n0 + ", " + n1 + ", " + n2);
        
        a0 = 0; 
        n0 = 0; 
        n1 = 0; 
        n2 = 0;
        // La probabilité d'avoir un discriminant nul est d'autant plus faible
        // que la plage des coefficients est grande. Elle est quasiment nulle
        // pour des coefficients entre -10000 et 10000.
        // Pour pouvoir tester dans cette plage, je relance les tests sans imposer
        // la passage par le cas "une seule racine".
        for (int i = 0; i < 1000000 || a0 < 10 || n0 < 10 || n2 < 10; i++) {
            try {
                a = getCoef(10000);
                b = getCoef(10000);
                c = getCoef(10000);
                Equation e = new Equation(a, b, c);
                assertTrue("a devrait ?tre différent de 0", a != 0);
                switch (e.nbRacines) {
                    case 1:
                        assertEquals(e.toString() + " x1 : " + e.x1, 0, a * e.x1 * e.x1 + b * e.x1 + c, EPSILON);
                        ++n1;
                        break;
                    case 2:
                        assertEquals(e.toString() + " x1 : " + e.x1, 0, a * e.x1 * e.x1 + b * e.x1 + c, EPSILON);
                        assertEquals(e.toString() + " x2 : " + e.x2, 0, a * e.x2 * e.x2 + b * e.x2 + c, EPSILON);
                        ++n2;
                        break;
                    default:
                        assertTrue(b * b - 4 * a * c < 0.0);
                        ++n0;
                        break;
                }
            } catch (NulCoefException ex) {
                assertEquals(0, a);
                ++a0;
            }
        }

        System.out.println(a0 + ", " + n0 + ", " + n1 + ", " + n2);
        
        // Grâce ? ces tests, je peux (? peu pr?s) garantir que mon programme 
        // résoud ? 1e-7 pr?s les racines réelles d'une équation du second degré
        // dont les coefficients a, b, c dans [-10000,10000].
        
    }
}
