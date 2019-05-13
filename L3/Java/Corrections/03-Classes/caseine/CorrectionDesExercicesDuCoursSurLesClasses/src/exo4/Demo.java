package exo4;

import java.util.Scanner;
import static exo4.Afficheur.printfSimplifie;

/**
 *
 * @author yvan
 */
public class Demo {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("La table des ");
        int n = in.nextInt();

        for (int i = 1; i < 10; i++) {
            printfSimplifie("% x % = %", i, n, n * i);
            printfSimplifie("\n");
        }

        printfSimplifie("\n");
        printfSimplifie("Les tables de multiplications\n\n");
        printfSimplifie("Table des 1\tTable des 2\tTable des 3\tTable des 4\tTable des 5\n");

        for (int i = 1; i < 10; i++) {
            printfSimplifie("% x 1 = %\t% x 2 = %\t% x 3 = %\t% x 4 = %\t% x 5 = %\n", i, i, i, 2 * i, i, 3 * i, i, 4 * i, i, 5 * i);
        }

        printfSimplifie("\nTable des 6\tTable des 7\tTable des 8\tTable des 9\tTable des 10\n");

        for (int i = 1; i < 10; i++) {
            printfSimplifie("% x 6 = %\t% x 7 = %\t% x 8 = %\t% x 9 = %\t% x 10 = %\n", i, 6 * i, i, 7 * i, i, 8 * i, i, 9 * i, i, 10 * i);
        }

        int[] njours = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        printfSimplifie("\nLe nombre de jours par mois\n\n");

        for (int i = 0; i < njours.length; ++i) {
            printfSimplifie("Le mois % compte % jours\n", i + 1, njours[i]);
        }
    }
}
