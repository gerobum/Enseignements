
package testBidon;

import static testBidon.Couple.max;

/**
 *
 * @author maillot
 */
public class Main {
   
    public static void main(String[] args) {
        SuperBidon s1 = new SuperBidon(2);
        SuperBidon s2 = new SuperBidon(3);
        Couple<SuperBidon> csb = new Couple<>(s1, s2);
        
        System.out.println(max(csb));
        
        Bidon b1 = new Bidon(1, "d'huile");
        Bidon b2 = new Bidon(2, "d'huile");
        Couple<Bidon> cb = new Couple<>(b1, b2);
        
        // Si on enlève les commentaires, la ligne suivante ne compile pas.
        System.out.println(Couple.maxOK(cb));
        // C'est parce que la méthode max n'a pas été correctement écrite.
        // Celle qui convient est maxOK.
        
    } 
}
