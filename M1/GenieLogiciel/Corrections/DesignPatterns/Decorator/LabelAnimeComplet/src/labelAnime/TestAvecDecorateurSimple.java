package labelAnime;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class TestAvecDecorateurSimple extends JFrame {
    private JLabel tourne, clignote, tourneEtClignote;

    public TestAvecDecorateurSimple() {
        super("Test");
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
        new Clignoteur(new AnimateurConcret(clignote)).animer();
        new Tourneur(new AnimateurConcret(tourne)).animer();
        new Clignoteur(new Tourneur(new AnimateurConcret(tourneEtClignote))).animer();

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TestAvecDecorateurSimple();
            }
        });

    }
}
