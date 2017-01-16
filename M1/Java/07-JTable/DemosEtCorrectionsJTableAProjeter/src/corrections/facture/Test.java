/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package corrections.facture;



/**
 *
 * @author Maillot
 */
public class Test {
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Facture();
            }
        });
    }
}
