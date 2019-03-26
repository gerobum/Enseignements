package mastermind;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class FrameMasterMind extends JFrame {

    private static Random random = new Random();

    private int nbCoups;
    private int nbCouleurs;
    private int taille;

    public FrameMasterMind(int nbCoups, int nbCouleurs, int taille) {
        this.nbCoups = nbCoups;
        this.nbCouleurs = nbCouleurs;
        this.taille = taille;

        getContentPane().add(new PanneauPropositions(), "Center");
        getContentPane().add(new PanneauCouleurs(), "East");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();

    }
    
    private class BoutonClou extends JButton {
        public final ImageIcon icon;

        public BoutonClou(ImageIcon icon) {
            super(icon);
            this.icon = icon;
        }
        
        
        
    }

    private class PanneauCouleurs extends JPanel {

        private final ImageIcon clouBlanc = new ImageIcon(getClass().getResource("images/clouBlanc.png"));
        private final ImageIcon clouBleu = new ImageIcon(getClass().getResource("images/clouBleu.png"));
        private final ImageIcon clouCyan = new ImageIcon(getClass().getResource("images/clouCyan.png"));
        private final ImageIcon clouJaune = new ImageIcon(getClass().getResource("images/clouJaune.png"));
        private final ImageIcon clouNoir = new ImageIcon(getClass().getResource("images/clouNoir.png"));
        private final ImageIcon clouOrange = new ImageIcon(getClass().getResource("images/clouOrange.png"));
        private final ImageIcon clouRouge = new ImageIcon(getClass().getResource("images/clouRouge.png"));
        private final ImageIcon clouVert = new ImageIcon(getClass().getResource("images/clouVert.png"));
        private final ImageIcon clouViolet = new ImageIcon(getClass().getResource("images/clouViolet.png"));
        private final ImageIcon[] clou = {clouBlanc, clouBleu, clouCyan, clouJaune, clouNoir, clouOrange, clouRouge, clouVert, clouViolet};
        private ButtonGroup groupe = new ButtonGroup();
        private JButton[] boutonsClou;
        

        public PanneauCouleurs() {
            setLayout(new GridLayout(0, 1));
            boutonsClou = new JButton[nbCouleurs];
            for (int i = 0; i < nbCouleurs; i++) {
                boutonsClou[i] = new JButton(clou[i]);
                add(boutonsClou[i]);
               boutonsClou[i].setBackground(Color.white);
                groupe.add(boutonsClou[i]);
            }
            //boutonsClou[0].setSelected(true);
        }

    }

    private class PanneauPropositions extends JPanel {

        private final ImageIcon teteBlanc = new ImageIcon(getClass().getResource("images/teteBlanche.png"));
        private final ImageIcon teteBleu = new ImageIcon(getClass().getResource("images/teteBleue.png"));
        private final ImageIcon teteCyan = new ImageIcon(getClass().getResource("images/teteCyan.png"));
        private final ImageIcon teteJaune = new ImageIcon(getClass().getResource("images/teteJaune.png"));
        private final ImageIcon teteNoir = new ImageIcon(getClass().getResource("images/teteNoire.png"));
        private final ImageIcon teteOrange = new ImageIcon(getClass().getResource("images/teteOrange.png"));
        private final ImageIcon teteRouge = new ImageIcon(getClass().getResource("images/teteRouge.png"));
        private final ImageIcon teteVert = new ImageIcon(getClass().getResource("images/teteVerte.png"));
        private final ImageIcon teteViolet = new ImageIcon(getClass().getResource("images/teteViolette.png"));
        private final ImageIcon trouVide = new ImageIcon(getClass().getResource("images/trouVide.png"));
        private final ImageIcon[] tete = {teteBlanc, teteBleu, teteCyan, teteJaune, teteNoir, teteOrange, teteRouge, teteVert, teteViolet};
        private JLabel[][] labels;

        public PanneauPropositions() {
            setLayout(new GridLayout(nbCoups, taille));
            labels = new JLabel[nbCoups][taille];
            for (int i = 0; i < nbCoups; i++) {
                for (int j = 0; j < taille; j++) {

                    labels[i][j] = new JLabel(trouVide);
                    add(labels[i][j]);

                }
            }
            for (int i = 0; i < taille; i++) {
                labels[nbCoups-1][i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("CLIC");
                }

            });
            }
            
        }

    }

    public static void main(String[] args) {
        new FrameMasterMind(10, 6, 4);
    }

}
