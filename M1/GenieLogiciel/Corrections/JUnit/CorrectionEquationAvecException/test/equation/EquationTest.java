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

    /**
     * Tirer un entier dans [-LIM, LIM]
     *
     * @param LIM
     * @return un entier dans [-LIM, LIM]
     */
    private int getCoef(final int LIM) {
        return LIM - RANDOM.nextInt(2 * LIM + 1);
    }

    @Test
    public void testSomeMethod() {
        int a = 0, b, c;
        int nbCasSansRacine = 0, nbCasRacineDouble = 0, nbCasDeuxRacines = 0, nbCasCoefNul = 0;
        // La probabilité d'avoir un discriminant nul est faible.
        for (int i = 0; i < 1000000 || nbCasCoefNul < 10 || nbCasSansRacine < 10 || nbCasRacineDouble < 5 || nbCasDeuxRacines < 10; i++) {
            try {
                a = getCoef(1000);
                b = getCoef(1000);
                c = getCoef(1000);
                Equation e = new Equation(a, b, c);

                assertNotEquals("a devrait être différent de 0", 0,  a);

                // Les 3 cas peuvent survenir
                switch (e.nbRacines) {
                    case 1:

                        assertEquals(String.format("Cas N°%d (racine double) %s => x1 = %f ", i, e, e.x1), 0, a * e.x1 * e.x1 + b * e.x1 + c, EPSILON);
                        ++nbCasRacineDouble;
                        break;
                    case 2:

                        assertEquals(String.format("Cas N°%d (2 racines) %s => x1 = %f ", i, e, e.x1), 0, a * e.x1 * e.x1 + b * e.x1 + c, EPSILON);
                        assertEquals(String.format("Cas N°%d (2 racines) %s => x2 = %f ", i, e, e.x2), 0, a * e.x2 * e.x2 + b * e.x2 + c, EPSILON);
                        ++nbCasDeuxRacines;
                        break;
                    default:
                        assertTrue(String.format("Cas N°%d (pas de racine) %s => Le discrimant devrait être négatif alors qu'il est égal à %d ", i, e, (b * b - 4 * a * c)), b * b - 4 * a * c < 0.0);
                        ++nbCasSansRacine;
                        break;
                }
            } catch (NulCoefException ex) {
                assertEquals(String.format("Cas N°%d (Coef a nul) => L'exception  NulCoefException a été lancée : a devrait être null", i), 0, a);
                ++nbCasCoefNul;
            }
        }
        
        System.out.println(nbCasSansRacine + ", " + nbCasRacineDouble + ", " + nbCasDeuxRacines);

        nbCasSansRacine = 0;
        nbCasRacineDouble = 0;
        nbCasDeuxRacines = 0;
        // La probabilité d'avoir un discriminant nul est d'autant plus faible
        // que la plage des coefficients est grande. Elle est quasiment nulle
        // pour des coefficients entre -10000 et 10000.
        // Pour pouvoir tester dans cette plage, je relance les tests sans imposer
        // la passage par le cas "une seule racine".
        for (int i = 0; i < 1_000_000 || nbCasCoefNul < 10 || nbCasSansRacine < 10 || nbCasDeuxRacines < 10; i++) {
            try {
                a = 0;

                a = getCoef(10000);
                b = getCoef(10000);
                c = getCoef(10000);

                Equation e = new Equation(a, b, c);

                assertNotEquals("a devrait être différent de 0", 0, a);

                switch (e.nbRacines) {
                    case 1:
                        assertEquals(String.format("Cas N°%d (racine double) %s => x1 = %f ", i, e, e.x1), 0, a * e.x1 * e.x1 + b * e.x1 + c, EPSILON);
                        ++nbCasRacineDouble;
                        break;
                    case 2:
                        assertEquals(String.format("Cas N°%d (2 racines) %s => x1 = %f ", i, e, e.x1), 0, a * e.x1 * e.x1 + b * e.x1 + c, EPSILON);
                        assertEquals(String.format("Cas N°%d (2 racines) %s => x2 = %f ", i, e, e.x2), 0, a * e.x2 * e.x2 + b * e.x2 + c, EPSILON);
                        ++nbCasDeuxRacines;
                        break;
                    default:
                        assertTrue(String.format("Cas N°%d (pas de racine) %s => Le discrimant devrait être négatif alors qu'il est égal à %d ", i, e, (b * b - 4 * a * c)), b * b - 4 * a * c < 0.0);
                        ++nbCasSansRacine;
                        break;
                }
            } catch (NulCoefException ex) {
                assertEquals(String.format("Cas N°%d (Coef a nul) => L'exception  NulCoefException a été lancée : a devrait être null", i), 0, a);
                ++nbCasCoefNul;
            }
        }

        System.out.println(nbCasSansRacine + ", " + nbCasRacineDouble + ", " + nbCasDeuxRacines);

        // Grâce â ces tests, je peux (à peu près) garantir que mon programme 
        // résoud à 1e-7 près les racines réelles d'une équation du second degré
        // dont les coefficients a, b, c sont dans [-10000,10000].
    }
}
