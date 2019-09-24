package crible;

import chrono.Chrono;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Crible {

    /**
     * Retourne un flux qui est celui en entrée privé des multiples de m (sauf m)
     * 
     * @param s le flux d'int en entrée
     * @param m l'entier dont on veut enlever les multiples sauf lui
     * @return le flut d'entrée privé des multiples de m (sauf m)
     */
    public static IntStream enleveMultiple(IntStream s, int m) {
        return s.filter(p -> p == m || p % m != 0);
    }

    /**
     * @param n un entier positif
     * @return le flux des premiers nombres premiers croissants avant n
     */
    public static IntStream crible(int n) {
        // Le flux des int de 2 à n sauf les multiples de 2
        IntStream s = IntStream.rangeClosed(2, n).filter(p -> p == 2 || p % 2 != 0);
        int rn = (int) Math.sqrt(n) + 1;
        // Ce n'est pas tout à fait Eratostène car on enlève (par exemple) les 
        // multiples de 4 alors que c'est inutile.
        for (int i = 3; i <= rn; i += 2) {
            s = enleveMultiple(s, i);
        }
        return s;
    }

    /**
     * Solution la plus efficace proposée par Adrien Couchot Promo MIAGE 2018
     * 
     * Véritable Eratostème
     * 
     * @param NMAX
     * @return 
     */
    public static IntStream cribleAdrienCouchot(int NMAX) {
        return Crible.cribleAdrienCouchot(IntStream.rangeClosed(2, NMAX), 0, NMAX + 1);
    }

    private static IntStream cribleAdrienCouchot(IntStream is, int N, int NMax) {
        
        int[] array = is.toArray();

        int divisor = array[N];
        if (divisor <= Math.sqrt(NMax)) {
            return Crible.cribleAdrienCouchot(Arrays.stream(array)
                    .filter(p -> p == divisor || p % divisor != 0), N + 1, NMax);
        }
        return Arrays.stream(array);
    }

    private static IntStream cribleAdrienCouchotIteratif(int NMax) {
        // Tous les entiers de 2 à NMax inclus
        IntStream is = IntStream.rangeClosed(2, NMax);
        // Transformation du flux en tableau
        int[] array = is.toArray();
        // n est l'indice du premier nombre premier (2)
        int n = 0;
        int m = array[n]; // multiple de 2 à rayer (sauf 2)
        while (m <= Math.sqrt(NMax)) {
            // le tableau est retransformé en flux dont on enlève les multiples de m
            is = enleveMultiple(Arrays.stream(array), m);
            // le flux est retransformer en tableau
            array = is.toArray();
            // de sorte à récupérer le prochain m
            m = array[++n];
        }
        return Arrays.stream(array);
    }

    /**
     * Quelques tests de performance
     * 
     * @param args
     * @throws NoSuchMethodException 
     */
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
            
            pnp = cribleAdrienCouchotIteratif(n);
            System.out.println("Il y a " + pnp.count() + " nombres premiers avant " + n);
            Chrono.affichageDureeDepuisDernierJalon("Le décompte des nombres premiers a pris");
            System.out.println("-----------------------------------");
            
            pnp = cribleAdrienCouchot(n);
            System.out.println("Il y a " + pnp.count() + " nombres premiers avant " + n);
            Chrono.affichageDureeDepuisDernierJalon("Le décompte des nombres premiers a pris");
            System.out.println("-----------------------------------");
            
            System.out.println("===================================");
        }

    }
}
