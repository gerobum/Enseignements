/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exo1;

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
public class AutoCompteurTest {
    
    public AutoCompteurTest() {
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
     * Test of getNbInstances method, of class AutoCompteur.
     */
    @Test
    public void testGetNbInstances() {
        System.out.println("getNbInstances");
        assertEquals(0, AutoCompteur.getNbInstances());
        for(int i = 1; i < 10; ++i) {
            new AutoCompteur();
            assertEquals(i, AutoCompteur.getNbInstances());
        }
    }
    
}
