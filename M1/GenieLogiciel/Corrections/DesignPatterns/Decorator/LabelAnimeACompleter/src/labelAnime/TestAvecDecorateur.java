package labelAnime;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class TestAvecDecorateur extends JFrame {
    private JLabel tourne, clignote, tourneEtClignote;

    public TestAvecDecorateur() {
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
     }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TestAvecDecorateur();
            }
        });

    }
}
