package exercices.boutons.implémentation;

import java.awt.Color;
import javax.swing.JButton;

public class BoutonClignotant extends JButton implements Runnable {
    
    private Color allume, eteint;
    
    public BoutonClignotant(String texte, Color allume, Color eteint) {
        super(texte);
        this.allume = allume;
        this.eteint = eteint;
        setBackground(allume);
        new Thread(this).start();
        setFont(getFont().deriveFont(50f));
    }

    @Override
    public void run() {
        while(true) {
           try {
                if (getBackground() == allume) {
                    setBackground(eteint);
                    Thread.sleep(100);
                } else {
                    setBackground(allume);
                    Thread.sleep(500);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }  
}
