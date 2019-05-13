package caseine.cf.fractions;

import exceptions.*;
import tags.ToCheck;
/*
Reprendre la classe immuable Fraction de l’exercice du cours sur
les exceptions et redéfinir comme il se doit les méthodes de Object
*/
public final class Fraction {

    public final int numérateur;
    public final int dénominateur;
    
    
    // #### Redéfinition des méthodes de Object
    
    // #### Redéfinition de toString()
    @Override
    @ToCheck(priority = 1)
    public String toString() {
        if (numérateur == 0 || dénominateur == 1) {
            return "" + numérateur;
        } else {
            return numérateur + "/" + dénominateur;
        }
    }
    
    // #### redéfinition de hashCode()
    @Override
    @ToCheck(priority = 2)
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.numérateur;
        hash = 97 * hash + this.dénominateur;
        return hash;
    }

    // #### Redéfinition de equals
    @Override
    @ToCheck(priority = 3)
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Fraction other = (Fraction) obj;
        
        return this.numérateur == other.numérateur && this.dénominateur == other.dénominateur;
    }
    
    // #### Il ne faut pas redéfinir clone() car la classe est immuable
    
    
    
    /* ####  Plus besoin de cette méthode, toString est là pour ça
    public void affiche() {
        if (numérateur == 0 || dénominateur == 1) {
            System.out.print("" + numérateur);
        } else {
            System.out.print(numérateur + "/" + dénominateur);
        }
    } */
    
    // #### Sinon, rien de nouveau 
    
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

    public Fraction opposé() {
        try {
            return new Fraction(-numérateur, dénominateur);
        } catch (DénominateurNul ex) {
            throw new IllegalStateException();
        }
    }

    public Fraction inverse() throws InversionDuneFractionNulle {
        try {
            return new Fraction(dénominateur, numérateur);
        } catch (DénominateurNul ex) {
            throw new InversionDuneFractionNulle();
        }
    }

    public Fraction plus(Fraction f) {
        try {
            int ppcm = ppcm(dénominateur, f.dénominateur);
            return new Fraction(numérateur * ppcm / dénominateur + f.numérateur * ppcm / f.dénominateur, ppcm);
        } catch (DénominateurNul ex) {
        }
        return null;
    }

    public Fraction moins(Fraction f) {
        return this.plus(f.opposé());
    }

    public Fraction diviséPar(Fraction f) throws DivisionParZéro {
        try {
            return fois(f.inverse());
        } catch (InversionDuneFractionNulle ex) {
            throw new DivisionParZéro();
        }
    }

    public Fraction fois(Fraction f) {
        try {
            return new Fraction(numérateur * f.numérateur, dénominateur * f.dénominateur);
        } catch (DénominateurNul ex) {
        }
        return null;
    }
}
