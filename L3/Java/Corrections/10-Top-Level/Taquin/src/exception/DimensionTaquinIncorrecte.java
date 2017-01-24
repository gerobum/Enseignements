package exception;

public class DimensionTaquinIncorrecte extends Exception {
    public DimensionTaquinIncorrecte(int nl, int nc) {
        super(nl + " lignes et " + nc + " colonnes : dimension du taquin incorrecte\n");     
    }    
}
