package exercices.boutons.interruption;

import java.awt.Color;
import javax.swing.JButton;


/**
 * Modifiez la classe pour que le bouton clignote jusqu'a ce qu'il soit cliqué
 * @author maillot
 */
public class BoutonClignotantAvecArret extends JButton {    
    private Color allume, eteint;
    public BoutonClignotantAvecArret(String texte, Color allume, Color eteint) {
        super(texte);
        this.allume = allume;
        this.eteint = eteint;
        setBackground(allume);
    }
}
