/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package double_vision;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maillot
 */
public class Personne {

    private String nom;
    private int age;
    private boolean masculin;

    public boolean isMasculin() {
        return masculin;
    }

    private Personne pere, mere;
    private List<Personne> enfants = new ArrayList<>();

    public Personne(String nom, int age, boolean masculin) {
        this.nom = nom;
        this.age = age;
        this.masculin = masculin;
    }

    @Override
    public String toString() {
        if (age < 0) {
            return "";
        }
        return nom + " (" + age + " an" + (age > 1 ? "s" : "") + ")";
    }

    public int getAge() {
        return age;
    }

    public String getNom() {
        return nom;
    }

    public Personne getPere() {
        return pere;
    }

    public Personne getMere() {
        return mere;
    }

    public int getNbEnfants() {
        return enfants.size();
    }

    public Personne getEnfant(int i) {
        return enfants.get(i);
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setParent(Personne p) {
        if (p.isMasculin()) {
            this.pere = pere;
            if (!pere.aPourEnfant(this)) {
                pere.ajouterEnfant(this);
            }
        } else {
            this.mere = mere;
            if (!mere.aPourEnfant(this)) {
                mere.ajouterEnfant(this);
            }
        }
    }

    public boolean aPourEnfant(Personne p) {
        return enfants.contains(p);
    }

    public void ajouterEnfant(Personne p) {
        if (!aPourEnfant(p)) {
            enfants.add(p);
            if (isMasculin()) {
                p.pere = this;
            } else {
                p.mere = this;
            } 
        }
    }

    public void retirerEnfant(Personne p) {
        enfants.remove(p);
    }
}
