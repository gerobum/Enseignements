package exercices.boutons.derivation;

import java.awt.Color;
import javax.swing.JButton;

public class BoutonClignotant extends JButton {    
    private Color allume, eteint;    
    private class Clignoteur extends Thread {
        @Override
        public void run() {
            while(true) {
               try {
                    if (getBackground() == allume) {
                        setBackground(eteint);
                        Thread.sleep(50);
                    } else {
                        setBackground(allume);
                        Thread.sleep(700);
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }       
    }
    public BoutonClignotant(String texte, Color allum�, Color eteint) {
        super(texte);
        this.allume = allum�;
        this.eteint = eteint;
        setBackground(allum�);
        new Clignoteur().start();
        setFont(getFont().deriveFont(50f));
    }
}
