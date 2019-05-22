package avec_decorateur.pattern;

import java.awt.Color;

public class Clignoteur extends Decorateur {

    public Clignoteur(Animateur composant) {
        super(composant);
    }
    // TODO déclarer un constructeur adapté
    
    @Override
    public void animer() {
        // TODO redéfinir correctement cette méthode
        super.animer();
        faireClignoter();
    }  
    
    private void faireClignoter() {
        // TODO faire clignoter le JLabel. S'inspirer de sans_decorateur.JLabelClignotant
    // Le thread qui fait clignoter
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Color couleur = getJLabel().getForeground();
                    while (true) {
                        if (getJLabel().getForeground() == couleur) {
                            getJLabel().setForeground(getJLabel().getBackground());
                            Thread.sleep(200);
                        } else {
                            getJLabel().setForeground(couleur);
                            Thread.sleep(500);
                        }
                    }
                } catch (InterruptedException ex) {                   
                }
            }
        }).start();
    }

}
