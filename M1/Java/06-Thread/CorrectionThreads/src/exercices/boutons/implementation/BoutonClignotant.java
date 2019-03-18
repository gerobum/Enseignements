package exercices.boutons.implementation;

import java.awt.Color;
import javax.swing.JButton;

/**
 * Clignotement du bouton par "impl�mentation de Runnable"
 */
public class BoutonClignotant extends JButton implements Runnable {
    
    private final Color on, off;
    
    public BoutonClignotant(String texte, Color on, Color off) {
        super(texte);
        this.on = on;
        this.off = off;
        initUI();
    }

    /*
    Puisque BoutonClignotant impl�mente Runnable, il faut red�finir run
    */
    @Override
    public void run() {
        while(true) {
           try {
                if (getBackground() == on) {
                    setBackground(off);
                    Thread.sleep(100);
                } else {
                    setBackground(on);
                    Thread.sleep(500);
                }
            } catch (InterruptedException ex) {
            }
        }
    }  

    private void initUI() {        
        setBackground(on);
        // Pour lancer le clignotement � la construction
        new Thread(this).start();
        setFont(getFont().deriveFont(50f)); 
    }
}
