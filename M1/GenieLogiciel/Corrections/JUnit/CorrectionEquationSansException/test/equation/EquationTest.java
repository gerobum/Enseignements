package equation;

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

    /**
     * La mauvaise idée serait de refaire la résolution de l'équation dans les
     * tests. C'est-à-dire (re)calculer delta et éventuellement x1 et x2.
     *
     * Si on a fait une erreur dans la classe Equation, il est probable qu'on
     * fasse la même dans les tests.
     *
     * Il faut donc extraire "l'essence" de la résolution d'une équation.
     * Pourquoi veut-on résoudre une equation ? Pour trouver la ou les valeurs
     * qui satisfont l'équation.
     *
     * Donc, si x1 a une valeur, il suffit de remplacer x par la valeur de x1
     * dans l'équation et vérifier si elle donne bien 0.
     *
     * Par exemple les racines de x^2 + x - 2 = 0 sont x1 = 1 et x1 = -2
     *
     * Si on remplace x par 1 : 1*1 + 1 - 2 = 0 (c'est bon)
     *
     * Si on remplace x par -2 : -2*-2 + -2 - 2 = 4 - 2 - 2 = 0 (c'est bon)
     *
     * C'est bien comme ça que vous faisiez en première pour vérifier que vous
     * aviez trouvé les bonnes racines. non ?
     */
    // @Test
    public void testPremiereIdeePasComplete() {
        int a, b, c;

        // On veut résoudre un million d'équations
        for (int i = 0; i < 1_000_000 ; i++) {

            // Attention à ne pas prendre n'importe quelle valeur de coefficients.
            a = 0;
            while (a == 0) {
                a = getCoef(1000);
            } // Pour avoir a dans [-1000, 0[ U ]0, 1000]
            b = getCoef(1000); // Pour avoir b dans [-1000, 1000]
            c = getCoef(1000); // Pour avoir c dans [-1000, 1000]

            // Résolution de l'équation
            Equation e = new Equation(a, b, c);

            assertTrue("a devrait être différent de 0", a != 0);

            // Les 3 cas peuvent survenir
            switch (e.nbRacines) {
                case 1:
                    // Plus le message est complet et plus le déboguage sera aisé.
                    assertEquals(String.format("Cas N°%d (racine double) %s => x1 = %f ", i, e, e.x1), 
                            // A gauche, la valeur attendue : 0
                            // Au milieu, le calcul en remplaçant x par e.x1 (notre résultat)
                            // A droite, EPSILON car deux doubles se comparent à EPSILON près.
                            0, a * e.x1 * e.x1 + b * e.x1 + c, EPSILON);
                    break;
                case 2:
                    // Idem mais pour les deux racines
                    assertEquals(String.format("Cas N°%d (2 racines) %s => x1 = %f ", i, e, e.x1), 
                            0, a * e.x1 * e.x1 + b * e.x1 + c, EPSILON);
                    assertEquals(String.format("Cas N°%d (2 racines) %s => x2 = %f ", i, e, e.x2), 
                            0, a * e.x2 * e.x2 + b * e.x2 + c, EPSILON);
                    break;
                default:
                    // Cas sans racine, il n'y a pas mieux que de vérifier si delta est bien négatif
                    assertTrue(String.format("Cas N°%d (pas de racine) %s => Le discrimant devrait être négatif alors qu'il est égal à %d ", i, e, (b * b - 4 * a * c)), 
                            b * b - 4 * a * c < 0.0);
                    break;
            }
        }
        // Mais je ne suis pas très satisfait car pas sûr que les trois cas ont été traités.
    }

    @Test
    public void testSomeMethod() {
        int a, b, c;
        int nbCasSansRacine = 0, nbCasRacineDouble = 0, nbCasDeuxRacines = 0;
        // La probabilité d'avoir un discriminant nul est faible.
        // Celle d'avoir une racine double encore plus.
        
        // On veut résoudre au moins un million d'équations et rencontrer au moins 10 cas sans racine (delta < 0)
        //                                                                au moins 5 cas d'une racine double (delta = 0)
        //                                                                au moins 10 cas de 2 racines (delta > 0)
        for (int i = 0; i < 1_000_000 || nbCasSansRacine < 10 || nbCasRacineDouble < 5 || nbCasDeuxRacines < 10; ++i) {

            a = 0;
            while (a == 0) {
                a = getCoef(1000);
            } // Pour avoir a dans [-1000, 0[ U ]0, 1000]
            b = getCoef(1000); // Pour avoir b dans [-1000, 1000]
            c = getCoef(1000); // Pour avoir c dans [-1000, 1000]

            // Résolution de l'équation
            Equation e = new Equation(a, b, c);

            assertTrue("a devrait être différent de 0", a != 0);

            // Les 3 cas peuvent survenir
            switch (e.nbRacines) {
                case 1:
                    // Plus le message est complet et plus le déboguage sera aisé.
                    assertEquals(String.format("Cas N°%d (racine double) %s => x1 = %f ", i, e, e.x1), 
                            // A gauche, la valeur attendue : 0
                            // Au milieu, le calcul en remplaçant x par e.x1 (notre résultat)
                            // A droite, EPSILON car deux doubles se comparent à EPSILON près.
                            0, a * e.x1 * e.x1 + b * e.x1 + c, EPSILON);
                    ++nbCasRacineDouble;
                    break;
                case 2:
                    // Idem mais pour les deux racines
                    assertEquals(String.format("Cas N°%d (2 racines) %s => x1 = %f ", i, e, e.x1), 
                            0, a * e.x1 * e.x1 + b * e.x1 + c, EPSILON);
                    assertEquals(String.format("Cas N°%d (2 racines) %s => x2 = %f ", i, e, e.x2), 
                            0, a * e.x2 * e.x2 + b * e.x2 + c, EPSILON);
                    ++nbCasDeuxRacines;
                    break;
                default:
                    // Cas sans racine, il n'y a pas mieux que de vérifier si delta est bien négatif                    
                    assertTrue(String.format("Cas N°%d (pas de racine) %s => Le discrimant devrait être négatif alors qu'il est égal à %d ", i, e, (b * b - 4 * a * c)), 
                            b * b - 4 * a * c < 0.0);
                    ++nbCasSansRacine;
                    break;
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
        for (int i = 0; i < 1_000_000 || nbCasSansRacine < 10 || nbCasDeuxRacines < 10; i++) {

            a = 0;
            while (a == 0) {
                a = getCoef(10000);
            }

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
        }

        System.out.println(nbCasSansRacine + ", " + nbCasRacineDouble + ", " + nbCasDeuxRacines);

        // Grâce â ces tests (s'ils passent), je peux (à peu près) garantir que mon programme 
        // résoud à 1e-7 près les racines réelles d'une équation du second degré
        // dont les coefficients a, b, c sont dans [-10000,10000].
        
        // La classe Equation fournie présentent des erreurs qui font que les tests ne passent pas
        // À vous de déboguer.
    }
}
