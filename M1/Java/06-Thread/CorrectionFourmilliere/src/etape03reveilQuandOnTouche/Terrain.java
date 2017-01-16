package etape03reveilQuandOnTouche;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Le terrain de jeu des fourmis. Il peut contenir au départ de 80x50 fourmis.
 * La dimension du terrain en X et en Y 
 * Une fourmi y sera représentée par un rectangle plein dont la dimension est
 * modifiable : (tailleFourmiX x tailleFourmiY).
 * 
 * @author maillot
 */
public class Terrain extends JPanel {
    private Fourmi[][] terrain;
    private int maxX = 80;
    private int maxY = 50;
    private int tailleFourmiX = 8;
    private int tailleFourmiY = 8;
    private JLabel quantite;    
    
    public Terrain(JLabel quantite) {
        this.quantite = quantite;
        terrain = new Fourmi[maxX][maxY];
        setPreferredSize(new Dimension(maxX*tailleFourmiX, maxY*tailleFourmiY));
        setBackground(Color.WHITE);
    } 
    /**
     * Permet de tenir compte des redimensionnement de la fenêtre.
     * Le calcul du nombre de fourmis en X et Y se fait en fonction
     * de la dimension de la fenêtre et de la taille des fourmis en X et en Y.
     * 
     */
    private void changementDeDimension() {
        Fourmi[][] nq = new Fourmi[maxX][maxY];
        int ni = terrain.length < nq.length ? terrain.length : nq.length;
        int nj = terrain[0].length < nq[0].length ? terrain[0].length : nq[0].length;
        for(int i = 0; i < ni; i++)
            for(int j = 0; j < nj; j++) {
                nq[i][j] = terrain[i][j];
            }
        terrain = nq;
        setPreferredSize(new Dimension(maxX*tailleFourmiX, maxY*tailleFourmiY));         
    }

    /**
     * Permet de modifier le nombre de fourmis en Y
     * @param maxY nombre de fourmis en Y
     */
    public void setMaxY(int maxY) {
        this.maxY = maxY;
        changementDeDimension();        
    }
    /**
     * Permet de modifier le nombre de fourmis en X
     * @param maxX nombre de fourmis en X
     */
    public void setMaxX(int maxX) {
        this.maxX = maxX;
        changementDeDimension();        
    }
    /**
     * Retourne le nombre de fourmis en Y
     * @return le nombre de fourmis en Y
     */
    public int getMaxY() {
        return maxY;
    }    
    /**
     * Retourne le nombre de fourmis en X
     * @return le nombre de fourmis en X
     */
    public int getMaxX() {
        return maxX;
    }
    /**
     * 
     * @return
     */
    public int getTailleFourmiX() {
        return tailleFourmiX;
    }

    public void setTailleFourmiX(int tailleFourmiX) {
        this.tailleFourmiX = tailleFourmiX;
        changementDeDimension();
    }

    public int getTailleFourmiY() {
        return tailleFourmiY;
    }

    public void setTailleFourmiY(int tailleFourmiY) {
        this.tailleFourmiY = tailleFourmiY;
        changementDeDimension();
    }
    public void set(int x, int y, Fourmi fourmi) {
        try {
            terrain[x][y] = fourmi;
        } catch(ArrayIndexOutOfBoundsException ao) {
        }
    }
    public Fourmi get(int x, int y) {
        try {
            return terrain[x][y];
        } catch(ArrayIndexOutOfBoundsException ao) {
            return null; 
        }
    }
    @Override
    public void paint(java.awt.Graphics g) {
        super.paint(g);
        int n = 0;
        for(int x = 0; x < maxX; x++)
            for(int y = 0; y < maxY; y++) 
                if (terrain[x][y] != null) {
                    g.setColor(terrain[x][y].getCouleur());
                    g.fillRect(x*tailleFourmiX, y*tailleFourmiY, tailleFourmiX, tailleFourmiY);
                    n++;
                }
        quantite.setText(""+n);
    }
    public void ajouterFourmi() {
        new Fourmi(this);
    }
    public void ajouterFourmi(int x, int y) {
        new Fourmi(this, x, y);
    }
}
