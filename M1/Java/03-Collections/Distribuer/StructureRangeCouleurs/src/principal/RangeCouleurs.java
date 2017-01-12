package principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RangeCouleurs extends JFrame {

    private final JTextField nomDeLaCouleur;
    private final JPanel sud, centre;
    private final JTextField rouge;
    private final JTextField vert;
    private final JTextField bleu;

    public RangeCouleurs() {
        super("Fenêtre pour ranger les couleurs");

        nomDeLaCouleur = new JTextField("Blanc");

        sud = new JPanel();
        centre = new JPanel();
        centre.setPreferredSize(new Dimension(200, 200));
        centre.setBackground(Color.white);

        rouge = new JTextField("000");
        vert = new JTextField("000");
        bleu = new JTextField("000");
        rouge.setPreferredSize(new Dimension(50, 20));
        vert.setPreferredSize(new Dimension(50, 20));
        bleu.setPreferredSize(new Dimension(50, 20));

        sud.add(rouge);
        sud.add(vert);
        sud.add(bleu);

        generateUI();
        putListeners();
    }

    private void generateUI() {
        getContentPane().setLayout(new BorderLayout());

        getContentPane().add(nomDeLaCouleur, "North");
        getContentPane().add(centre, "Center");
        getContentPane().add(sud, "South");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    public static void main(String[] args) {
        new RangeCouleurs();
    }

    private void putListeners() {
        // A vous de jouer...
    }

}
