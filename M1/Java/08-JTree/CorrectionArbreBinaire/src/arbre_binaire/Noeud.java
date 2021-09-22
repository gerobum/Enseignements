/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arbre_binaire;

/**
 *
 * @author maillot
 */
public class Noeud<T> {

    private Noeud gauche, droite;
    private T valeur;
    public static final Noeud vide = new Noeud();

    private Noeud() {
        this.valeur = null;
        gauche = null;
        droite = null;
    }

    public Noeud(T valeur) {
        this.valeur = valeur;
        gauche = vide;
        droite = vide;
    }

    public void setDroite(Noeud droite) {
        this.droite = droite;
    }

    public void setGauche(Noeud gauche) {
        this.gauche = gauche;
    }

    public Noeud getDroite() {
        return droite;
    }

    public Noeud getGauche() {
        return gauche;
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
