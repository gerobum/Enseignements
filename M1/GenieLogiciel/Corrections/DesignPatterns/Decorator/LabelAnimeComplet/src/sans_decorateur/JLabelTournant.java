
package sans_decorateur;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

public class JLabelTournant extends JLabel {

    public JLabelTournant(String string) {
        this(string, JLabel.LEFT);
    }

    public JLabelTournant(String string, int alignement) {
        super(string, alignement);
        new Thread(new Runnable() {

            public void run() {
                while (true) {
                    setText(getText().substring(1) + getText().substring(0, 1));
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(JLabelTournant.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }).start();
    }

}
