
package moyenne;

import java.util.Locale;
import java.util.Scanner;


public class Moyenne {
    public static void main(String[] args) {
        // Création d'un scanner qui enrobone System.in
        Scanner in = new Scanner(System.in);
        // in.useLocale(Locale.US); // Décommenter pour lire des nombres à point
        // Invite à l'utilisateur
        System.out.print("Combien de notes : ");
        // Saisie d'un entier
        int n = in.nextInt();
        double s = 0.0;
        for(int i = 1; i <= n; ++i) {
            System.out.print("Note N°" + i + ": ");
            // Saisie d'un nombre à virgule
            s += in.nextDouble();
            // Attention, vraiment à virgule en France.
        }
        System.out.println("La moyenne est " + s/n);
    }
}
