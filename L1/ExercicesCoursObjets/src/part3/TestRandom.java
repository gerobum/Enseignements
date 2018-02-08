package part3;

import java.util.Random;

/**
 *
 * @author maillot
 */
public class TestRandom {

    public static void main(String[] args) {
        Random random = new Random();

        testBoolean(random, 1_000_000);
        testInt(random, 1_000_000, 6);
    }

    private static void testInt(Random random, int nt, int nb) {

        int[] tn = new int[nb];

        for (int i = 0; i < nt; ++i) {
            tn[random.nextInt(nb)]++;
        }
        System.out.println("Après " + nt + " tirages :");
        for (int i = 0; i < nb; ++i) {
            System.out.printf("%f%% de %d\n", 100.0 * tn[i] * 1.0 / nt, i);
            
        }
    }

    private static void testBoolean(Random random, int nb) {

        int nbt = 0;
        int nbf = 0;

        for (int i = 0; i < nb; ++i) {
            if (random.nextBoolean()) {
                nbt++;
            } else {
                nbf++;
            }
        }
        System.out.println("Après " + nb + " tirages :");
        System.out.printf("%f%% de true\n", 100.0 * nbt * 1.0 / nb);
        System.out.printf("%f%% de false\n", 100.0 * nbf * 1.0 / nb);
    }
}
