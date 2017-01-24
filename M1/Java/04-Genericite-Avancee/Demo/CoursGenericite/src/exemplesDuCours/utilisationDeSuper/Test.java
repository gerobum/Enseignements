package exemplesDuCours.utilisationDeSuper;

import java.util.ArrayList;
import java.util.Collection;

public class Test {
     public static <T> T toutTraiter(Collection<T> c, Traitement<T> s) {
            T dernier = null;
            for(T t : c) {
                    dernier = t;
                            s.traiter(dernier);
            }
            return dernier;
        }

     public static void main(String[] args) {
        Collection<Integer> ci = new ArrayList<>();
        ci.add(1); ci.add(2); ci.add(3);
        Collection<String> cs = new ArrayList<>();
        cs.add("un"); cs.add("deux"); cs.add("trois");
        Affichage<Integer> ai = new Affichage<>();
        Integer di = toutTraiter(ci, ai);
        Affichage<String> as = new Affichage<>();
        String ds = toutTraiter(cs, as);
    }

}
