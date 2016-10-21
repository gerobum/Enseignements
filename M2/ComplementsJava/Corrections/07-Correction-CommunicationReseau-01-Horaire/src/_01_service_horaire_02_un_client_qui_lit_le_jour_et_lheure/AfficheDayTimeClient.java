/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package _01_service_horaire_02_un_client_qui_lit_le_jour_et_lheure;

import javax.swing.JFrame;

/**
 *
 * @author maillot
 */
public class AfficheDayTimeClient extends JFrame {
    public AfficheDayTimeClient() {
        getContentPane().add(new DayTimeClient());
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AfficheDayTimeClient();
    }

}
