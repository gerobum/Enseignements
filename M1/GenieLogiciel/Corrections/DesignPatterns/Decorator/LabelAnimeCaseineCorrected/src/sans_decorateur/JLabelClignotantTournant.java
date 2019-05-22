package sans_decorateur;

import java.awt.Color;
import javax.swing.JLabel;

public class JLabelClignotantTournant extends JLabel {

    public JLabelClignotantTournant(String string) {
        this(string, JLabel.LEFT);
    }

    public JLabelClignotantTournant(String string, int alignement) {
        super(string, alignement);
        // Le thread qui fait clignoter
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Color couleur = getForeground();
                    while (true) {
                        if (getForeground() == couleur) {
                            setForeground(getBackground());
                            Thread.sleep(200);

                        } else {
                            setForeground(couleur);
                            Thread.sleep(500);
                        }
                    }
                } catch (InterruptedException ex) {                    
                }
            }
        }).start();
        // Le thread qui fait tourner
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    setText(getText().substring(1) + getText().substring(0, 1));
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        
                    }
                }
            }
        }).start();
    }

}
