package sudoku;

import junit.framework.TestCase;

/**
 *
 * @author yvan
 */
public class GrilleTest extends TestCase {

    public GrilleTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getLigne method, of class Grille.
     */
    public void testGetLigne() {
        System.out.println("getLigne");

    }

    public void testConstructeurDeGrille() {
        System.out.println("Test constructeurde grille");
        Grille g = new Grille();
        for (int l = 0; l < 9; ++l) {
            for (int c = 0; c < 9; ++c) {
                assertEquals(g.getLigne(l).getCase(c), g.getColonne(c).getCase(l));
            }
        }
    }

    /**
     * Test of getColonne method, of class Grille.
     */
    public void testGetColonne() {
        System.out.println("getColonne");
       
    }

    /**
     * Test of getCarr� method, of class Grille.
     */
    public void testGetCarr�() {
        System.out.println("getCarr\u00e9");
       
    }

}
