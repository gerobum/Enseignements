/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package caseine.checker;

import checker.Checker;

/**
 *
 * @author yvan
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("----------- Fraction ---------------");
        System.out.println(new Checker(caseine.cf.fractions.Fraction.class));
        System.out.println("----------- Polynome ---------------");
        System.out.println(new Checker(caseine.cf.fractions.Polynome.class));
    }
}
