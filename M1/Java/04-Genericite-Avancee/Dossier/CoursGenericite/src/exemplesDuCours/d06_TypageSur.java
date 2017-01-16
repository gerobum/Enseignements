package exemplesDuCours;

import java.util.ArrayList;
import java.util.List;

public class d06_TypageSur {
    public static void main(String[] args) {
        List<String> ls = new ArrayList<>();
        List<Object> lo = ls; // Heureusement le compilateur interdit ça.
        // Car une liste de String n'est pas une liste d'Object
        // même si un String est un Object.
    }


}
