
package iterators;

import java.util.Iterator;
import table.Tableau2D;


public class LigneColonneIterator implements Iterator<Double> {
    private final Tableau2D tableau;
    private int l, c;

    public LigneColonneIterator(Tableau2D tableau) {
        this.tableau = tableau;
        l = c = 0;
    }        

    @Override
    public boolean hasNext() {
       return l < tableau.nbLignes();
    }

    @Override
    public Double next() {
        double v = tableau.get(l, c);
        ++c;
        if (c == tableau.nbColonnes()) {
            c = 0;
            ++l;
        }
        return v;
    }
    
}
