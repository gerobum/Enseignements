package exo2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author yvan
 */
public class BoutonVolupteux extends JFrame {

    private JButton bouton;

    public BoutonVolupteux() {
        miseEnPlaceDesComposants();
        miseEnPlacesDesEcouteurs();
        miseEnPlaceUI();

    }

    private void miseEnPlaceDesComposants() {
        bouton = new JButton("Cliquez moi !");
        getContentPane().add(bouton);
    }

    private void miseEnPlacesDesEcouteurs() {
        bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bouton.setText(bouton.getText() + " Encore !");
                pack();
            }
        });
    }

    private void miseEnPlaceUI() {
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new BoutonVolupteux();
    }
}
