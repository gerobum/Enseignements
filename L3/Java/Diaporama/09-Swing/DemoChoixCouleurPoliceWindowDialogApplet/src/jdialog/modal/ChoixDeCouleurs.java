/*
 * ChoixDeCouleurs.java
 */
package jdialog.modal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ChoixDeCouleurs extends JDialog {

    private JPanel centre, sud;
    private JSlider rouge, vert, bleu;
    private Color couleurChoisie;

    public Color couleurChoisie() {
        return couleurChoisie;
    }

    public ChoixDeCouleurs() {
        miseEnPage();
        miseEnPlaceDesEcouteurs();

        //setVisible(true);
        // setModal(true); Obsolète
        setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
        pack();

        setDefaultCloseOperation(HIDE_ON_CLOSE);

    }

    private void miseEnPage() {

        centre = new JPanel();

        centre.setBackground(Color.BLACK);

        centre.setPreferredSize(new Dimension(200, 200));

        sud = new JPanel();

        rouge = new JSlider(0, 255, 0);
        vert = new JSlider(0, 225, 0);
        bleu = new JSlider(0, 255, 0);

        rouge.setBackground(Color.RED);
        vert.setBackground(Color.GREEN);
        bleu.setBackground(Color.BLUE);

        couleurChoisie = new Color(0, 0, 0);

        getContentPane().add(centre, "Center");
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
                couleurChoisie = new Color(ChoixDeCouleurs.this.rouge.getValue(), ChoixDeCouleurs.this.vert.getValue(), ChoixDeCouleurs.this.bleu.getValue());
                centre.setBackground(couleurChoisie);
            }
        };

        rouge.addChangeListener(action);
        vert.addChangeListener(action);
        bleu.addChangeListener(action);
    }

}
