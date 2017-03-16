package exercices.boutons.interruption;

import java.awt.Color;
import javax.swing.JButton;

/** 
 * Modification du bouton pour que le bouton s'arr�te (resp. r�d�marre) quand on clique
 * s'il �tait en marche (resp. arr�t�).
 * 
 * Par ailleur, on met fin d�finitevement au clignotement si la touche alt
 * est enfonc�e au moment du clic.
 */
public class BoutonClignotantAvecArretEtDepart extends JButton {    
    private Color allume, eteint; 

    public BoutonClignotantAvecArretEtDepart(String texte, Color allum�, Color eteint) {
        super(texte);
        this.allume = allum�;
        this.eteint = eteint;
        setBackground(allum�);
    }
}
