package _01_tout_immuable.polynome;

import java.util.Arrays;
import _01_tout_immuable.fractions.Fraction;
import exceptions.PolynomeNul;

public class Polynome {

    private final Fraction[] a;

    public Polynome(Fraction... a) {
        int d = a.length - 1;
        while (d >= 0 && a[d].numérateur == 0) {
            --d;
        }
        this.a = new Fraction[d + 1];
        for (int i = 0; i < this.a.length; ++i) {
            this.a[i] = a[i];
        }
    }

    public Fraction get(int i) {
        return a[i];
    }
    
    public int getDegre() {
        return a.length-1;
    }

    public Fraction evaluer(Fraction x) {
        try {
        Fraction r = a[0];
        Fraction xpi = new Fraction();
        for (int i = 1; i < a.length; ++i) {
            xpi = xpi.fois(x);
            r = r.plus(a[i].fois(xpi));
        }
        return r;
        } catch(ArrayIndexOutOfBoundsException ai) {
            throw new PolynomeNul();
        }
    }

    @Override
    public String toString() {
        if (a.length == 0) {
            return "Polynôme nul";
        } else {
            StringBuilder sb = new StringBuilder();
            int n = a.length-1;
            sb.append(a[n]).append("x^").append(n);
            for(int i = n-1; i >= 0; --i) {
                sb.append("+").append(a[i]).append("x^").append(i);
            }
            
            return sb.toString();
        }
    } 

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Arrays.deepHashCode(this.a);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Polynome other = (Polynome) obj;
        return Arrays.deepEquals(this.a, other.a);
    }
    
    
}
