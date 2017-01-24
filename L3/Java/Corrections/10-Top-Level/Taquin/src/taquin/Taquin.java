package taquin;

import exception.DimensionTaquinIncorrecte;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import piece.PièceTaquin;

/**
 * Pour jouer au taquin. Le jeu est réglable au sens où on peut fixer le nombre
 * de lignes et de colonnes dans une certaine limite.
 *
 * @author Yvan
 */
public class Taquin extends JPanel {

    public static final int MINIMUM_LIGNES = 2;
    public static final int MAXIMUM_LIGNES = 10;
    public static final int MINIMUM_COLONNES = 2;
    public static final int MAXIMUM_COLONNES = 10;
    // Les pièces du taquin sont en fait dérivées de JButton
    // voir la classe pièce.PièceTaquin
    private PièceTaquin[][] pièce;
    private PièceTaquin trou;
    private final Random mélange;
    private BufferedImage image;

    private void clic(PièceTaquin source) {
        if ((source.ligne - 1 == trou.ligne && source.colonne == trou.colonne)
                || (source.ligne + 1 == trou.ligne && source.colonne == trou.colonne)
                || (source.ligne == trou.ligne && source.colonne + 1 == trou.colonne)
                || (source.ligne == trou.ligne && source.colonne - 1 == trou.colonne)) {
            déplacer(source);

        }
    }

    private void positionnerLesPièces(int nl, int nc) {
        int k = 0;

        pièce = new PièceTaquin[nl][nc];

        for (int i = 0; i < nl; ++i) {
            for (int j = 0; j < nc; ++j) {
                pièce[i][j] = new PièceTaquin(i, j, Integer.toString(k));
                add(pièce[i][j]);
                pièce[i][j].addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        PièceTaquin source = (PièceTaquin) actionEvent.getSource();

                        if ((source.ligne - 1 == trou.ligne && source.colonne == trou.colonne)
                                || (source.ligne + 1 == trou.ligne && source.colonne == trou.colonne)
                                || (source.ligne == trou.ligne && source.colonne + 1 == trou.colonne)
                                || (source.ligne == trou.ligne && source.colonne - 1 == trou.colonne)) {
                            déplacer(source);

                        }
                    }
                });
                ++k;
            }
        }
        trou = pièce[0][0];
        trou.setVisible(false);
    }

    public void mélanger() {
        int c;
        int l;
        int base = pièce.length * pièce[0].length * 5;
        int nfois = mélange.nextInt(base) + base;
        for (int i = 0; i < nfois; ++i) {
            // Déplacement par rapport au trou
            // Soit en ligne ou en colonne
            if (mélange.nextBoolean()) { // En ligne
                c = trou.colonne;
                if (mélange.nextBoolean()) { // Positif sauf si on déborde
                    l = trou.ligne + 1;
                    if (l >= pièce.length) {
                        l = trou.ligne - 1;
                    }
                } else { // Négatif sauf si on déborde
                    l = trou.ligne - 1;
                    if (l < 0) {
                        l = trou.ligne + 1;
                    }
                }
            } else { // En colonne
                l = trou.ligne;
                if (mélange.nextBoolean()) { // Positif sauf si on déborde
                    c = trou.colonne + 1;
                    if (c >= pièce[0].length) {
                        c = trou.colonne - 1;
                    }
                } else { // Négatif sauf si on déborde
                    c = trou.colonne - 1;
                    if (c < 0) {
                        c = trou.colonne + 1;
                    }
                }
            }
            //pièce[l][c].doClick();            
            clic(pièce[l][c]);
        }
    }

    public void setImage(BufferedImage bufferedImage) {
        image = bufferedImage;
        int x;
        int y = 0;
        int dy = image.getHeight() / pièce.length;
        int dx = image.getWidth() / pièce[0].length;
        for (int i = 0; i < pièce.length; ++i) {
            x = 0;
            for (int j = 0; j < pièce[0].length; ++j) {
                pièce[i][j].setIcon(new ImageIcon(image.getSubimage(x, y, dx, dy)));
                pièce[i][j].setText(null);
                pièce[i][j].setPreferredSize(new Dimension(dx, dy));
                x += dx;
            }
            y += dy;
        }
    }

    /**
     * Déplace la pièce source vers le trou et inversement
     *
     */
    private void déplacer(PièceTaquin source) {
        
        // Echanger les textes
        trou.setText(source.getText());
        source.setText("0");
        // Echanger les icones
        Icon tmp = trou.getIcon();
        trou.setIcon(source.getIcon());
        source.setIcon(tmp);
        // Rétablir l'invisibilité du trou
        trou.setVisible(true);
        source.setVisible(false);

        trou = source;
    }

    /**
     * Créer un jeu dont les dimensions sont fournies
     *
     * @param nl Le nombre de lignes demandé
     * @param nc le nombre de colonnes demandé
     * @throws exception.DimensionTaquinIncorrecte
     *
     */
    public Taquin(int nl, int nc) throws DimensionTaquinIncorrecte {
        mélange = new Random();

        if (nl < MINIMUM_LIGNES || nl > MAXIMUM_LIGNES || nc < MINIMUM_COLONNES || nc > MAXIMUM_COLONNES) {
            throw new DimensionTaquinIncorrecte(nl, nc);
        } else {
            setLayout(new GridLayout(nl, nc));
            positionnerLesPièces(nl, nc);
        }
    }
}
