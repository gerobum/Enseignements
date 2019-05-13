/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Colors {

    private final Map<Integer, Color> back = new HashMap<>();
    private final Map<Integer, Color> fore = new HashMap<>();
    private final Map<Integer, Font> font = new HashMap<>();
    private final Font fontDef = new Font("Comics", Font.BOLD, 50);
    private final Color colorDef = new Color(205, 193, 180);

    public Colors() {
        back.put(0, colorDef);
        fore.put(0, colorDef);
        font.put(0, fontDef);
        back.put(2, new Color(238, 228, 218));
        fore.put(2, new Color(199, 110, 101));
        font.put(2, fontDef);
        back.put(4, new Color(237, 224, 200));
        fore.put(4, new Color(119, 110, 101));
        font.put(4, fontDef);
        back.put(8, new Color(242, 177, 121));
        fore.put(8, new Color(249, 246, 242));
        font.put(8, fontDef);
        back.put(16, new Color(245, 149, 9));
        fore.put(16, new Color(249, 246, 242));
        font.put(16, fontDef);
        back.put(32, new Color(246, 124, 95));
        fore.put(32, new Color(249, 246, 242));
        font.put(32, fontDef);
        back.put(64, new Color(246, 94, 59));
        fore.put(64, new Color(249, 246, 242));
        font.put(64, fontDef);
        back.put(128, new Color(237, 207, 114));
        fore.put(128, new Color(249, 246, 242));
        font.put(128, fontDef.deriveFont(40f));
        back.put(256, new Color(237, 204, 97));
        fore.put(256, new Color(249, 246, 242));
        font.put(256, fontDef.deriveFont(40f));
        back.put(512, new Color(237, 200, 80));
        fore.put(512, new Color(249, 246, 242));
        font.put(512, fontDef.deriveFont(40f));
        back.put(1024, new Color(237, 197, 63));
        fore.put(1024, new Color(249, 246, 242));
        font.put(1024, fontDef.deriveFont(40f));
        back.put(2048, Color.BLACK);
        fore.put(2048, new Color(249, 246, 242));
        font.put(2048, fontDef.deriveFont(40f));
    }

    public Color getBack(int v) {
        Color r = back.get(v);
        return r != null ? r : colorDef;
    }

    public Color getFore(int v) {
        Color r = fore.get(v);
        return r != null ? r : Color.white;
    }

    public Font getFont(int v) {
        Font r = font.get(v);
        return r != null ? r : fontDef;
    }
}

/**
 * Fenêtre d'affichage du jeu 2048
 * @author yvan
 */
public class Frame extends JFrame {

    private static final ResourceBundle messages = ResourceBundle.getBundle("frame/messages");
    private final Font fontDef;
    private final Color colorDef = new Color(205, 193, 180);

    private final JLabel[][] C;
    private final Colors colors = new Colors();
    private char rs = 0;
    private KeyListener keyListener;
    private JLabel message;
    private JLabel score;

    public Frame() {
        C = new JLabel[4][4];
        fontDef = new Font("Comics", Font.BOLD, 30);
        initUI();
        config();
        initListeners();
    }

    /**
     * Change l'affichage de la fenêtre en fonction de la grille passée en 
     * paramètre
     * @param grille la grille à afficher contient des puissances de 2
     */
    public void setGrille(int[][] grille) {

        for (int l = 0; l < C.length; ++l) {
            for (int c = 0; c < C[l].length; ++c) {
                C[l][c].setBackground(colors.getBack(grille[l][c]));
                C[l][c].setForeground(colors.getFore(grille[l][c]));
                C[l][c].setFont(colors.getFont(grille[l][c]));
                C[l][c].setText(grille[l][c] + "");
            }
        }
    }

    private synchronized void typing() {
        notifyAll();
    }

    /**
     * Méthode bloquante qui attend la frappe d'une flèche (haut, bas, gauche, droite).
     * @return 'H' (pour haut), 'B', 'G', 'D'
     */
    public synchronized char saisie() {
        removeKeyListener(keyListener);
        addKeyListener(keyListener);
        while (rs == 0) {
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
        char srs = rs;
        rs = 0;
        return srs;
    }

    private void config() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setResizable(false);
    }

    private void initUI() {
        JPanel centre = new JPanel();
        centre.setLayout(new GridLayout(4, 4, 25, 25));
        centre.setPreferredSize(new Dimension(600, 600));
        for (int l = 0; l < C.length; ++l) {
            for (int c = 0; c < C[l].length; ++c) {
                C[l][c] = new JLabel("    ", JLabel.CENTER);
                C[l][c].setOpaque(true);
            }
        }
        for (int l = 0; l < C.length; ++l) {
            for (int c = 0; c < C[l].length; ++c) {
                centre.add(C[l][c]);
            }
        }
        message = new JLabel(messages.getString("ESSAIE D'OBTENIR 2048"), JLabel.CENTER);
        message.setOpaque(true);
        message.setFont(fontDef);
        message.setForeground(colorDef.darker().darker());
        message.setPreferredSize(new Dimension(0, 100));
        
        score = new JLabel("Score : ", JLabel.CENTER);
        score.setForeground(colorDef.darker().darker());
        score.setPreferredSize(new Dimension(0, 100));
        score.setFont(fontDef.deriveFont(Font.BOLD, 40f));
        
        getContentPane().add(centre, "Center");
        getContentPane().add(message, "South");
        getContentPane().add(score, "North");
    }
    
    public void setScore(int score) {
       this.score.setText("Score : " + score);
    }

    private void initListeners() {
        keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //System.out.println(e);
            }

            @Override
            public synchronized void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case 104:
                    case 38:
                    case 224:
                        removeKeyListener(keyListener);
                        rs = 'H';
                        typing();
                        break;
                    case 98:
                    case 40:
                    case 225:
                        removeKeyListener(keyListener);
                        rs = 'B';
                        typing();
                        break;
                    case 100:
                    case 37:
                    case 226:
                        removeKeyListener(keyListener);
                        rs = 'G';
                        typing();
                        break;
                    case 102:
                    case 39:
                    case 227:
                        removeKeyListener(keyListener);
                        rs = 'D';
                        typing();
                        break;
                    default:
                        rs = 0;

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //System.out.println(e);
            }
        };
        addKeyListener(keyListener);
    }
    
    /**
     * Méthode à appeler en cas victoire
     */
    public void afficheVictoire() {
        message.setBackground(new Color(177, 242, 121));
        message.setForeground(new Color(246, 249, 242));
        message.setText(messages.getString("BRAVO !!!"));
    }
    
    /**
     * Méthode à appeler en cas de défaite
     */
    public void afficheDefaite() {
        message.setBackground(new Color(242, 177, 121));
        message.setForeground(new Color(249, 246, 242));
        message.setText(messages.getString("DOMMAGE !!!"));
    }
    

    private void afficheEnCours() {
        message.setOpaque(true);
        message.setFont(fontDef);
        message.setForeground(colorDef.darker().darker());
        message.setText(messages.getString("ESSAIE D'OBTENIR 2048"));
    }

}
