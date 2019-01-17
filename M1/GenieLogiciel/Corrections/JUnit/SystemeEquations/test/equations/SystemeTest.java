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
public class SystemeTest {

    private Systeme s;
    private static Random random = new Random();
    
    private final double[][][] td = {
        {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},},};

    public SystemeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        s = new Systeme(3);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void setGet() {
        for (int l = 0; l < td[0].length; ++l) {
            for (int c = 0; c < td[0][l].length; ++c) {
                s.set(l, l, td[0][l][c]);
            }
        }
        
        for (int l = 0; l < td[0].length; ++l) {
            for (int c = 0; c < td[0][l].length; ++c) {
                assertEquals(s.get(l, c), td[0][l][c], 1e10);                
            }
        }
        
        for(int i = 0; i < 1000; i++) {
            int l = random.nextInt(3);
            int c = random.nextInt(4);
            double v = random.nextDouble();
            s.set(l, c, v);
            assertEquals(v, s.get(l, c), 0.0);
        }
    }    
}
