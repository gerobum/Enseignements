package exemplesDuCours;

import java.util.ArrayList;
import java.util.List;

public class d12_consequenceEffacement {

    public static void main(String[] args) {
        List<String> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        if (l1.getClass() == l2.getClass())
            System.out.println("Hélas ce sont bien les mêmes classes");

        // Il est également illégal d’écrire
        if (l1 instanceof List<String>)
            ;
    }
}
