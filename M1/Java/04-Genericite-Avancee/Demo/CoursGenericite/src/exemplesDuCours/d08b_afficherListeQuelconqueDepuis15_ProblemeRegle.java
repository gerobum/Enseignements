
package exemplesDuCours;

import java.util.LinkedList;
import java.util.List;

public class d08b_afficherListeQuelconqueDepuis15_ProblemeRegle {
    //                        Problème réglé
    //                               |
    //                               v
    public static void afficher(List<?> v) {
        for(int i = 0; i < v.size(); i++)
          System.out.println(v.get(i));
    }
    public static void main(String[] args) {
        List<Integer> v = new LinkedList<>();
        List<String> w = new LinkedList<>();
        v.add(1); v.add(2);
        w.add("un"); w.add("deux");
        // List<?> est une liste d'objets quelconques
        afficher(v); // v et w en sont
        afficher(w);
    }

}
