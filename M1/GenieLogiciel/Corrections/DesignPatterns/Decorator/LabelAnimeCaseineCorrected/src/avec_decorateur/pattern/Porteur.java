package avec_decorateur.pattern;

import javax.swing.JLabel;

/*
Classe "Porteur" car elle porte un JLabel
*/
public class Porteur extends Animateur {
    
    private final JLabel label;

    // TODO déclarer un ou des attributs si nécesaire
    public Porteur(JLabel label) {
        this.label = label;        
    }
    
    // TODO écrire un constructeur qui définit le JLabel à animer

    @Override
    public void animer() {
    }

    @Override
    public JLabel getJLabel() {
        return label;
    }
}
