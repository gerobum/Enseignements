package piece;

import javax.swing.JButton;

/**
 * Une pièce du taquin pourrait n'être qu'un simple bouton (JButton). 
 * Pour des raisons de commodités, c'est un bouton spécialisé dans le sens
 * où sa position dans le tableau est dans ses attributs sous la forme ligne et colonne 
 * @author Yvan
 */
public class PièceTaquin extends JButton {
    public final int ligne;
    public final int colonne;
    
    public PièceTaquin(int ligne, int colonne, String texte) {
        super(texte);
        this.ligne = ligne;
        this.colonne = colonne;
    }   
    
    @Override
    public String toString() {
        return ligne + ", " + colonne;
    }
}
