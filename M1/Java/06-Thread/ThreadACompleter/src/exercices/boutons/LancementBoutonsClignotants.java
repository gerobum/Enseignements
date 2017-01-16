/*
 * Frame.java
 *
 * Created on 4 mars 2008, 17:40
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package exercices.boutons;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Yvan
 */
public class LancementBoutonsClignotants extends JFrame {
    
    public LancementBoutonsClignotants() {
      
    getContentPane().setLayout(new GridLayout(0, 1));
        getContentPane().add(new exercices.boutons.implementation.BoutonClignotant("Bling Bling !!!", new Color(0, 255, 0), new Color(0, 100, 0)));
        getContentPane().add(new exercices.boutons.interruption.BoutonClignotantAvecArret("Je m'arrête de clignoter et je m'éteinds quand on clique", new Color(0, 0, 255), new Color(0, 0, 100)));
        getContentPane().add(new exercices.boutons.derivation.BoutonClignotant("Bling Bling !!!", new Color(255, 0, 0), new Color(100, 0, 0)));
        getContentPane().add(new exercices.boutons.interruption.BoutonClignotantAvecArretEtDepart("Clic and Go", Color.YELLOW, Color.YELLOW.darker().darker()));
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        new LancementBoutonsClignotants();
        try {
            Thread.sleep(60000);
        } catch (InterruptedException ex) {
            System.out.println("L'attente s'est terminee plus tot que prevu");
        }
    }
    
}
