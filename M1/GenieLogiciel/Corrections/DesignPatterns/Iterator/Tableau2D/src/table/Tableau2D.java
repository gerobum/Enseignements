package table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Tableau2D implements Iterable<Double> {

    private final Double[][] table;
    private final int nl;
    private final int nc;

    private class DiagonaleIterator implements Iterator<Double> {

        private int crt;

        public DiagonaleIterator() {
            crt = 0;
        }

        @Override
        public boolean hasNext() {
            return crt < nbLignes() && crt < nbColonnes();
        }

        @Override
        public Double next() {
            double v = get(crt, crt);
            ++crt;
            return v;
        }

    }

    private class ColonneLigneIterator implements Iterator<Double> {

        private int l, c;

        public ColonneLigneIterator() {
            l = c = 0;
        }

        @Override
        public boolean hasNext() {
            return c < nbColonnes();
        }

        @Override
        public Double next() {
            double v = get(l, c);
            ++l;
            if (l == nbLignes()) {
                l = 0;
                ++c;
            }
            return v;
        }

    }

    private class LigneColonneIterator implements Iterator<Double> {

        private int l, c;

        public LigneColonneIterator() {
            l = c = 0;
        }

        @Override
        public boolean hasNext() {
            return l < nbLignes();
        }

        @Override
        public Double next() {
            double v = get(l, c);
            ++c;
            if (c == nbColonnes()) {
                c = 0;
                ++l;
            }
            return v;
        }

    }

    /**
     * Autre solution (il faut choisir entre celle-ci et la précédente (celle
     * pas static)
     *
     * En Java, on peut choisir entre une classe interne statique ou non, mais
     * pas dans tous les langages, comme en C++ par exemple.
     */
    public static class RandomIterator implements Iterator<Double> {

        private final Tableau2D tableau;
        private int i;
        private static final Random random = new Random();

        private void shuffle(LC[] lc) {
            for (int i = lc.length - 1; i > 0; --i) {
                swap(i, random.nextInt(i + 1), lc);
            }
        }

        private void swap(int a, int b, LC[] lc) {
            LC x = lc[a];
            lc[a] = lc[b];
            lc[b] = x;
        }

        private class LC {

            public final int l, c;

            public LC(int l, int c) {
                this.l = l;
                this.c = c;
            }

        }
        private final LC[] lc;

        public RandomIterator(Tableau2D tableau) {
            this.tableau = tableau;
            i = 0;
            lc = new LC[tableau.nbLignes() * tableau.nbColonnes()];
            int k = 0;
            for (int l = 0; l < tableau.nbLignes(); ++l) {
                for (int c = 0; c < tableau.nbColonnes(); ++c) {
                    lc[k++] = new LC(l, c);
                }
            }
            shuffle(lc);
        }

        @Override
        public boolean hasNext() {
            return i < lc.length;
        }

        @Override
        public Double next() {
            double v = tableau.get(lc[i].l, lc[i].c);
            ++i;
            return v;
        }

    }
    
    public class RandomIteratorNSWL implements Iterator<Double> {

        private final Iterator<Double> iterator;

        public RandomIteratorNSWL() {
            List<Double> shuffledList = new ArrayList<>(nl*nc);

            for (int l = 0; l < nbLignes(); ++l) {
                for (int c = 0; c < nbColonnes(); ++c) {
                    shuffledList.add(get(l, c));
                }
            }
            Collections.shuffle(shuffledList);
            iterator = shuffledList.iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Double next() {
            return iterator.next();
        }

    }

    public Tableau2D(int nl, int nc) {
        this.nl = nl;
        this.nc = nc;
        table = new Double[nl][nc];
    }

    public int nbLignes() {
        return nl;
    }

    public int nbColonnes() {
        return nc;
    }

    public double get(int l, int c) {
        return table[l][c];
    }

    public void set(int l, int c, double v) {
        table[l][c] = v;
    }

    @Override
    public Iterator<Double> iterator() {
        return new DiagonaleIterator();
    }

    public Iterator<Double> ligneColonneIterator() {
        return new LigneColonneIterator();
    }

    public Iterator<Double> colonneLigneIterator() {
        return new ColonneLigneIterator();
    }

    public Iterator<Double> randomIterator() {
        return new RandomIterator(this);
    }
    
    public boolean equalsToItsTransposed() {
        Iterator<Double> lc = ligneColonneIterator();
        Iterator<Double> cl = colonneLigneIterator();
        while(lc.hasNext()) {
            if (!Objects.equals(lc.next(), cl.next()))
                return false;
        }
        return true;
    }
}
