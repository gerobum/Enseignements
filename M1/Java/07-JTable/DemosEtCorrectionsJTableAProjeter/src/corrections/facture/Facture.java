/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package corrections.facture;    

import javax.swing.JFrame;

/**
 *
 * @author Yvan
 */
public class Facture extends JFrame {
    public Facture() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TestFacture newContentPane = new TestFacture();
        newContentPane.setOpaque(false); //content panes must be opaque
        setContentPane(newContentPane);
        pack();
        setVisible(true);
    }
}
