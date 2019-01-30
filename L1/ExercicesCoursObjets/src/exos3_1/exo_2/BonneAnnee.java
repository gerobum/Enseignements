package exos3_1.exo_2;

/**
 * Écrire un programme qui souhaite la bonne année à tous les noms passés en ligne de commande.
*/
public class BonneAnnee {
    public static void main(String[] args) {
        System.out.print("Bonne année");
        for(int i = 0; i < args.length; ++i) {
            System.out.print(" " + args[i]);
        }
    }
}
