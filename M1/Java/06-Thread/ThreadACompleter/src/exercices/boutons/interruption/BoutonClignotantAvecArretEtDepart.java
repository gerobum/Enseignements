package exercices.boutons.interruption;

import java.awt.Color;
import javax.swing.JButton;

/** 
 * Modification du bouton pour que le bouton s'arrête (resp. rédémarre) quand on clique
 * s'il était en marche (resp. arrêté).
 * 
 * Par ailleur, on met fin définitevement au clignotement si la touche alt
 * est enfoncée au moment du clic.
 */
public class BoutonClignotantAvecArretEtDepart extends JButton {    
    private Color allume, eteint; 

    public BoutonClignotantAvecArretEtDepart(String texte, Color allumé, Color eteint) {
        super(texte);
        this.allume = allumé;
        this.eteint = eteint;
        setBackground(allumé);
    }
}
