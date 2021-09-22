package test;

import java.util.Iterator;
import table.Tableau2D;

public class Test {

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
        parcours("LC T1\n", t1.ligneColonneIterator());
        parcours("CL T1\n", t1.colonneLigneIterator());
        parcours("LC T2\n", t2.ligneColonneIterator());
        parcours("CL T2\n", t2.colonneLigneIterator());
        parcours("Random T1\n", t1.randomIterator());
        parcours("Random T2\n", t2.randomIterator());
        parcours("Diagonale inverse T1\n", new Iterator<Double>() {
            private final Tableau2D tableau;
            private int l, c;

            {
                this.tableau = t1;
                l = 0;
                c = tableau.nbColonnes() - 1;
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
                c = tableau.nbColonnes() - 1;
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

        if (t1.equalsToItsTransposed()) {
            System.out.println("T1 est égal à sa transposée");
        } else {
            System.out.println("T1 n'est pas égal à sa transposée");
        }
        Tableau2D t3 = new Tableau2D(3, 3);
        t3.set(0, 0, 1); t3.set(0, 1, 2); t3.set(0, 2, 3);
        t3.set(1, 0, 2); t3.set(1, 1, 4); t3.set(1, 2, 5);
        t3.set(2, 0, 3); t3.set(2, 1, 5); t3.set(2, 2, 6);

        if (t3.equalsToItsTransposed()) {
            System.out.println("T3 est égal à sa transposée");
        } else {
            System.out.println("T3 n'est pas égal à sa transposée");
        }
    }
}
