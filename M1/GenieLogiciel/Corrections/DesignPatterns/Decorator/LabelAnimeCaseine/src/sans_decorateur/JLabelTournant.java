
package sans_decorateur;

import javax.swing.JLabel;

/**
 *
 * @author Yvan
 */
public class JLabelTournant extends JLabel {

    public JLabelTournant(String string) {
        this(string, JLabel.LEFT);
    }

    public JLabelTournant(String string, int alignement) {
        super(string, alignement);
        new Thread(new Runnable() {
            @Override
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
