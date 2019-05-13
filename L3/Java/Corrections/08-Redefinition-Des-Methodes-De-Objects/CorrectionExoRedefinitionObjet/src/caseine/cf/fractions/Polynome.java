/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package caseine.cf.fractions;

import exceptions.PolynomeNul;
import java.util.Arrays;
import tags.ToCheck;

/*
Écrire une classe Polynôme qui possède :
   1. Un attribut de type tableau de fractions. 
      L’indice 0 correspond au coefficient de degré 0 tel que 
           a0 + a1 x + a2x2 + ... + an xn .
   2. Un constructeur avec liste de fractions en argument.
   3. Un accesseur à un paramètre entier pour accéder aux coefficients, tel que
      get(int i) retourne le coefficient de degré i.
   4. Une méthode évaluer à un paramètre (x) de type Fraction qui retourne 
      la fraction issue de l’évaluation du polynôme pour x.

Et surtout, redéfinir comme il se doit les méthodes issues de Object.

*/
public class Polynome {

    @ToCheck(priority = 11)
    private final Fraction[] a;

    @ToCheck(priority = 12)
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

    @ToCheck(priority = 13)
    public Fraction get(int i) {
        return a[i];
    }
    
    @ToCheck(priority = 14)
    public int getDegre() {
        return a.length-1;
    }

    @ToCheck(priority = 15)
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
    @ToCheck(priority = 16)
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
    @ToCheck(priority = 17)
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Arrays.deepHashCode(this.a);
        return hash;
    }

    @Override
    @ToCheck(priority = 18)
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
