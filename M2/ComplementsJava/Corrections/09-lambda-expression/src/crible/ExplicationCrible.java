package crible;

import java.util.stream.IntStream;
import java.util.stream.Stream;

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
            s = enleveMultiple(s, i);
        }
        s.forEach(p -> System.out.print(p + " "));
        System.out.println();
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
        //crible(100);
        affichageDe2a25sansLesMultiplesDe2sauf2();
    }
}
