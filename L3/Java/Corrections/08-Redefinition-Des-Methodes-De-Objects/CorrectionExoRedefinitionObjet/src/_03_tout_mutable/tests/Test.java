
package _03_tout_mutable.tests;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import exceptions.DivisionParZéro;
import exceptions.DénominateurNul;
import exceptions.InversionDuneFractionNulle;
import exceptions.VerificationException;
import _03_tout_mutable.fractions.Fraction;
import _03_tout_mutable.polynome.Polynome;


/**
 *
 * @author yvan
 */
public class Test {

    private static final Random random = new Random();

    public static Fraction randomFraction() {
        try {
            int den;
            do {
                den = 10 - random.nextInt(21);
            } while (den == 0);
            return new Fraction(10 - random.nextInt(21), den);
        } catch (DénominateurNul ex) {
            throw new IllegalStateException();
        }
    }

    public static void inegaliteAttendue(String msg, int a, int b) {
        if (a == b) {
            throw new VerificationException(msg + " valeur attendu " + a + " mais reçu " + b);
        }
    }

    public static void inegaliteAttendue(int a, int b) {
        inegaliteAttendue("", a, b);
    }

    // ##### Il faut utiliser equals maintenant
    public static void egaliteAttendue(String msg, Fraction a, Fraction b) {
        if (!a.equals(b)) {
            throw new VerificationException(msg + " valeur attendu " + a + " mais reçu " + b);
        }
    }

    public static void egaliteAttendue(Fraction a, Fraction b) {
        egaliteAttendue("", a, b);
    }

    public static void egaliteAttendue(String msg, int a, int b) {
        if (a != b) {
            throw new VerificationException(msg + " valeur attendu " + a + " mais reçu " + b);
        }
    }

    public static void egaliteAttendue(int a, int b) {
        egaliteAttendue("", a, b);
    }

    public static void véritéAttendue(String msg, boolean v) {
        if (!v) {
            throw new VerificationException(msg);
        }
    }

    public static void plantageProvoqué(String msg) {
        throw new VerificationException(msg);
    }

    public static void testConstruction() {
        System.out.println("Test construction");
        try {
            Fraction ar;
            ar = new Fraction(4, 2);
            egaliteAttendue(new Fraction(2, 1), ar);

            ar = new Fraction(5, 0);
            plantageProvoqué("Ne devrait pas passer par là");
        } catch (DénominateurNul ex) {
        }
    }

    public static void testPlus() {
        System.out.println("Test plus");
        try {
            Fraction a = new Fraction(1, 2);
            Fraction b = new Fraction(2, 3);
            Fraction c = new Fraction(7, 6);

            egaliteAttendue(c, a.plus(b));

            egaliteAttendue(new Fraction(23, 4), new Fraction(25, 5).plus(new Fraction(3, 4)));
            egaliteAttendue(new Fraction(17, 4), new Fraction(25, 5).plus(new Fraction(-3, 4)));
            egaliteAttendue(new Fraction(17, 4), new Fraction(25, 5).plus(new Fraction(-3, 4)));
            egaliteAttendue(new Fraction(-17, 4), new Fraction(-25, 5).plus(new Fraction(3, 4)));
            egaliteAttendue(new Fraction(-23, 4), new Fraction(-25, 5).plus(new Fraction(-3, 4)));
            egaliteAttendue(new Fraction(-23, 4), new Fraction(25, -5).plus(new Fraction(3, -4)));
            egaliteAttendue(new Fraction(23, -4), new Fraction(25, -5).plus(new Fraction(3, -4)));
        } catch (DénominateurNul ex) {
        }
    }

    public static void testMoins() {
        System.out.println("Test différence");
        try {
            Fraction a = new Fraction(1, 2);
            Fraction b = new Fraction(2, 3);
            Fraction c = new Fraction(-1, 6);
            egaliteAttendue(c, a.moins(b));

            egaliteAttendue(new Fraction(17, 4), new Fraction(25, 5).moins(new Fraction(3, 4)));
            egaliteAttendue(new Fraction(23, 4), new Fraction(25, 5).moins(new Fraction(-3, 4)));
            egaliteAttendue(new Fraction(23, 4), new Fraction(25, 5).moins(new Fraction(-3, 4)));
            egaliteAttendue(new Fraction(23, -4), new Fraction(-25, 5).moins(new Fraction(3, 4)));
            egaliteAttendue(new Fraction(-17, 4), new Fraction(-25, 5).moins(new Fraction(-3, 4)));
            egaliteAttendue(new Fraction(-17, 4), new Fraction(25, -5).moins(new Fraction(3, -4)));
            egaliteAttendue(new Fraction(17, -4), new Fraction(25, -5).moins(new Fraction(3, -4)));
        } catch (DénominateurNul ex) {
        }
    }

    public static void testMultiplication() {
        System.out.println("Test multiplication");
        try {
            Fraction a = new Fraction(1, 2);
            Fraction b = new Fraction(2, 3);
            Fraction c = new Fraction(1, 3);
            egaliteAttendue(c, a.fois(b));
            egaliteAttendue(new Fraction(24, 120), new Fraction(1, 2).fois(new Fraction(2, 3)).fois(new Fraction(3, 4)).fois(new Fraction(4, 5)));
            egaliteAttendue(new Fraction(24, 120), new Fraction(-1, 2).fois(new Fraction(-2, 3)).fois(new Fraction(-3, 4)).fois(new Fraction(-4, 5)));
            egaliteAttendue(new Fraction(24, -120), new Fraction(-1, 2).fois(new Fraction(2, -3)).fois(new Fraction(-3, 4)).fois(new Fraction(-4, -5)));
            egaliteAttendue(new Fraction(24, 120), new Fraction(1, -2).fois(new Fraction(-2, 3)).fois(new Fraction(3, -4)).fois(new Fraction(-4, 5)));
        } catch (DénominateurNul ex) {
        }
    }

    public static void testDivision() {
        System.out.println("Test division");
        boolean plantage = false;
        try {
            Fraction a = new Fraction(1, 2);
            Fraction b = new Fraction(2, 3);
            Fraction c = new Fraction(3, 4);
            egaliteAttendue(c, a.diviséPar(b));

            egaliteAttendue(new Fraction(5, 4), new Fraction(1, 2).diviséPar(new Fraction(2, 3)).diviséPar(new Fraction(3, 4)).diviséPar(new Fraction(4, 5)));
            egaliteAttendue(new Fraction(5, 4), new Fraction(-1, 2).diviséPar(new Fraction(-2, 3)).diviséPar(new Fraction(-3, 4)).diviséPar(new Fraction(-4, 5)));
            egaliteAttendue(new Fraction(5, -4), new Fraction(-1, 2).diviséPar(new Fraction(2, -3)).diviséPar(new Fraction(-3, 4)).diviséPar(new Fraction(-4, -5)));
            egaliteAttendue(new Fraction(5, 4), new Fraction(1, -2).diviséPar(new Fraction(-2, 3)).diviséPar(new Fraction(3, -4)).diviséPar(new Fraction(-4, 5)));
            a.diviséPar(new Fraction(0));
            plantageProvoqué("Ne devrait pas passer par là");
        } catch (DivisionParZéro ex) {
            plantage = true;
        } catch (DénominateurNul ex) {
        }
        véritéAttendue("Il devrait y avoir une division par zéro", plantage);
    }

    public static void testOppose() {
        System.out.println("Test oppose");
        try {
            Fraction a = new Fraction(1, 2);
            Fraction b = new Fraction(-2, 4);
            egaliteAttendue(b, a.opposé());

            egaliteAttendue(new Fraction(20, 4), new Fraction(5, -1).opposé());
            egaliteAttendue(new Fraction(-20, 4), new Fraction(5, 1).opposé());
            egaliteAttendue(new Fraction(-20, -4), new Fraction(-5, 1).opposé());
            egaliteAttendue(new Fraction(20, -4), new Fraction(-5, -1).opposé());

        } catch (DénominateurNul ex) {
        }
    }

    public static void testInverse() {
        System.out.println("Test inverse");
        boolean plantage = false;
        try {
            Fraction a = new Fraction(1, 2);
            Fraction b = new Fraction(2, 1);
            egaliteAttendue(b, a.inverse());
            b = new Fraction(0, 1);
            b.inverse();
            plantageProvoqué("Ne devrait pas passer par là");
        } catch (DénominateurNul dn) {
        } catch (InversionDuneFractionNulle ex) {
            plantage = true;
        }
        véritéAttendue("Il devrait y avoir une inversion de fraction null", plantage);
    }

    public static void testFractions() {
        testConstruction();
        testPlus();
        testMoins();
        testInverse();
        testOppose();
        testDivision();
        testMultiplication();
    }
    
    public static void testEvaluationPolynome() {
        try {
            System.out.println("Test Evaluation Polynome");
            Polynome p = new Polynome();
            boolean passeICI = false;
            try {
                p.evaluer(new Fraction());
                plantageProvoqué("Ne devrait pas passer par là");
            } catch(ArrayIndexOutOfBoundsException aie) {
                passeICI = true;
            }
            véritéAttendue("Une exception aurait du être lancé", passeICI);
            
            p = new Polynome(new Fraction(0,1), new Fraction(0,1), new Fraction(0,1));
            passeICI = false;
            try {
                p.evaluer(new Fraction());
                plantageProvoqué("Ne devrait pas passer par là");
            } catch(ArrayIndexOutOfBoundsException aie) {
                passeICI = true;
            }
            véritéAttendue("Une exception aurait du être lancé", passeICI);
            
            Fraction a0 = new Fraction(3,4);
            p = new Polynome(a0);
            Fraction x = new Fraction(3, 2);
            Fraction r = new Fraction(6, 8);
            
            egaliteAttendue(r, p.evaluer(x));
            
            Fraction a1 = new Fraction(3, 7);
            p = new Polynome(a0, a1);
            r = a0.plus(a1.fois(x));
            
            egaliteAttendue(r, p.evaluer(x));
            
            Fraction a2 = new Fraction(13, 17);
            p = new Polynome(a0, a1, a2);
            r = a0.plus(a1.fois(x)).plus(a2.fois(x).fois(x));
            
            egaliteAttendue(r, p.evaluer(x));
            
            
        } catch(DénominateurNul ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void testPolynome() {
        testEvaluationPolynome();
    }


    public static void main(String[] args) throws DénominateurNul {
        testFractions();
        testPolynome();
        
        Polynome p = new Polynome(new Fraction(1,2), new Fraction(2,3), new Fraction(3,4));
        System.out.println(p);
    }
}
