package exemplesDuCours;

import java.util.Collection;

public class d09_ContraintesEntreParametres {

    // méthode qui ajoute le contenu d'un tableau dans une collection
    static void tab2Coll(Object[] t, Collection<?> c) {
        for (Object o : t)
            // Impossible d'ajouter un objet car
            // un Object n'est pas un objet quelconque.
                c.add(o);
        }
}
