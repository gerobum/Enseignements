package iterators;

import java.util.Iterator;
import java.util.Random;
import table.Tableau2D;

public class RandomIterator implements Iterator<Double> {

    private final Tableau2D tableau;
    private int i;
    private static final Random random = new Random();

    private void shuffle(LC[] lc) {
        for(int i = lc.length-1; i > 0; --i) {
            swap(i, random.nextInt(i+1), lc);
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
