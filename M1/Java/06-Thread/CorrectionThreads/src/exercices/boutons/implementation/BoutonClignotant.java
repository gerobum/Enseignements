package exercices.boutons.implementation;

import java.awt.Color;
import javax.swing.JButton;

public class BoutonClignotant extends JButton implements Runnable {
    
    private final Color on, off;
    
    public BoutonClignotant(String texte, Color on, Color off) {
        super(texte);
        this.on = on;
        this.off = off;
        initUI();
    }

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
        new Thread(this).start();
        setFont(getFont().deriveFont(50f)); 
    }
}
