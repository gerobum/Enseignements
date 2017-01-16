package exercices.boutons.derivation;

import java.awt.Color;
import javax.swing.JButton;

public class BoutonClignotant extends JButton {    
    private final Color on, off;    

    private class Clignoteur extends Thread {
        @Override
        public void run() {
            while(true) {
               try {
                    if (getBackground() == on) {
                        setBackground(off);
                        Thread.sleep(50);
                    } else {
                        setBackground(on);
                        Thread.sleep(70);
                    }
                } catch (InterruptedException ex) {
                }
            }
        }       
    }
    public BoutonClignotant(String texte, Color on, Color off) {
        super(texte);
        this.on = on;
        this.off = off;
        initUI();
    }
    
    
    private void initUI() {        
        setBackground(on);
        new Clignoteur().start();
        setFont(getFont().deriveFont(50f));
    }
}
