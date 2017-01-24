package testeur;

import java.awt.Font;
import sansdecorateur.JLabelClignotant;
import sansdecorateur.JLabelTournant;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import sansdecorateur.JLabelClignotantTournant;

public class TestSansDecorateur extends JFrame {

    public TestSansDecorateur() {
        super("Test");
        setUI();
    }

    private void setUI() {

        getContentPane().setLayout(new GridLayout(0, 1));
        JLabelTournant tourne = new JLabelTournant("Tourne ", JLabel.CENTER);
        JLabelClignotant clignote = new JLabelClignotant("Clignote", JLabel.CENTER);
        JLabelClignotantTournant tourneEtClignote = new JLabelClignotantTournant("tourne et clignote ", JLabel.CENTER);

        Font font = new Font("Courrier", Font.BOLD, 25);
        tourne.setFont(font);
        clignote.setFont(font);
        tourneEtClignote.setFont(font);

        getContentPane().add(tourne);
        getContentPane().add(clignote);
        getContentPane().add(tourneEtClignote);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    public static void main(String[] args) {
        new TestSansDecorateur();
    }
}
