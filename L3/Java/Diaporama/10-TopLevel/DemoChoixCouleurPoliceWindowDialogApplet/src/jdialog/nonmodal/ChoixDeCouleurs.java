/*
 * ChoixDeCouleurs.java
 */
package jdialog.nonmodal;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ChoixDeCouleurs extends JDialog {

    private JPanel sud;
    private JSlider rouge, vert, bleu;
    private final Container panneau;
    private final JLabel message;

    public ChoixDeCouleurs(Container panneau, JLabel message) {
        this.panneau = panneau;
        this.message = message;

        miseEnPlaceDesComposant();
        miseEnPlaceDesEcouteurs();
        miseEnPlaceUI();
    }

    private void miseEnPlaceDesComposant() {

        sud = new JPanel();

        rouge = new JSlider(0, 255, this.panneau.getBackground().getRed());
        vert = new JSlider(0, 255, this.panneau.getBackground().getGreen());
        bleu = new JSlider(0, 255, this.panneau.getBackground().getBlue());

        rouge.setBackground(Color.RED);
        vert.setBackground(Color.GREEN);
        bleu.setBackground(Color.BLUE);

        getContentPane().add(sud, "South");

        sud.setLayout(new GridLayout(3, 1));

        sud.add(rouge);
        sud.add(vert);
        sud.add(bleu);
    }

    private void miseEnPlaceDesEcouteurs() {

        ChangeListener action = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                panneau.setBackground(new Color(ChoixDeCouleurs.this.rouge.getValue(), ChoixDeCouleurs.this.vert.getValue(), ChoixDeCouleurs.this.bleu.getValue()));
            }
        };

        rouge.addChangeListener(action);
        vert.addChangeListener(action);
        bleu.addChangeListener(action);
    }

    private void miseEnPlaceUI() {
        pack();
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

}
