package correction_exercices.exo6_2.sortie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Une fenêtre qui est associée à un flux de données (DataInputStream) défini à
 * la construction.
 *
 * À chaque appui sur le bouton suivant, une donnée de type long est lue dans le
 * flux de données et affichée dans un label en hexadécimal sur 16x4 bits.
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class FrameAffichage extends JFrame {

    private JLabel valeur;
    private JButton suivant;
    private final DataInputStream in;

    public FrameAffichage(DataInputStream din) {
        super("Affichage");
        in = din;

        generateUI();
        putListeners();

    }

    private void generateUI() {
        valeur = new JLabel("000000000000000000000", JLabel.CENTER);
        valeur.setFont(valeur.getFont().deriveFont(30f));
        getContentPane().add(valeur, "Center");
        suivant = new JButton("Suivant");
        suivant.setFont(suivant.getFont().deriveFont(30f));
        getContentPane().add(suivant, "South");
        // Placement et autres fioritures
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void putListeners() {
        suivant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    valeur.setText(String.format("%016X", in.readLong()));
                } catch (IOException ex) {
                }
            }
        });
    }

}
