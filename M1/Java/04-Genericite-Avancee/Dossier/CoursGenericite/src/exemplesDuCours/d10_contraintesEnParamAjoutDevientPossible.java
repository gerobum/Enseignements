package exemplesDuCours;

import java.util.Collection;

public class d10_contraintesEnParamAjoutDevientPossible {

    // méthode qui ajoute le contenu d'un tableau dans une collection
    public static <T> void tab2Coll(T[] t, Collection<T> c) {
        for (T o : t)
            // Cette fois l'ajout est possible car la méthode est paramétrée par
            // T et donc les éléments de t et c sont de même type, même si on ne
            // le connait pas encore.
                c.add(o);
    }
}
