package lanceurs;

import avec_decorateur.swing.FenetreAvecPleinDAnimations;
import javax.swing.SwingUtilities;

public class LanceurAvecDecorateur {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FenetreAvecPleinDAnimations("Plein d'animations");
            }
        });        
    }
}
