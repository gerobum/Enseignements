/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arbre_binaire_modele;

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
        filsGauche = null;
        filsDroit = null;
        valeur = null;
    }
    
    public NoeudBinaire(T valeur) {
        this.valeur = valeur;
        filsGauche = vide;
        filsDroit = vide;
    }

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
    
    public boolean estVide() {
        return filsDroit == null && filsGauche == null;
    }
    
    @Override
    public String toString() {
        if (valeur == null)
            return "";
        else
            return valeur.toString();
    }
}
