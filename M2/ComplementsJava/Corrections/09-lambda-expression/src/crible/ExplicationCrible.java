package crible;

import java.util.stream.IntStream;

/**
 *
 * @author yvan
 */
public class ExplicationCrible {

    public static IntStream enleveMultiple(IntStream s, int m) {
        return s.filter(p -> p == m || p % m != 0);
    }

    public static void crible(int n) {
        IntStream s = IntStream.rangeClosed(2, n);
        int rn = (int) Math.sqrt(n)+1;
        for (int i = 2; i <= rn; ++i) {
            s = enleveMultiple(s, i)/*.peek(x -> {
                System.out.print("{"+x+"}");
            })*/;
        }
        //s.forEach(p -> System.out.println(p + " "));
        //s.count();
        int limit = n;
        long topDepart = System.nanoTime();
        System.out.println("Il y a " + s.count() + " nombres premiers avant " + limit);
        System.out.println("Le calcul a pris " + ((System.nanoTime() - topDepart)/1_000_000.0) + " ms");
    }

    public static void affichageDe2a25sansLesMultiplesDe5() {
        IntStream.range(2, 26)
                .filter(p -> p == 2 || p % 2 != 0)
                .filter(p -> p == 3 || p % 3 != 0)
                .filter(p -> p == 5 || p % 5 != 0)
                .forEach(System.out::println);
    }

    public static void affichageDe2a25sansLesMultiplesDe3() {
        IntStream.range(2, 26)
                .filter(p -> p == 2 || p % 2 != 0)
                .filter(p -> p == 3 || p % 3 != 0)
                .forEach(System.out::println);
    }

    public static void affichageDe2a25sansLesMultiplesDe2sauf2() {
        IntStream.range(2, 26)
                .filter(p -> p == 2 || p % 2 != 0)
                .forEach(System.out::println);
    }

    public static void affichageDe2a25() {
        IntStream.range(2, 26)
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        //affichageDe2a25();
        //affichageDe2a25sansLesMultiplesDe2sauf2();
        //affichageDe2a25sansLesMultiplesDe3();
        //affichageDe2a25sansLesMultiplesDe5();
        
        crible(1_000_000);
    }
}
