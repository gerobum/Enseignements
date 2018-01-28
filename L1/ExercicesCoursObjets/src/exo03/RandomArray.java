
package exo03;

import java.util.Random;

/**
 * Programme qui remplit aléatoirement un tableau d’entiers dont la 
 * taille est aussi tirée au sort entre 10 et 100 et qui affiche son contenu.
 * @author yvan
 */
public class RandomArray {
    static int auHasardEntre(int d, int f, Random r) {
        return d + r.nextInt((f-d)+1);
    }
    static void remplissageAuHasard(int[] tableau, Random r) {
        for(int i = 0; i < tableau.length; ++i) {
            tableau[i] = r.nextInt();
        }
    }
    static void affichage(int[] tableau) {
        for(int v : tableau) {
            System.out.print(v + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        // Un objet de type Random pour tirer des nombres au hasard.
        Random random = new Random();
        int[] tableau = new int[auHasardEntre(10, 100, random)];
        remplissageAuHasard(tableau, random);
        affichage(tableau);               
    }
}
