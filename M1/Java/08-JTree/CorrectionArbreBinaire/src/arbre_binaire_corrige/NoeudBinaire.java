/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arbre_binaire_corrige;

import java.io.Serializable;

/**
 *
 * @author maillot
 */
public class NoeudBinaire<T extends Serializable> implements Serializable {
    private NoeudBinaire<T> filsGauche, filsDroit;
    private T valeur;
    public static final NoeudBinaire vide = new NoeudBinaire();

    private NoeudBinaire() {
        this.valeur = null;
        filsGauche = null;
        filsDroit = null;
    }

    public NoeudBinaire(T valeur) {
        this.valeur = valeur;
        filsGauche = vide;
        filsDroit = vide;
    }

    public void setFilsDroit(NoeudBinaire<T> filsDroit) {
        this.filsDroit = filsDroit;
    }

    public void setFilsGauche(NoeudBinaire<T> filsGauche) {
        this.filsGauche = filsGauche;
    }

    public NoeudBinaire<T> getFilsDroit() {
        return filsDroit;
    }

    public NoeudBinaire<T> getFilsGauche() {
        return filsGauche;
    }

    
    public boolean estVide() {
        return valeur == null;
    }

    public T getValeur() {
        return valeur;
    }

    @Override
    public String toString() {
        if (valeur == null) {
            return "";
        } else {
            return valeur.toString();
        }
    }
}
