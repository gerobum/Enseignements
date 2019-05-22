package salle;

/**
 *
 * @author maillot
 */
public class Baguette {

    private Philosophe gauche, droite;
    private boolean disponible;

    public Philosophe getGauche() {
        return gauche;
    }

    public void setGauche(Philosophe gauche) {
        this.gauche = gauche;
    }

    public Philosophe getDroite() {
        return droite;
    }

    public void setDroite(Philosophe droit) {
        this.droite = droit;
    }

    public boolean isDisponible() {
        return disponible;
    }
    
    public synchronized void prendre() {
        disponible = false;
    }
    
    public void poser() {
        disponible = true;
    }
    
    
}
