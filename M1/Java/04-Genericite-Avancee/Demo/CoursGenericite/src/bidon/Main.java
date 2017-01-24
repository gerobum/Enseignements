package bidon;

import couples.Couple;

public class Main {
    
    public static void test(Couple<?> c) {
        System.out.println(c.un);
        System.out.println(c.deux);
    }

    // Fonctionne seulement avec des superBidon
    public static <U extends Comparable<U>> U maxNOk(Couple<U> c) {
        if (c.un.compareTo(c.deux) < 0)
            return c.deux;
        else
            return c.un;
    }
    // Fonctionne avec des bidon et des superbidon
    public static <U extends Comparable<? super U>> U maxOk(Couple<U> c) {
        if (c.un.compareTo(c.deux) < 0)
            return c.deux;
        else
            return c.un;
    }

    public static void main(String[] args) {
        SuperBidon s1 = new SuperBidon(2);
        SuperBidon s2 = new SuperBidon(1);
        Couple<SuperBidon> csb = new Couple<>(s1, s2);

        System.out.println(Main.maxNOk(csb));
        System.out.println(Main.maxOk(csb));
        
        Bidon b1 = new Bidon(1, "d'huile");
        Bidon b2 = new Bidon(2, "d'huile");
        Couple<Bidon> cb = new Couple<>(b1, b2);

        // La ligne suivante ne peut pas être compilé
        System.out.println(Main.maxNOk(cb));
        System.out.println(Main.maxOk(cb));
    }
}
