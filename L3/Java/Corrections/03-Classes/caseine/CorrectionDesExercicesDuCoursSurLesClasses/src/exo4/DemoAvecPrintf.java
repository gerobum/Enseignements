package exo4;

import java.util.Scanner;

/**
 *
 * @author yvan
 */
public class DemoAvecPrintf {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("La table des ");
        int n = in.nextInt();

        for (int i = 1; i < 10; i++) {
            System.out.printf("%d x %d = %d", i, n, n * i);
            System.out.printf("\n");
        }

        System.out.printf("\n");
        System.out.printf("Les tables de multiplications\n\n");
        System.out.printf("Table des 1\tTable des 2\tTable des 3\tTable des 4\tTable des 5\n");

        for (int i = 1; i < 10; i++) {
            System.out.printf("%d x 1 = %d\t%d x 2 = %d\t%d x 3 = %d\t%d x 4 = %d\t%d x 5 = %d\n", i, i, i, 2 * i, i, 3 * i, i, 4 * i, i, 5 * i);
        }

        System.out.printf("\nTable des 6\tTable des 7\tTable des 8\tTable des 9\tTable des 10\n");

        for (int i = 1; i < 10; i++) {
            System.out.printf("%d x 6 = %d\t%d x 7 = %d\t%d x 8 = %d\t%d x 9 = %d\t%d x 10 = %d\n", i, 6 * i, i, 7 * i, i, 8 * i, i, 9 * i, i, 10 * i);
        }

        String[] mois = {"janvier","février","mars","avril","mai","juin","juillet","août","septembre","octobre","novembre","décembre"};
        int[] njours = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        System.out.printf("\nLe nombre de jours par mois\n\n");

        for (int i = 0; i < njours.length; ++i) {
            System.out.printf("Le mois %s compte %d jours\n", mois[i], njours[i]);
        }
    }
}
