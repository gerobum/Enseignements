package exemplesDuCours;

import java.util.LinkedList;
import java.util.List;

public class d07_afficherListeQuelconqueAvant15 {
    public static void afficher(List v) {
        for(int i = 0; i < v.size(); i++)
          System.out.println(v.get(i));
    }
    public static void main(String[] args) {
        List v = new LinkedList();
        List w = new LinkedList();
        v.add(1); v.add(2);
        w.add("un"); w.add("deux");
        // Tout va bien car v et w sont des listes d'objets
        afficher(v);
        afficher(w);
    }
}
