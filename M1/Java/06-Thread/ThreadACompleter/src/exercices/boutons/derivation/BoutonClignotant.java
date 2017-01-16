package exercices.boutons.derivation;

import java.awt.Color;
import javax.swing.JButton;

/**
 * Modifiez la classe pour que le bouton clignote par derivation de Thread
 * @author maillot
 */
public class BoutonClignotant extends JButton {    
    private Color allume, eteint;
    public BoutonClignotant(String texte, Color allume, Color eteint) {
        super(texte);
        this.allume = allume;
        this.eteint = eteint;
        setBackground(allume);
    }
}
