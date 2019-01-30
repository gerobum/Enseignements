
package exos3_1.exo_3ter;

import java.util.Random;

/**
 * Programme qui remplit aléatoirement un tableau d’entiers dont la 
 * taille est aussi tirée au sort entre 10 et 100 et qui affiche son contenu.
 * @author yvan
 */
public class RandomArrayMini {
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
    
    static void echange(int[] t, int a, int b) {
        int x = t[a];
        t[a] = t[b];
        t[b] = x;
    }
    
    /**
     * Retourne un élément de valeur minimale d'un tableau à partir d'une 
     * position
     * @param t
     * @param p
     * @return 
     */
    static int miniFrom(int[] t, int p) {
        int im = p;
        for (int i = p+1; i < t.length; ++i) {
            if (t[i] < t[im])
                im = i;
        }
        echange(t, im, p);
        return t[p];
    }
    
    static boolean enOrdre(int[] t) {
        for (int i = 1; i < t.length; ++i) {
            if (t[i-1] > t[i])
                return false;
        }
        return true;
    }
    
    
    public static void main(String[] args) {
        // Un objet de type Random pour tirer des nombres au hasard.
        Random random = new Random();
        int[] tableau = new int[auHasardEntre(10, 100, random)];
        remplissageAuHasard(tableau, random);
        affichage(tableau); 
        // ---------------------------------
        System.out.println(miniFrom(tableau, 0));
        System.out.println(miniFrom(tableau, 1));
        System.out.println(miniFrom(tableau, 2));
        
        for(int i = 3; i < tableau.length - 1; ++i) {
            miniFrom(tableau, i);
        }
        
        affichage(tableau);
        // (pour provoquer une erreur) tableau[auHasardEntre(0, tableau.length, random)] = 0;
        
        if (enOrdre(tableau))
            System.out.println("Le tableau est trié");
        else
            System.err.println("Le tableau n'est pas trié");
    }
}
