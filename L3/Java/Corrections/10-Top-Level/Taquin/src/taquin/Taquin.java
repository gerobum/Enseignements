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
import piece.Pi�ceTaquin;

/**
 * Pour jouer au taquin. Le jeu est r�glable au sens o� on peut fixer le nombre
 * de lignes et de colonnes dans une certaine limite.
 *
 * @author Yvan
 */
public class Taquin extends JPanel {

    public static final int MINIMUM_LIGNES = 2;
    public static final int MAXIMUM_LIGNES = 10;
    public static final int MINIMUM_COLONNES = 2;
    public static final int MAXIMUM_COLONNES = 10;
    // Les pi�ces du taquin sont en fait d�riv�es de JButton
    // voir la classe pi�ce.Pi�ceTaquin
    private Pi�ceTaquin[][] pi�ce;
    private Pi�ceTaquin trou;
    private final Random m�lange;
    private BufferedImage image;

    private void clic(Pi�ceTaquin source) {
        if ((source.ligne - 1 == trou.ligne && source.colonne == trou.colonne)
                || (source.ligne + 1 == trou.ligne && source.colonne == trou.colonne)
                || (source.ligne == trou.ligne && source.colonne + 1 == trou.colonne)
                || (source.ligne == trou.ligne && source.colonne - 1 == trou.colonne)) {
            d�placer(source);

        }
    }

    private void positionnerLesPi�ces(int nl, int nc) {
        int k = 0;

        pi�ce = new Pi�ceTaquin[nl][nc];

        for (int i = 0; i < nl; ++i) {
            for (int j = 0; j < nc; ++j) {
                pi�ce[i][j] = new Pi�ceTaquin(i, j, Integer.toString(k));
                add(pi�ce[i][j]);
                pi�ce[i][j].addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        Pi�ceTaquin source = (Pi�ceTaquin) actionEvent.getSource();

                        if ((source.ligne - 1 == trou.ligne && source.colonne == trou.colonne)
                                || (source.ligne + 1 == trou.ligne && source.colonne == trou.colonne)
                                || (source.ligne == trou.ligne && source.colonne + 1 == trou.colonne)
                                || (source.ligne == trou.ligne && source.colonne - 1 == trou.colonne)) {
                            d�placer(source);

                        }
                    }
                });
                ++k;
            }
        }
        trou = pi�ce[0][0];
        trou.setVisible(false);
    }

    public void m�langer() {
        int c;
        int l;
        int base = pi�ce.length * pi�ce[0].length * 5;
        int nfois = m�lange.nextInt(base) + base;
        for (int i = 0; i < nfois; ++i) {
            // D�placement par rapport au trou
            // Soit en ligne ou en colonne
            if (m�lange.nextBoolean()) { // En ligne
                c = trou.colonne;
                if (m�lange.nextBoolean()) { // Positif sauf si on d�borde
                    l = trou.ligne + 1;
                    if (l >= pi�ce.length) {
                        l = trou.ligne - 1;
                    }
                } else { // N�gatif sauf si on d�borde
                    l = trou.ligne - 1;
                    if (l < 0) {
                        l = trou.ligne + 1;
                    }
                }
            } else { // En colonne
                l = trou.ligne;
                if (m�lange.nextBoolean()) { // Positif sauf si on d�borde
                    c = trou.colonne + 1;
                    if (c >= pi�ce[0].length) {
                        c = trou.colonne - 1;
                    }
                } else { // N�gatif sauf si on d�borde
                    c = trou.colonne - 1;
                    if (c < 0) {
                        c = trou.colonne + 1;
                    }
                }
            }
            //pi�ce[l][c].doClick();            
            clic(pi�ce[l][c]);
        }
    }

    public void setImage(BufferedImage bufferedImage) {
        image = bufferedImage;
        int x;
        int y = 0;
        int dy = image.getHeight() / pi�ce.length;
        int dx = image.getWidth() / pi�ce[0].length;
        for (int i = 0; i < pi�ce.length; ++i) {
            x = 0;
            for (int j = 0; j < pi�ce[0].length; ++j) {
                pi�ce[i][j].setIcon(new ImageIcon(image.getSubimage(x, y, dx, dy)));
                pi�ce[i][j].setText(null);
                pi�ce[i][j].setPreferredSize(new Dimension(dx, dy));
                x += dx;
            }
            y += dy;
        }
    }

    /**
     * D�place la pi�ce source vers le trou et inversement
     *
     */
    private void d�placer(Pi�ceTaquin source) {
        
        // Echanger les textes
        trou.setText(source.getText());
        source.setText("0");
        // Echanger les icones
        Icon tmp = trou.getIcon();
        trou.setIcon(source.getIcon());
        source.setIcon(tmp);
        // R�tablir l'invisibilit� du trou
        trou.setVisible(true);
        source.setVisible(false);

        trou = source;
    }

    /**
     * Cr�er un jeu dont les dimensions sont fournies
     *
     * @param nl Le nombre de lignes demand�
     * @param nc le nombre de colonnes demand�
     * @throws exception.DimensionTaquinIncorrecte
     *
     */
    public Taquin(int nl, int nc) throws DimensionTaquinIncorrecte {
        m�lange = new Random();

        if (nl < MINIMUM_LIGNES || nl > MAXIMUM_LIGNES || nc < MINIMUM_COLONNES || nc > MAXIMUM_COLONNES) {
            throw new DimensionTaquinIncorrecte(nl, nc);
        } else {
            setLayout(new GridLayout(nl, nc));
            positionnerLesPi�ces(nl, nc);
        }
    }
}
