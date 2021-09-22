/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package corrections.facture;    

import corrections.facture.article.Articles;
import javax.swing.JFrame;

/**
 *
 * @author Yvan
 */
public class Facture extends JFrame {
    public Facture() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanelFacture newContentPane = new JPanelFacture(Articles.baguette, 
                Articles.croissant, 
                Articles.meringue, 
                Articles.painLong, 
                Articles.petitPain, 
                Articles.rien);
        newContentPane.setOpaque(false); //content panes must be opaque
        setContentPane(newContentPane);
        pack();
        setVisible(true);
    }
}
