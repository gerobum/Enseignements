package crible;

import chrono.Chrono;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Crible {

    public static IntStream enleveMultiple(IntStream s, int m) {
        return s.filter(p -> p == m || p % m != 0);
    }

    public static IntStream crible(int n) {
        IntStream s = IntStream.range(2, n + 1).filter(p -> p == 2 || p % 2 != 0);
        int rn = (int) Math.sqrt(n) + 1;
        for (int i = 3; i <= rn; i += 2) {
            s = enleveMultiple(s, i);
        }
        return s;
    }

    public static IntStream cribleJusquA(int NMAX) {
        return cribleJusquA(IntStream.rangeClosed(2, NMAX), 0, NMAX + 1);
    }

    private static IntStream cribleJusquA(IntStream is, int N, int NMax) {
        int[] array = is.toArray();

        int divisor = array[N];
        if (divisor <= Math.sqrt(NMax)) {
            return cribleJusquA(Arrays.stream(array)
                    .filter(p -> p == divisor || p % divisor != 0), N + 1, NMax);
        }
        return Arrays.stream(array);
    }

    private static IntStream cribleJusquAIteratif(int NMax) {
        IntStream is = IntStream.rangeClosed(2, NMax);
        int n = 0;
        int[] array = is.toArray();
        int divisor = array[n];
        while (divisor <= Math.sqrt(NMax)) {
            is = enleveMultiple(Arrays.stream(array), divisor);
            array = is.toArray();
            divisor = array[++n];
        }
        return Arrays.stream(array);
    }

    public static void main(String[] args) throws NoSuchMethodException {
        IntStream pnp;

        int n = 1;
        Chrono.premierJalon();
        while (n < 10_000_000) {
            n *= 10;
            
            pnp = crible(n);
            Chrono.affichageDureeDepuisDernierJalon("Le pré-filtrage a pris");
            System.out.println("Il y a " + pnp.count() + " nombres premiers avant " + n);
            Chrono.affichageDureeDepuisDernierJalon("Le décompte des nombres premiers a pris");
            System.out.println("-----------------------------------");
            
            pnp = cribleJusquAIteratif(n);
            System.out.println("Il y a " + pnp.count() + " nombres premiers avant " + n);
            Chrono.affichageDureeDepuisDernierJalon("Le décompte des nombres premiers a pris");
            System.out.println("-----------------------------------");
            
            pnp = cribleJusquA(n);
            System.out.println("Il y a " + pnp.count() + " nombres premiers avant " + n);
            Chrono.affichageDureeDepuisDernierJalon("Le décompte des nombres premiers a pris");
            System.out.println("-----------------------------------");
            
            System.out.println("===================================");
        }

    }
}
