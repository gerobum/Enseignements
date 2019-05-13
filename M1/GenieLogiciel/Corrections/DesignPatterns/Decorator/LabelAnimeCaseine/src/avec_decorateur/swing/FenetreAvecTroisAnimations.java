package avec_decorateur.swing;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FenetreAvecTroisAnimations extends JFrame {
    private JLabel tourne, clignote, tourneEtClignote;

    public FenetreAvecTroisAnimations(String message) {
        super(message);
        setUI();
        animation();
    }
    
    private void setUI() {
        
        tourne = new JLabel("Tourne ", JLabel.CENTER);
        clignote = new JLabel("Clignote", JLabel.CENTER);
        tourneEtClignote = new JLabel("Tourne et Clignote ", JLabel.CENTER);

        Font font = new Font("Courrier", Font.BOLD, 25);
        tourne.setFont(font);
        clignote.setFont(font);
        tourneEtClignote.setFont(font);

        getContentPane().setLayout(new GridLayout(0, 1));

        getContentPane().add(tourne);
        getContentPane().add(clignote);
        getContentPane().add(tourneEtClignote);

        pack();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void animation() {
        // TODO faire clignoter le JLabel clignote

        // TODO faire tourner le JLabel tourne

        // TODO faire tourner et clignoter le JLabel tourneEtClignote
    }
}
