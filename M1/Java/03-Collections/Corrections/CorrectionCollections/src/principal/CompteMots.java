package principal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CompteMots {

    public static void main(String args[]) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("La Java des Bombes Atomiques.txt"));
        Map<String, Integer> combien = new HashMap<>();
        sc.useDelimiter("[\\s;,.?!':\\\"\\-)(“”’0123456789]+");
        while(sc.hasNext()) {
            String mot = sc.next().toLowerCase();
            if (combien.get(mot) == null) {
                combien.put(mot, 1);
            }
            else {
                combien.put(mot, combien.get(mot)+1);
            }       
        }

        System.out.println(combien);

    }
}
