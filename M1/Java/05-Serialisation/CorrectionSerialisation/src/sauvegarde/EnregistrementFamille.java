
package sauvegarde;

import famille.Personne;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author maillot
 */
public class EnregistrementFamille {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Personne jean_phillipe = new Personne("Jean-phillipe", "Hervitmonslip", 10, 11, 1902, true);
        Personne alain = new Personne("Alain", "Terieur", 9, 5, 1937, true);
        Personne alex = new Personne("Alex", "Terieur", 27, 9, 1968, true);
        Personne nicole = new Personne("Nicole", "Niscotch", 7, 8, 2006, false);
        Personne gerard = new Personne("Gerard", "Menfin", 16, 2, 2014, true);

        jean_phillipe.setEnfant(alain);
        alain.setEnfant(alex);
        alex.setEnfant(nicole);
        nicole.setEnfant(gerard);


        System.out.println(alex.genealogie());

        System.out.println("Il y a " + Personne.population() + " habitants");

        FileOutputStream fout = new FileOutputStream("tmp");
        ObjectOutputStream fobj = new ObjectOutputStream(fout);
        
        
        System.out.println("Enregistrement de alex (et sa famille)");
        fobj.writeObject(alex);
    }
}
