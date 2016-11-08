package utilitaire;

import interfaces.VeryCloneable;

/**
 *
 * @author yvan
 */
public class Test {

    /**
     * Pour vérifier que le contrat imposé par Cloneable est bien respecté
     *
     * @param o
     * @return
     */
    public static boolean isWellCloneable(VeryCloneable o) {
        if (o.clone().getClass() != o.getClass()) {
            System.err.println("L'objet et son clone ne sont pas de même type");
            System.err.println(o.getClass()+" # "+o.clone().getClass());
            return false;
        } else if (o == o.clone()) {
            System.err.println("L'objet et son clone ont la même référence");
            return false;
        } else if(!o.clone().equals(o)) {
            System.err.println("L'objet et son clone ne sont pas égaux");
            return false;
        } 

        return  true;

    }
}
