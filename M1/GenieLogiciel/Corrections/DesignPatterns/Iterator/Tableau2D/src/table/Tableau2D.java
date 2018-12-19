
package table;

import iterators.ColonneLigneIterator;
import iterators.DiagonaleIterator;
import iterators.LigneColonneIterator;
import iterators.RandomIterator;
import java.util.Iterator;


public class Tableau2D implements Iterable<Double> {
    private final Double[][] table;
    private final int nl;
    private final int nc;

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
        return new DiagonaleIterator(this);        
    }
    
    public Iterator<Double> otherIterator(String iteratorName) {
        switch(iteratorName) {
            case "LC" :
                return new LigneColonneIterator(this);
            case "CL" :
                return new ColonneLigneIterator(this);
            case "R" :
                return new RandomIterator(this);
            default:
                return new DiagonaleIterator(this); 
        }               
    }
}
