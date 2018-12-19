
package iterators;

import java.util.Iterator;
import table.Tableau2D;


public class DiagonaleIterator implements Iterator<Double> {
    private final Tableau2D tableau;
    private int crt;

    public DiagonaleIterator(Tableau2D tableau) {
        this.tableau = tableau;
        crt = 0;
    }        

    @Override
    public boolean hasNext() {
        return crt < tableau.nbLignes() && crt < tableau.nbColonnes();
    }

    @Override
    public Double next() {
        double v = tableau.get(crt, crt);
        ++crt;
        return v;
    }
    
}
