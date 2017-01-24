package exo3;

import exo2.Transition;
import testBidon.Bidon;
import testBidon.SuperBidon;

/*
Dans un paquetage exo3, écrire une classe Utilitaire qui contient :
1. une méthode générique (public static) qui retourne le plus grand des éléments 
   d’une Transition.
2. une méthode générique (public static) qui retourne la somme des éléments 
   d’une Transition, sachant que tout Number peut se convertir en Double grâce à la 
   méthode doubleValue().
*/

public class Utilitaire {

    public static Number somme(Transition<? extends Number, ? extends Number, ? extends Number> t) {
        return t.un.doubleValue() + t.deux.doubleValue() + t.trois.doubleValue();
    }
    
   public static <T extends Comparable<? super T>> T plusGrand(Transition<? extends T,? extends T,? extends T> k) {
        if (k.un.compareTo(k.deux) > 0) {
            if (k.un.compareTo(k.trois) > 0) {
                return k.un;
            } else {
                return k.trois;
            }
        } else {
            if (k.deux.compareTo(k.trois) > 0) {
                return k.deux;
            } else {
                return k.trois;
            }
        }
    }
   
   public static <T extends Comparable<? super T>> void affichagePG(Transition<? extends T,? extends T,? extends T> t) {
       System.out.println("Le plus grand d'entre " + t.un + ", " + t.deux + " et " + t.trois + " est " + plusGrand(t));        
   }
    
    public static void main(String[] args) {

        Transition<Integer, Double, Float> idf = new Transition<>(3, 3.14, 3.14F);
        System.out.println(idf.un + " + " + idf.deux + " + " + idf.trois + " = " + somme(idf));

        Transition<String, String, String> sss = new Transition<>("a", "b", "c");
        // Ne compile pas car n'hérite pas de Number
        ///System.out.println(somme(ass));
        affichagePG(sss);
        
        Transition<Double, Double, Double> ddd = new Transition<>(4.0, 6.0, 5.5);

        affichagePG(ddd);
        
        Bidon b = new Bidon(5, "d'eau");
        Bidon c = new Bidon(3, "d'huile");
        SuperBidon a = new SuperBidon(10);
        
        Transition<SuperBidon, Bidon, Bidon> sbsbsb = new Transition<>(a, b, c);
        
        affichagePG(sbsbsb);

    }
}
