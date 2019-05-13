/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avec_decorateur.pattern;

import java.lang.reflect.Modifier;
import javax.swing.JLabel;
import junit.framework.TestCase;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author yvan
 */
public class AnimateurTest extends TestCase {
    
    @Test
    public void isAbstract() {
        System.out.println("La classe est-elle abstraite");
        assertTrue("La classe Animateur doit rester abstraite", 
                Modifier.isAbstract(Animateur.class.getModifiers()));
    }

    
}
