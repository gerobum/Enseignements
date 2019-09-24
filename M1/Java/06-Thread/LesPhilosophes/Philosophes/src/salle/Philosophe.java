package salle;

import java.awt.Color;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author maillot
 */
public final class Philosophe extends JPanel implements Runnable {
    
    private static Random random = new Random();

    private static final Color COULEUR_POUR_MANGER = new Color(91, 191, 211);

    private static final Color COULEUR_POUR_PENSER = new Color(225, 128, 83);

    private boolean entrainDeManger = false;
    private Baguette gauche, droite;
    public final int n;

    public Philosophe(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        while(true) {
            try {
                entrainDeManger = false;
                System.out.println(this);
                Thread.sleep(random.nextInt(20)+10);
                if (gauche.isDisponible()) {
                    gauche.prendre();
                }
                if (droite.isDisponible()) {
                    droite.prendre();
                }
                System.out.println(this);
                entrainDeManger = true;
                System.out.println(this);
                Thread.sleep(random.nextInt(2000)+1000);
                gauche.poser();
                droite.poser();
            } catch (InterruptedException ex) {
                Logger.getLogger(Philosophe.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Baguette getGauche() {
        return gauche;
    }

    public void setGauche(Baguette gauche) {
        this.gauche = gauche;

    }

    public Baguette getDroite() {
        return droite;
    }

    public void setDroite(Baguette droite) {
        this.droite = droite;
    }

    @Override
    public String toString() {
        return n + " : " + (entrainDeManger ? "mange" : "pense") ;
    }
    
    public void start() {
        new Thread(this).start();
    }

}
