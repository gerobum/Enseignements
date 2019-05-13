/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avec_decorateur.pattern;

import java.lang.reflect.Modifier;
import javax.swing.JLabel;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertTrue;

/**
 *
 * @author yvan
 */
public class PorteurTest extends TestCase {
    
    public PorteurTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
         System.out.println("La classe est-elle abstraite");
        assertTrue("La classe Animateur doit rester abstraite", 
                Modifier.isAbstract(Animateur.class.getModifiers()));
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of animer method, of class Porteur.
     */
    public void testAnimer() {
        System.out.println("animer");
        Porteur instance = null;
        instance.animer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJLabel method, of class Porteur.
     */
    public void testGetJLabel() {
        System.out.println("getJLabel");
        Porteur instance = null;
        JLabel expResult = null;
        JLabel result = instance.getJLabel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
