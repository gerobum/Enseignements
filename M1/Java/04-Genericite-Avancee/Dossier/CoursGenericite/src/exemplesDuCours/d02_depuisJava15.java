package exemplesDuCours;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class d02_depuisJava15 {

    public static void main(String[] args) {
        // Façon de faire avant Java 1.5
        List<String> chaînes = new ArrayList<>();
        chaînes.add("un");
        chaînes.add("deux");
        List<Integer> entiers = new ArrayList<>();
        entiers.add(1);
        entiers.add(2);
        int somme = 0;
        for (Integer i : entiers) {
            somme += i;
        }
        System.out.println(somme);
// Cette erreur est maintenant contrôlée.
        entiers.add("trois");// Elle empêche la compilation.
        somme = 0;
        for (Integer i : entiers) {
            somme += i;
        }
    }
}
