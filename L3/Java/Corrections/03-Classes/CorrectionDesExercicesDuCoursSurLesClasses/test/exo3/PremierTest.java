/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exo3;

import exo3.Premier;
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
public class PremierTest {

    private static final boolean[] EST_PREMIER = new boolean[Short.MAX_VALUE];
    /*
     Ce tableau sera utilisé pour savoir si un nombre est premier. Le booléen à l’indice p dans
     est_premier est vrai si et seulement si p est premier. Par exemple est_premier[12] est false
     alors que est_premier[97] est true.*/
    private static final short[] PREMIER;

    /* Ce tableau contient les premiers nombres premiers inférieurs à Short.MAX_VALUE.
     */
    // Il faut remplir les tableaux premier et est_premier.
    // Ce serait possible "à la main" mais fastidieux.
    // C'est aussi possible en appliquant l'algorithme du crible d'Eratostene.
    // Mais il faut le faire dans un bloc d'initialisation statique
    static {
        for (int i = 2; i < EST_PREMIER.length; ++i) {
            EST_PREMIER[i] = true;
        }
        {   // Ce bloc ne sert qu'à isoler la variable i
            // et pouvoir la réutiliser par la suite
            int i = 2;

            while (i * i < EST_PREMIER.length) {
                for (int j = i * i; j < EST_PREMIER.length; j += i) {
                    EST_PREMIER[j] = false;
                }
                i++;
            }
        }
        int nbpremiers = 0;
        for (int i = 0; i < EST_PREMIER.length; ++i) {
            if (EST_PREMIER[i]) {
                nbpremiers++;
            }
        }
        PREMIER = new short[nbpremiers];
        int k = 0;
        for (short i = 0; i < EST_PREMIER.length; ++i) {
            if (EST_PREMIER[i]) {
                PREMIER[k++] = i;
            }
        }
    }

    public PremierTest() {
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
     * Test of estPremier method, of class Premier.
     */
    @Test
    public void testEstPremier() {
        System.out.println("estPremier");
        try {
            short i = 0;
            while (i < EST_PREMIER.length && EST_PREMIER[i] == Premier.estPremier(i)) {
                ++i;
            }
            assertTrue("Le tableau EST_PREMIER n'est pas correct", i == EST_PREMIER.length);
        } catch (ArrayIndexOutOfBoundsException ex) {
            fail("Le tableau EST_PREMIER ne fait pas la bonne taille");
        } catch (NullPointerException ex) {
            fail("Il semble qu'un objet n'est pas instancié");
        }

    }

    /**
     * Test of premier method, of class Premier.
     */
    @Test
    public void testPremier() {
        System.out.println("premier");
        try {
            short i = 0;
            while (i < PREMIER.length && PREMIER[i] == Premier.premier(i)) {
                ++i;
            }
            assertTrue("Le tableau des premiers nombres premiers n'est pas correct", i == PREMIER.length);
        } catch (ArrayIndexOutOfBoundsException ex) {
            fail("Le tableau des premiers nombres premiers ne fait pas la bonne taille");
        } catch (NullPointerException ex) {
            fail("Il semble qu'un objet n'est pas instancié");
        }
    }

    /**
     * Test of nbPremiers method, of class Premier.
     */
    @Test
    public void testNbPremiers() {
        System.out.println("NbPremiers");
        try {
            assertEquals("Le nombre de nombres premiers n'est pas exact", PREMIER.length, Premier.nbPremiers());
        } catch (NullPointerException ex) {
            fail("Il semble qu'un objet n'est pas instancié");
        }
    }

}
