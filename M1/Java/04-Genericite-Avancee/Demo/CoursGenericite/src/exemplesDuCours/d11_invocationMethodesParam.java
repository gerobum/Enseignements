package exemplesDuCours;

import java.util.ArrayList;
import java.util.Collection;

public class d11_invocationMethodesParam {

    // méthode qui ajoute le contenu d'un tableau dans une collection
    public static <T> void tab2Coll(T[] t, Collection<T> c) {
        for (T o : t)
            // Cette fois l'ajout est possible car la méthode est paramétrée par
            // T et donc les éléments de t et c sont de même type, même si on ne
            // le connait pas encore.
                c.add(o);
    }

    public static void main(String[] args) {
        Object[] to = new Object[100];
        Collection<Object> co = new ArrayList<>();
        tab2Coll(to, co);// T est remplacé par Object
        String[] ts = new String[100];
        Collection<String> cs = new ArrayList<>();
        tab2Coll(ts, cs);// T est remplacé par String
        tab2Coll(ts, co);// T est remplacé par Object
        tab2Coll(to, cs);// IMPOSSIBLE A COMPILER
        Integer[] ti = new Integer[100];
        Float[] tf = new Float[100];
        Number[] tn = new Number[100];
        Collection<Number> cn = new ArrayList<>();
        tab2Coll(ti, cn);// T est remplacé par Number
        tab2Coll(tf, cn);// T est remplacé par Number
        tab2Coll(tn, cn);// T est remplacé par Number
        tab2Coll(tn, co);// T est remplacé par Object
        tab2Coll(ti, co);// T est remplacé par Object
        tab2Coll(tn, cs);// IMPOSSIBLE A COMPILER
    }
}
