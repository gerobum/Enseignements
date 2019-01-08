
package fr.miage;

/**
 *
 * @author yvan
 */
public class ModeleEquation {
    private Double a, b, c;
    private String resultat;

    public ModeleEquation(Double a, Double b, Double c, String resultat) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.resultat = resultat;
    }

    public ModeleEquation() {
    }

    public Double getA() {
        return a;
    }

    public void setA(Double a) {
        this.a = a;
    }

    public Double getB() {
        return b;
    }

    public void setB(Double b) {
        this.b = b;
    }

    public Double getC() {
        return c;
    }

    public void setC(Double c) {
        this.c = c;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }
    
    
}
