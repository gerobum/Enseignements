
package famille;

import java.io.*;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Yvan
 *
 */
public class Personne implements Serializable {

    public final String nom;
    public final String prenom;
    private int numeroDeSecu;
    private Date naissance;
    private transient int age; 
    private static int population = 0;
    public final boolean monsieur;
    private Personne parent;
    private Personne enfant;

    public Personne(String prenom, String nom, Date naissance, boolean monsieur) {
        this.nom = nom;
        this.prenom = prenom;
        this.naissance = naissance;
        population++;
        numeroDeSecu = population;
        this.monsieur = monsieur;

        calculerAge();

        parent = null;
        enfant = null;
    }

    public Personne(String prenom, String nom, int jourNaissance, int moisNaissance, int anneeNaissance, boolean monsieur) {
        this(prenom, nom, new GregorianCalendar(anneeNaissance, moisNaissance, jourNaissance).getTime(), monsieur);
    }

    public final void calculerAge() {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(naissance);
        int annee_naissance = gc.get(GregorianCalendar.YEAR);
        gc.setTime(new Date());
        int annee_aujourdhui = gc.get(GregorianCalendar.YEAR);

        age = annee_aujourdhui - annee_naissance;
    }

    @Override
    public String toString() {
        String genre;
        if (monsieur) {
            genre = "Monsieur";
        } else {
            genre = "Madame";
        }
        if (age > 1) {
            return genre + " " + prenom + " " + nom + ", " + age + " ans, #" + numeroDeSecu;
        } else {
            return genre + " " + prenom + " " + nom + ", " + age + " an, #" + numeroDeSecu;
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

    // Enlever ces commentaires pour que ça fonctionne complètement.
    /*private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(population);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        calculerAge();
        population = in.readInt(); // Ou mieux (population++;)
    }*/
}