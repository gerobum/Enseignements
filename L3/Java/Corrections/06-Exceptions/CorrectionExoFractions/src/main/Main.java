
package main;

import exceptions.DénominateurNul;
import fractions.Fraction;

/**
 *
 * @author yvan
 */
public class Main {
    public static void main(String[] args) throws DénominateurNul {
        Fraction a = new Fraction(13,8);
        Fraction b = new Fraction(-7,12);
        a.plus(b).affiche();
    }
}
