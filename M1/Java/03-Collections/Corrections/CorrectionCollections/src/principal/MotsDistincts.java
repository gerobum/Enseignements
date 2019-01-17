package principal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MotsDistincts {

    public static void main(String args[]) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new File("La Java des Bombes Atomiques.txt"))) {
            Set<String> mots = new HashSet<>(); // Réponse à l'exo 1
            //Set<String> mots = new TreeSet<String>(); // Réponse à l'exo 2
            //Set<String> mots = new LinkedHashSet<>(); // Réponse à l'exo 3

            sc.useDelimiter("[\\s;,.?!':\\\"\\-)(“”’0123456789]+");
            while (sc.hasNext()) {
                mots.add(sc.next().toLowerCase());
            }
            System.out.println(mots.size());
            System.out.println(mots);
        }
    }
}
