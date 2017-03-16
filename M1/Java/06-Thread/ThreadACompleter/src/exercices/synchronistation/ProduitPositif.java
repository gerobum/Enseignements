package exercices.synchronistation;

import java.util.Random;

/**
 * Cette classe abrite deux threads. 
 * 
 * - L'un fait appel � la m�thode modifierAttributs()
 * qui modifie les 4 attributs n1, n2, n3, n4 en leur donnant des valeurs al�toires, 
 * les 4 �tant soit tous positifs, soit tous n�gatifs.
 * 
 * - L'autre fait � la m�thode afficherProduit qui affiche le produit des 4 attributs,
 * c'est-�-dire n1*n2*n3*n4. Comme ils sont tous n�gatifs ou tous positifs, 
 * leur produit devrait toujours �tre positif. 
 * 
 * POURTANT ca ne marche pas. Tr�s rapidement un produit sera n�gatif, mettant
 * fin au programme. C'est un probl�me de synchronisation.
 * 
 * Exercice : essayez de comprendre pourquoi et modifiez la classe pour r�gler
 * ce probl�me de synchronisation.
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
