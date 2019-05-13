package avec_decorateur.pattern;

import javax.swing.JLabel;

public class Decorateur extends Animateur {

    // TODO déclarer un ou des attributs si nécessaire
    private final Animateur composant;

    public Decorateur(Animateur composant) {
        // TODO redéfinir correctement ce constructeur
        this.composant = composant;
    }

    @Override
    public void animer() {
        composant.animer();
    }

    @Override
    public JLabel getJLabel() {
        return composant.getJLabel();
    }
}
