/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salle;

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
public class TableTest {

    public TableTest() {
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

    @Test
    public void testSomeMethod() {
        // TODO review the generated test code and remove the default call to fail.
        for (int i = 2; i < 4; ++i) {
            try {
                Table t = new Table(i);
                Philosophe phi = t.get(0);
                Philosophe voisin = phi.getGauche().getGauche();
                int j = 1;
                for (; j < i; ++j) {
                    assertFalse(i+" phi, j = " + j + " : " +phi + "<>" + voisin, phi == voisin);
                    voisin = voisin.getGauche().getGauche();
                }
                assertTrue(i+" phi, j = " + j + " : " +phi + "<>" + voisin, phi == voisin);
            } catch (Exception ex) {
                fail(ex.toString());
            }
        }
    }

}
