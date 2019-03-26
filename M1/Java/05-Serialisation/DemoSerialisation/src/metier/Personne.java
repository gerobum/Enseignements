/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import java.io.ObjectStreamField;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author yvan
 */
public class Personne implements Serializable {
// Sérialisables car String l’est

    public final String nom;
    public final String prénom;
    private int numeroDeSecu;
    private LocalDate naissance;
    private transient int âge; 
    private static int population = 0;
    public final boolean monsieur;
    private Personne parent;
    private Personne enfant;
    

    private static final ObjectStreamField[] serialPersistentFields = {
        new ObjectStreamField("prénom", String.class),
        new ObjectStreamField("nom", String.class),
        new ObjectStreamField("naissance", LocalDate.class),
        new ObjectStreamField("âge", int.class),
        new ObjectStreamField("monsieur", boolean.class),
        new ObjectStreamField("enfant", Personne.class),
        new ObjectStreamField("parent", Personne.class),
    };


    public Personne(String prenom, String nom, LocalDate naissance, boolean monsieur) {
        this.nom = nom;
        this.prénom = prenom;
        this.naissance = naissance;
        population++;
        numeroDeSecu = population;
        this.monsieur = monsieur;

        calculerAge();

        parent = null;
        enfant = null;
    }

    public Personne(String prenom, String nom, int jourNaissance, int moisNaissance, int anneeNaissance, boolean monsieur) {
        this(prenom, nom, LocalDate.of(anneeNaissance, moisNaissance, jourNaissance), monsieur);
    }

    public final void calculerAge() {

        âge = LocalDate.now().getYear()-naissance.getYear();
    }

    @Override
    public String toString() {
        String genre;
        if (monsieur) {
            genre = "Monsieur";
        } else {
            genre = "Madame";
        }
        if (âge > 1) {
            return genre + " " + prénom + " " + nom + ", " + âge + " ans, #" + numeroDeSecu;
        } else {
            return genre + " " + prénom + " " + nom + ", " + âge + " an, #" + numeroDeSecu;
        }
    }
    
    public String genealogie() {
        StringBuilder msg = new StringBuilder();
        Personne crt = this;
        while(crt.parent != null) {
            crt = crt.parent;
        }
        
        while(crt != this) {
            msg.append("\t").append(crt).append("\n");
            crt = crt.enfant;
        }
        msg.append("\n").append(this).append("\n\n");
        crt = enfant;
        while(crt != null) {
            
            msg.append("\t").append(crt).append("\n");
            crt = crt.enfant;
        }
        return msg.toString();
    }

    public void setEnfant(Personne enfant) {
        this.enfant = enfant;
        enfant.parent = this;
    }

    public void setParent(Personne parent) {
        this.parent = parent;
        parent.enfant = this;
    }

    public Personne getParent() {
        return parent;
    }

    public Personne getEnfant() {
        return enfant;
    }

    public static int population() {
        return population;
    }
}
