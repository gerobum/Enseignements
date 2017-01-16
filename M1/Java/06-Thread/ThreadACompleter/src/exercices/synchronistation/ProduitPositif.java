package exercices.synchronistation;

import java.util.Random;

/**
 * Cette classe abrite deux threads. 
 * 
 * - L'un fait appel à la méthode modifierAttributs()
 * qui modifie les 4 attributs n1, n2, n3, n4 en leur donnant des valeurs alétoires, 
 * les 4 étant soit tous positifs, soit tous négatifs.
 * 
 * - L'autre fait à la méthode afficherProduit qui affiche le produit des 4 attributs,
 * c'est-à-dire n1*n2*n3*n4. Comme ils sont tous négatifs ou tous positifs, 
 * leur produit devrait toujours être positif. 
 * 
 * POURTANT ca ne marche pas. Très rapidement un produit sera négatif, mettant
 * fin au programme. C'est un problème de synchronisation.
 * 
 * Exercice : essayez de comprendre pourquoi et modifiez la classe pour régler
 * ce problème de synchronisation.
 * 
 * @author maillot
 */
public class ProduitPositif {

    private int n1, n2, n3, n4;
    private Random random = new Random(0);
    
    private class Calculateur extends Thread {

        @Override
        public void run() {
            while (true) {
                afficherProduit();
            }
        }
    }

    private class Modificateur extends Thread {

        @Override
        public void run() {
            while (true) {
                modifierAttributs();
            }
        }
    }

    public ProduitPositif() {
        new Modificateur().start();
        new Calculateur().start();
    }

    private void afficherProduit() {
        int produit = n1 * n2 * n3 * n4;
        System.out.println(produit);
        if (produit < 0) {
            System.err.println("Ne devrait pas etre negatif");
            System.exit(1);
        }
    }

    private void modifierAttributs() {
        int signe = 1;
        if (random.nextBoolean()) {
            signe = -signe;
        }
        n1 = signe * (random.nextInt(5) + 1);
        n2 = signe * (random.nextInt(5) + 1);
        n3 = signe * (random.nextInt(5) + 1);
        n4 = signe * (random.nextInt(5) + 1);
    }

    public static void main(String[] args) {
        new ProduitPositif();
    }
}
