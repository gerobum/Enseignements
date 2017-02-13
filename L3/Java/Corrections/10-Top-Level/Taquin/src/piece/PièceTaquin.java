package piece;

import javax.swing.JButton;

/**
 * Une pi�ce du taquin pourrait n'�tre qu'un simple bouton (JButton). 
 * Pour des raisons de commodit�s, c'est un bouton sp�cialis� dans le sens
 * o� sa position dans le tableau est dans ses attributs sous la forme ligne et colonne 
 * @author Yvan
 */
public class Pi�ceTaquin extends JButton {
    public final int ligne;
    public final int colonne;
    
    public Pi�ceTaquin(int ligne, int colonne, String texte) {
        super(texte);
        this.ligne = ligne;
        this.colonne = colonne;
    }   
    
    @Override
    public String toString() {
        return ligne + ", " + colonne;
    }
}
