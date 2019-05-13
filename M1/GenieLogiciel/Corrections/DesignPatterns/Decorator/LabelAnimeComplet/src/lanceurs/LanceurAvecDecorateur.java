package labelAnime;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class TestAvecDecorateur extends JFrame {
        private JLabel tourne ;
        private JLabel clignote;
        private JLabel tourneEtClignote;
        private JLabel arcEnCiel;
        private JLabel vague;
        private JLabel vagueArcEnCiel;
        private JLabel calme;
        private JLabel agité;

    public TestAvecDecorateur() {
        super("Test");        setUI();
        animation();
    }
    
    private void setUI() {
        
        tourne = new JLabel("Tourne ", JLabel.CENTER);
        clignote = new JLabel("Clignote", JLabel.CENTER);
        tourneEtClignote = new JLabel("Tourne et Clignote ", JLabel.CENTER);
        arcEnCiel = new JLabel("Arc en ciel", JLabel.CENTER);
        vague = new JLabel("Vague", JLabel.CENTER);
        vagueArcEnCiel = new JLabel("Vague arc en ciel ", JLabel.CENTER);
        calme = new JLabel("Très calme", JLabel.CENTER);
        agité = new JLabel("très agité", JLabel.CENTER);
        
        Font font = new Font("Courrier", Font.BOLD, 25);
        tourne.setFont(font);
        clignote.setFont(font);
        tourneEtClignote.setFont(font);
        arcEnCiel.setFont(font);
        vague.setFont(font);
        vagueArcEnCiel.setFont(font);
        calme.setFont(font);
        agité.setFont(font);
        

              
        getContentPane().setLayout(new GridLayout(0, 1));
        
        getContentPane().add(tourne);
        getContentPane().add(clignote);
        getContentPane().add(tourneEtClignote);
        getContentPane().add(arcEnCiel);
        getContentPane().add(vague);
        getContentPane().add(vagueArcEnCiel);
        getContentPane().add(calme);
        getContentPane().add(agité);
        
        pack();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);  

    }
    
    private void animation() {        
        new Clignoteur(new Porteur(clignote)).animer();    
        new Tourneur(new Porteur(tourne)).animer();
        new Clignoteur(new Tourneur(new Porteur(tourneEtClignote))).animer();
        new Vague(new ArcEnCiel(new Porteur(vagueArcEnCiel))).animer();
        new ArcEnCiel(new Porteur(arcEnCiel)).animer();
        new Vague(new Porteur(vague)).animer();
        new Tourneur(new Tourneur(new Clignoteur(new ArcEnCiel(new Vague(new Porteur(agité)))))).animer();
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
