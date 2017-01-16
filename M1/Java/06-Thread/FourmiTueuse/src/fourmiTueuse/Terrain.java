/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fourmiTueuse;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Le terrain de jeu des fourmis. Il peut contenir au départ de 80x50 fourmis.
 * La dimension du terrain en X et en Y 
 * Une casier y sera représentée par un rectangle plein dont la dimension est
 * modifiable : (tailleFourmiX x tailleFourmiY).
 * 
 * @author maillot
 */
public class Terrain extends JPanel {
    private Casier[][] terrain;
    private int maxX = 80;
    private int maxY = 50;
    private int tailleFourmiX = 8;
    private int tailleFourmiY = 8;
    private JLabel quantite;    
    private int xcanon = -1;
    
    public Terrain(JLabel quantite) {
        this.quantite = quantite;
        terrain = new Casier[maxX][maxY];
        setPreferredSize(new Dimension(maxX*tailleFourmiX, maxY*tailleFourmiY));
        setBackground(Color.WHITE);
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    lancerMissile(((int)e.getX()/tailleFourmiX));
                    ///Terrain.this.xcanon = e.getX();
                    repaint();
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    ajouterValise(((int)e.getX()/tailleFourmiX), (int)e.getY()/tailleFourmiY);
                    repaint();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                Terrain.this.xcanon = e.getX()/tailleFourmiX;

                repaint();
            }
        });
    } 
    /**
     * Permet de tenir compte des redimensionnement de la fenêtre.
     * Le calcul du nombre de fourmis en X et Y se fait en fonction
     * de la dimension de la fenêtre et de la taille des fourmis en X et en Y.
     * 
     */
    private void changementDeDimension() {
        Casier[][] nq = new Casier[maxX][maxY];
        int ni = terrain.length < nq.length ? terrain.length : nq.length;
        int nj = terrain[0].length < nq[0].length ? terrain[0].length : nq[0].length;
        for(int i = 0; i < ni; i++)
            System.arraycopy(terrain[i], 0, nq[i], 0, nj);
        terrain = nq;
        setPreferredSize(new Dimension(maxX*tailleFourmiX, maxY*tailleFourmiY));        
    }

    /**
     * Permet de modifier le nombre de fourmis en Y
     * @param maxY nombre de fourmis en Y
     */
    public void setMaxY(int maxY) {
        if (maxY < 0)
            this.maxY = 0;
        else
            this.maxY = maxY;
        changementDeDimension();        
    }
    /**
     * Permet de modifier le nombre de fourmis en X
     * @param maxX nombre de fourmis en X
     */
    public void setMaxX(int maxX) {
        if (maxX < 0)
            this.maxX = 0;
        else
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
    public void set(int x, int y, Casier casier) {
        terrain[x][y] = casier;      
    }
    public Casier get(int x, int y) {
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
        if (xcanon > -1) {
            g.setColor(Color.RED);
            g.fillRect(xcanon*tailleFourmiX, (maxY-1)*tailleFourmiY, tailleFourmiX, tailleFourmiY);
        }
    }
    public void ajouterFourmiNoire() {
        new Fourmi(this, Color.BLACK);
    }
    public void ajouterFourmiNoire(int x, int y) {
        new Fourmi(this, x, y, Color.BLACK);
    }
    public void ajouterFourmi() {
        new Fourmi(this);
    }
    public void ajouterFourmi(int x, int y) {
        new Fourmi(this, x, y);
    }
    public void ajouterMur(int x, int y) {
        terrain[x][y] = new Mur();
    }
    public void ajouterValise(int x, int y) {
        terrain[x][y] = new Canon();
    }
    public void lancerMissile(int x) {
        terrain[x][maxY-1] = new Missile(this, x, maxY-1);
    }
}
