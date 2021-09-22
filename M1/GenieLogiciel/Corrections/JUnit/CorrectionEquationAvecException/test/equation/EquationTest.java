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
        // Donc je teste au moins un million de cas et davantage si les différentes situations n'ont pas été rencontrées suffisamment.
        // Ce test prend environ 8 secondes. C'est relativement long mais acceptable.
        // Probablement qu'il serait judicieux de faire une petite anayse numérique pour provoquer plus souvent les cas rares et éviter de
        // dépasser le million de tentatives.
        for (int i = 0; i < 1_000_000 || nbCasCoefNul < 10 || nbCasSansRacine < 10 || nbCasRacineDouble < 5 || nbCasDeuxRacines < 10; i++) {
            try {
                // Tirage au hasard de coefs entre -1000 et 1000
                a = getCoef(1000); // a peut être nul
                b = getCoef(1000);
                c = getCoef(1000);
                Equation e = new Equation(a, b, c);
                // Si a est nul, on ne devrait pas arriver là : c'est ce que prévient l'assert qui suit.
                assertNotEquals(String.format("Cas N°%d (Le coef a ne peut pas être nul) %s => x1 = %f ", i, e, e.getX1()), 0,  a);
                
                // Remarque importante pour tous les asserts. Le message doit être aussi complet que possible. Afin de pouvoir reproduire l'erreur
                // pour le débogage.

                // Les 3 cas peuvent survenir
                switch (e.getRootsCount()) {
                    case 1:
                        // Cas d'une racine double
                        
                        // Elle est résolue dans x1.
                        assertEquals(String.format("Cas N°%d (racine double) %s => x1 = %f ", i, e, e.getX1()), 0.0, a * e.getX1() * e.getX1() + b * e.getX1() + c, EPSILON);
                        


                        // Voici comment faire si l'on veut vérifier que x2 est bien égal à NaN (ce qui n'est pas explicitement demandé dans l'énoncé)
                        assertTrue(String.format("Cas N°%d (racine double) %s => x1 = %f ", i, e, e.getX1()), Double.isNaN(e.getX2()));                        
                        // Attention, il est délicat de tester NaN car NaN est différent de toute valeur de type double MÊME DE LUI-MÊME.
                        // Il faut donc utiliser Double.isNaN()
                        
                        // Un cas de racine double en plus (ils sont rares)
                        ++nbCasRacineDouble;
                        break;
                        
                    case 2:
                        // Cas de deux racines
                        
                        // Elles sont résolus dans x1 et x2.                        
                        assertEquals(String.format("Cas N°%d (2 racines) %s => x1 = %f ", i, e, e.getX1()), 0, a * e.getX1() * e.getX1() + b * e.getX1() + c, EPSILON);
                        assertEquals(String.format("Cas N°%d (2 racines) %s => x2 = %f ", i, e, e.getX2()), 0, a * e.getX2() * e.getX2() + b * e.getX2() + c, EPSILON);

                        // Attention dans ce cas, x1 et x2 doivent être différentes.
                        assertNotEquals(e.getX1(), e.getX2());
                        
                        // Un cas de deux racines en plus.
                        ++nbCasDeuxRacines;
                        break;
                    default:
                        // Cas sans racine
                        
                        assertTrue(String.format("Cas N°%d (pas de racine) %s => Le discrimant devrait être négatif alors qu'il est égal à %d ", i, e, (b * b - 4 * a * c)), b * b - 4 * a * c < 0.0);

                        // Voici comment faire si l'on veut vérifier que x2 est bien égal à NaN (ce qui n'est pas explicitement demandé dans l'énoncé)
                        assertTrue(String.format("Cas N°%d (racine double) %s => x1 = %f ", i, e, e.getX1()), Double.isNaN(e.getX2()));                        
                        // Attention, il est délicat de tester NaN car NaN est différent de toute valeur de type double MÊME DE LUI-MÊME.
                        // Il faut donc utiliser Double.isNaN()


                        // Un cas sans racine en plus.
                        ++nbCasSansRacine;
                        break;
                }
            } catch (NulCoefException ex) {
                // Si on arrive ici, c'est qu'il y a eu une exception NulCoefException (évidemment).
                // Encore faut-il que ce soit pour une bonne raison. Je vérifie.
                assertEquals(String.format("Cas N°%d (Coef a nul) => L'exception  NulCoefException a été lancée : a devrait être null", i), 0, a);
                
                        
                // Un cas de coef A nul en plus.
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

                switch (e.getRootsCount()) {
                    case 1:
                        assertEquals(String.format("Cas N°%d (racine double) %s => x1 = %f ", i, e, e.getX1()), 0, a * e.getX1() * e.getX1() + b * e.getX1() + c, EPSILON);
                        ++nbCasRacineDouble;
                        break;
                    case 2:
                        assertNotEquals(e.getX1(), e.getX2());
                        assertEquals(String.format("Cas N°%d (2 racines) %s => x1 = %f ", i, e, e.getX1()), 0, a * e.getX1() * e.getX1() + b * e.getX1() + c, EPSILON);
                        assertEquals(String.format("Cas N°%d (2 racines) %s => x2 = %f ", i, e, e.getX2()), 0, a * e.getX2() * e.getX2() + b * e.getX2() + c, EPSILON);
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
