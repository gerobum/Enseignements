package crible;

import java.util.stream.IntStream;



public class Crible {
    
    public static IntStream enleveMultiple(IntStream s, int m) {
        return s.filter(p -> p == m || p % m != 0);
    }
    
    

    public static void crible(int n) {
        IntStream s = IntStream.range(2, n+1).filter(p -> p == 2 || p % 2 != 0);
        int rn = (int) Math.sqrt(n)+1;
        for (int i = 3; i <= rn; i+=2) {
            s = enleveMultiple(s, i);
        }
        s.forEach(p -> System.out.print(p + " "));
        System.out.println();
    }
    
    private static IntStream filter(IntStream s, int i, int n) {
        if (i <= n) {
            return filter(s.filter(p->p == i || p%i!=0), i+2, n);
        } else {
            return s;
        }
    }
    
    private static void afficherLesNombresPremiersAvant(int n) {               
        filter(IntStream.range(2, n).
                filter(p->p == 2 || p%2!=0), 3, n).
                forEach(p -> {System.out.print(p +" ");} );
    }
    
    
    
    
    
    
    
    
    
    
    
    

    public static void main(String[] args) throws NoSuchMethodException {
    
        crible(10_000_000);
        //afficherLesNombresPremiersAvant(10000);
        
        System.out.println();
    }
}
