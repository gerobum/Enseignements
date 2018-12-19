
package iterators;

import java.util.Iterator;
import table.Tableau2D;


public class ColonneLigneIterator implements Iterator<Double> {
    private final Tableau2D tableau;
    private int l, c;

    public ColonneLigneIterator(Tableau2D tableau) {
        this.tableau = tableau;
        l = c = 0;
    }        

    @Override
    public boolean hasNext() {
       return c < tableau.nbColonnes();
    }

    @Override
    public Double next() {
        double v = tableau.get(l, c);
        ++l;
        if (l == tableau.nbLignes()) {
            l = 0;
            ++c;
        }
        return v;
    }
    
}
