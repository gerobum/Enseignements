
package principal;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author yvan
 */
public class Demo {

    public static void compteMotsDistincts(String fileName) {

        Map<String, Set<String>> sets = new LinkedHashMap<>();
        sets.put("Par ordre quelconque", new HashSet<>());
        sets.put("Par ordre alphabétique", new TreeSet<>());
        sets.put("Par ordre d'insertion", new LinkedHashSet<>());

        System.out.println("*********************************************");
        System.out.println("* " + fileName);
        System.out.println("*********************************************");
        int cpt = 1;
        for (String key : sets.keySet()) {
            Set<String> mots = sets.get(key);
            try (Scanner sc = new Scanner(MotsDistincts.class.getResourceAsStream("poemes/" + fileName))) {

                System.out.println();
                System.out.println("+--------------------------------------------");
                System.out.println("+ EXO N°" + cpt++);
                System.out.println("+--------------------------------------------");
                sc.useDelimiter("[\\s;,.?!':\\\"\\-)(0123456789]+");
                while (sc.hasNext()) {
                    mots.add(sc.next().toLowerCase());
                }
                System.out.println();
                System.out.println("Il y a " + mots.size() + " mots dans " + fileName);
                System.out.println("\n" + key + " : " + mots);
                System.out.println("+--------------------------------------------");
            }
        }

        System.out.println();
        System.out.println("+--------------------------------------------");
        System.out.println("+ EXO N°" + cpt++);
        System.out.println("+--------------------------------------------");
        Map<String, Integer> combien = new HashMap<>();
        try (Scanner sc = new Scanner(MotsDistincts.class.getResourceAsStream("poemes/" + fileName))) {
            while (sc.hasNext()) {
                String mot = sc.next().toLowerCase();
                if (combien.get(mot) == null) {
                    combien.put(mot, 1);
                } else {
                    combien.put(mot, combien.get(mot) + 1);
                }
            }

                System.out.println();
                System.out.println("Le nombre d'apparitions de chaque mot dans " + fileName + "\n");
            System.out.println(combien);

        }
    }

    public static void main(String args[]) throws FileNotFoundException {

        String[] titres = {
            "Chanson d'automne.txt",
            "Ils cassent le monde.txt",
            "L'albatros.txt",
            "La Java des Bombes Atomiques.txt",
            "La chanson des gueux",
            "Les regrets.txt"
        };

        for (String titre : titres) {
            compteMotsDistincts(titre);
        }
    }
}
