/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corrections.notes_ATM;

import javax.swing.JFrame;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Yvan
 */
public class FrameNotes extends JFrame {

    public FrameNotes(String titre, AbstractTableModel modele) {
        super(titre);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if ("Partiel".equals(titre)) {
            setContentPane(new PanneauNotes(2, modele)); 
            setLocation(0, 0);
        } else if ("Final".equals(titre)) {
            setContentPane(new PanneauNotes(1, modele));
            setLocation(0, 250);
        } else {
            setContentPane(new PanneauNotes(-1, modele));
            setLocation(0, 500);
        }
        pack();
        setVisible(true);

    }
}
