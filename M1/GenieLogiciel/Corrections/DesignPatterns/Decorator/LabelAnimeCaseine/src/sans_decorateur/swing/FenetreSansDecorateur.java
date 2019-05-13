package sans_decorateur.swing;

import java.awt.Font;
import sans_decorateur.JLabelClignotant;
import sans_decorateur.JLabelTournant;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import sans_decorateur.JLabelClignotantTournant;

public class FenetreSansDecorateur extends JFrame {

    public FenetreSansDecorateur(String message) {
        super(message);
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
}
