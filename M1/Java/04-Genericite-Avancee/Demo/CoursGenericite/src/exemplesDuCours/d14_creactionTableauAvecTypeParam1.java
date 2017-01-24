package exemplesDuCours;

import java.util.ArrayList;
import java.util.List;

public class d14_creactionTableauAvecTypeParam1 {


    public static void nok() {
        // La ligne ci-dessous n'est pas compilable
        List<String>[] lsa = new ArrayList<>[10]; 
        // sinon elle permettrait ça
        Object[] oa = (Object[]) lsa;
        List<Integer> li = new ArrayList<>();
        li.add(new Integer(3));
        oa[1] = li; // Faute, mais passe à la compil
        String s = lsa[1].get(0); 
        // Erreur à l’exécution        
    }

    public static void ok() {
        // La ligne ci-dessous est compilable
        List<?>[] lsa = new ArrayList<?>[10];
        // On peut alors écrire
        Object[] oa = (Object[]) lsa;
        List<Integer> li = new ArrayList<>();
        li.add(new Integer(3));
        oa[1] = li; // Correct, après tout lsa
        // contient des listes de types inconnus
        // On peut même écrire…
        lsa[0] = li;
        String s = (String) lsa[1].get(0);
        // Erreur à l’exécution
    }
    
    


}
