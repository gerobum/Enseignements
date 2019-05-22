
package sans_decorateur;

import java.awt.Color;
import javax.swing.JLabel;

/**
 *
 * @author Yvan
 */
public class JLabelClignotant extends JLabel {

    public JLabelClignotant(String string) {
        this(string, JLabel.LEFT);
    }

    public JLabelClignotant(String string, int alignement) {
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
    }
}
