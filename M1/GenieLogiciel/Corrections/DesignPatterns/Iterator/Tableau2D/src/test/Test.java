package test;

import java.util.Iterator;
import table.Tableau2D;

public class Test {
    
    public static boolean egalÀSaTransposé(Tableau2D t) {
        if (t.nbColonnes() != t.nbLignes())
            return false;
        
        Iterator<Double> i = t.otherIterator("LC");
        Iterator<Double> j = t.otherIterator("CL");
        while(i.hasNext() && j.hasNext()) {
            if (!i.next().equals(j.next()))
                return false;
        }
        return true;
    }

    public static void parcours(String titre, Iterator<Double> i) {
        System.out.print(titre);
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }

    public static void main(String[] args) {
        Tableau2D t1 = new Tableau2D(3, 4);
        double v = 1;
        for (int l = 0; l < t1.nbLignes(); ++l) {
            for (int c = 0; c < t1.nbColonnes(); ++c) {
                t1.set(l, c, v++);
            }
        }
        Tableau2D t2 = new Tableau2D(4, 3);
        v = 1;
        for (int l = 0; l < t2.nbLignes(); ++l) {
            for (int c = 0; c < t2.nbColonnes(); ++c) {
                t2.set(l, c, v++);
            }
        }

        parcours("(3,4)\n", t1.iterator());
        parcours("(4,3)\n", t2.iterator());
        parcours("LC T1\n", t1.otherIterator("LC"));
        parcours("CL T1\n", t1.otherIterator("CL"));
        parcours("LC T2\n", t2.otherIterator("LC"));
        parcours("CL T2\n", t2.otherIterator("CL"));
        parcours("Random T1\n", t1.otherIterator("R"));
        parcours("Random T2\n", t2.otherIterator("R"));
        parcours("Diagonale inverse T1\n", new Iterator<Double>() {
            private final Tableau2D tableau;
            private int l, c;

            {
                this.tableau = t1;
                l = 0;
                c = tableau.nbColonnes()-1;
            }

            @Override
            public boolean hasNext() {
                return l < tableau.nbLignes() && c >= 0;
            }

            @Override
            public Double next() {
                double v = tableau.get(l, c);
                ++l;
                --c;
                return v;
            }
        });
        parcours("Diagonale inverse T2\n", new Iterator<Double>() {
            private final Tableau2D tableau;
            private int l, c;

            {
                this.tableau = t2;
                l = 0;
                c = tableau.nbColonnes()-1;
            }

            @Override
            public boolean hasNext() {
                return l < tableau.nbLignes() && c >= 0;
            }

            @Override
            public Double next() {
                double v = tableau.get(l, c);
                ++l;
                --c;
                return v;
            }
        });
        
        
        if (egalÀSaTransposé(t1)) {
            System.out.println("T1 est égal à sa transposé");
        } else {
            System.out.println("T1 n'est pas égal à sa transposé");
        }
        Tableau2D t3 = new Tableau2D(3, 3);
        t3.set(0, 0, 1);  t3.set(0, 1, 2);  t3.set(0, 2, 3);
        t3.set(1, 0, 2);  t3.set(1, 1, 4);  t3.set(1, 2, 5);
        t3.set(2, 0, 3);  t3.set(2, 1, 5);  t3.set(2, 2, 6);
        
        if (egalÀSaTransposé(t3)) {
            System.out.println("T3 est égal à sa transposé");
        } else {
            System.out.println("T3 n'est pas égal à sa transposé");
        }
    }
}
