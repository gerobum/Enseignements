package equations;

public class Systeme {
    private final Equation[] systeme;
    //int nbInconnues;
    public Systeme(int nbe) {
        //this.nbInconnues = nbInconnues;
        systeme = new Equation[nbe];
        for(int i = 0; i < systeme.length; ++i) {
            systeme[i] = new Equation(nbe+1);
        }
    }
    
    public void set(int l, int c, double v) {
        systeme[l].set(c, v);
    }
    
    public double get(int l, int c) {
        return systeme[l].get(c);
    }

    public int getNbEq() {
        return systeme.length;
    }
}
