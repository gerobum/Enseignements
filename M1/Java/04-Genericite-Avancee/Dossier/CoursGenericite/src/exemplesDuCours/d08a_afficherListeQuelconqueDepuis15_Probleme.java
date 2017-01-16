package exemplesDuCours;

import java.util.LinkedList;
import java.util.List;

public class d08a_afficherListeQuelconqueDepuis15_Probleme {
    //                            Mauvaise idée
    //                                  |
    //                                  v
    public static void afficher(List<Object> v) {
        for(int i = 0; i < v.size(); i++)
          System.out.println(v.get(i));
    }
    public static void main(String[] args) {
        List<Integer> v = new LinkedList<>();
        List<String> w = new LinkedList<>();
        v.add(1); v.add(2);
        w.add("un"); w.add("deux");
        // Le compilateur interdit ça car ni v ni w ne sont des listes d'objets
        afficher(v);
        afficher(w);
    }

}
