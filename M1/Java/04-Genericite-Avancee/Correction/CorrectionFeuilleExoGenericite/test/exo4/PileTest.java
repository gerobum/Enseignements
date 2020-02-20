
package exo4;

import java.awt.Color;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author maillot
 */
public class PileTest {
    
    public PileTest() {
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
     * Teste toutes les méthodes de la pile
     */
    @Test
    public void testPile() {        
        Pile<Double> p = new Pile<>();
        assertTrue(p.vide());
        p.empiler(1.0);
        assertFalse(p.vide());
        assertEquals(1.0, p.depiler(), 0.0);
        assertTrue(p.vide());
        Double[] td = new Double[1000];
        for(int i = 0; i < 1000; ++i) {
            p.empiler(i/2.0);
            td[i] = i/2.0;
        }
        Double[] r = p.toArray();
        
        assertArrayEquals(td, r);
        
        while(!p.vide()) {
            p.depiler();
        }
        
        assertTrue(p.vide());
        
        Color[] tc = {Color.BLUE, Color.WHITE, Color.RED};
        
        Pile<Color> pc = new Pile<>(tc);
        assertEquals(Color.RED, pc.depiler());
        assertEquals(Color.WHITE, pc.depiler());
        assertEquals(Color.BLUE, pc.depiler());
    }

    /**
     * Test of empiler method, of class Pile.
     */
    @Test
    public void testEmpiler() {    
    }

    /**
     * Test of depiler method, of class Pile.
     */
    @Test
    public void testDepiler() {
    }

    /**
     * Test of vide method, of class Pile.
     */
    @Test
    public void testVide() {
    }

    /**
     * Test of toArray method, of class Pile.
     */
    @Test
    public void testToArray() {
    }
    
}
