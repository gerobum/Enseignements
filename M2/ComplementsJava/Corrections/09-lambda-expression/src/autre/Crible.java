/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autre;

import java.util.stream.IntStream;

/**
 *
 * @author yvan
 */
public class Crible {
    private static IntStream afficherLesNombresPremiersInferieursA(int N) {
        return IntStream.range(2, N)
                /*.peek(n -> {
                    System.out.print("("+n+")");
                })*/
                .filter(n -> estPremier(n));
    }
    private static boolean estPremier(int n) {
        return n == 2 || 
                afficherLesNombresPremiersInferieursA((int) Math.ceil(Math.sqrt(n+1)))
                /*.peek(x -> {
                    System.out.print("{"+x+"}");
                })*/
                        .noneMatch(divisor -> n % divisor == 0);
    }
    public static void main(String[] args) {
        /*afficherLesNombresPremiersInferieursA(100)
                .forEach(n -> {
                    System.out.println(n + " ");
                });*/
        int limit = 1_000_000;
        long topDepart = System.nanoTime();
        System.out.println("Il y a " + afficherLesNombresPremiersInferieursA(limit).count() + " nombres premiers avant " + limit);
        System.out.println("Le calcul a pris " + ((System.nanoTime() - topDepart)/1_000_000.0) + " ms");
        
    }
}
