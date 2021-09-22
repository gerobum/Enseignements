// #### 5/5

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arbre;

import java.io.Serializable;

/**
 *
 * @author maillot
 */

// #### 0.5/0.5 (Q 3)
// #### 1/1 (Q 3.3)
public class NoeudBinaire<T extends Serializable> implements Serializable {
    private NoeudBinaire<T> filsGauche;
    private NoeudBinaire<T> filsDroit;
    private T valeur;
    
    // #### 0.5/0.5 (Q 3.1.1)
    public static final NoeudBinaire vide = new NoeudBinaire();
    // #### 0.5/0.5 (Q 3.1.2)
    public NoeudBinaire() {
        filsDroit = null;
        filsGauche = null;
        valeur = null;
    }

    // #### 0.5/0.5 (Q 3.1.3)
    public NoeudBinaire(T valeur) {
        this.valeur = valeur;
        filsDroit = vide;
        filsGauche = vide;
    }

    // #### 0.5/0.5 (Q 3.2.1 et 2)
    public NoeudBinaire<T> getFilsDroit() {
        return filsDroit;
    }

    public void setFilsDroit(NoeudBinaire<T> filsDroit) {
        this.filsDroit = filsDroit;
    }

    public NoeudBinaire<T> getFilsGauche() {
        return filsGauche;
    }

    public void setFilsGauche(NoeudBinaire<T> filsGauche) {
        this.filsGauche = filsGauche;
    }

    public T getValeur() {
        return valeur;
    }
    // #### 0.5/0.5 (Q 3.2.3)
    public boolean estVide() {
        return valeur == null;
    }

    // #### 1/1 (Q 3.2.4)
    @Override
    public String toString() {
        if (valeur == null)
            return "";
        else 
            return valeur.toString();
    }
    
    
}
