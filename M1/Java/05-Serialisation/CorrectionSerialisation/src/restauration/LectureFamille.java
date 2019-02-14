package restauration;

import famille.Personne;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;

public class LectureFamille {
    
    public LectureFamille() {
    }
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        
        FileInputStream fin = new FileInputStream("tmp");
        ObjectInputStream oin = new ObjectInputStream(fin);
        
        // La popution déjà une personne
        Personne x = new Personne("x", "x", LocalDate.now(), true);
        
        System.out.println("Restauration de alex (et sa famille)");
        Personne p1 = (Personne) oin.readObject();
        
        System.out.println(p1.genealogie());
        
        System.out.println("Il y a " + Personne.population() + " habitants");

    }
    
}
