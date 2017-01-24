package fractions;

import exceptions.*;

/**
 *
 * @author yvan
 */
public final class Fraction {

    public final int numérateur, dénominateur;

    private static int pgcd(int a, int b) {
        int r = a % b;
        while (r != 0) {
            a = b;
            b = r;
            r = a % b;
        }
        return b;
    }

    private static int ppcm(int a, int b) {
        return (a * b) / pgcd(a, b);
    }

    public Fraction(int numérateur, int dénominateur) throws DénominateurNul {
        if (dénominateur == 0) {
            throw new DénominateurNul();
        }
        int pgcd = pgcd(Math.abs(numérateur), Math.abs(dénominateur));
        if (dénominateur < 0) {
            this.numérateur = -numérateur / pgcd;
            this.dénominateur = -dénominateur / pgcd;
        } else {
            this.numérateur = numérateur / pgcd;
            this.dénominateur = dénominateur / pgcd;
        }
    }

    public Fraction(int numérateur) {
        this.numérateur = numérateur;
        this.dénominateur = 1;
    }

    public Fraction() {
        this(1);
    }

    public void affiche() {
        if (dénominateur == 1 || numérateur == 0) {
            System.out.println(numérateur);
        } else {
            System.out.println(numérateur + "/" + dénominateur);
        }
    }
    
    public Fraction opposé() {
        try {
            return new Fraction(-numérateur, dénominateur);
        } catch (DénominateurNul ex) {
            throw new SituationImpossible();// IllegalStateException
        }
    }
    
    public Fraction inverse() throws InversionFractionNulle {
        try {
            return new Fraction(dénominateur, numérateur);
        } catch (DénominateurNul ex) {
            throw new InversionFractionNulle();
        }
    }
    
    public Fraction plus(Fraction f) {
        int ppcm = ppcm(dénominateur, f.dénominateur);
        try {
            return new Fraction(numérateur*ppcm/dénominateur+f.numérateur*ppcm/f.dénominateur, ppcm);
        } catch (DénominateurNul ex) {
            throw new SituationImpossible();
        }
    }
    
    public Fraction fois(Fraction f) {
        try {
            return new Fraction(numérateur*f.numérateur, dénominateur*f.dénominateur);
        } catch (DénominateurNul ex) {
            throw new SituationImpossible();
        }
    }
    
    public Fraction moins(Fraction f) {
        return plus(f.opposé());
    }
    
    public Fraction diviséPar(Fraction f) throws DivisionParUneFractionNulle {
        try {
            return fois(f.inverse());
        } catch (InversionFractionNulle ex) {
            throw new DivisionParUneFractionNulle();
        }
    }

}
