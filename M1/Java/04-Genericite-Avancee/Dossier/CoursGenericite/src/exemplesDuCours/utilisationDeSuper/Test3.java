package exemplesDuCours.utilisationDeSuper;

import java.util.ArrayList;
import java.util.Collection;

public class Test3 {

     public static <T> T toutTraiter(Collection<? extends T> c, Traitement<T> s) {
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
        Affichage<Object> ao = new Affichage<>();
        //Integer di = toutTraiter(ci, ao);
        //String ds = toutTraiter(cs, ao);
        Object[] arrayo = new Object[100];
        Integer[] ai = new Integer[100];
        Collection<Object> co = new ArrayList<>();

        Integer di = (Integer) toutTraiter(ci, ao);        
        String ds = (String) toutTraiter(cs, ao);
        // Cette fois le problème vient du retour
        //On pourrait caster, mais il y a mieux : voir Test4
    }
}
