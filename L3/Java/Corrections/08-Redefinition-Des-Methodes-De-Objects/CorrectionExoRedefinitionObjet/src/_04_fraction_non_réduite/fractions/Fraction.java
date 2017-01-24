package _04_fraction_non_réduite.fractions;

import exceptions.DivisionParZéro;
import exceptions.DénominateurNul;
import exceptions.InversionDuneFractionNulle;

public final class Fraction implements Cloneable {

    private int numérateur;
    private int dénominateur;

    public int getNumérateur() {
        return numérateur;
    }

    public void setNumérateur(int numérateur) {
        this.numérateur = numérateur;
    }

    public int getDénominateur() {
        return dénominateur;
    }

    public void setDénominateur(int dénominateur) throws DénominateurNul {
        if (dénominateur == 0) {
            throw new DénominateurNul();
        }
        this.dénominateur = dénominateur;
        if (dénominateur < 0) {
            this.numérateur = -numérateur;
            this.dénominateur = -dénominateur;
        }
    }

    public void reduire() {
        int pgcd = pgcd(Math.abs(numérateur), Math.abs(dénominateur));

        this.numérateur = numérateur / pgcd;
        this.dénominateur = dénominateur / pgcd;

    }

    // #### equals change maintenant car 1/2 == 2/4
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Fraction other = (Fraction) obj;

        return numérateur * other.dénominateur == other.numérateur * dénominateur;
    }

    // #### et même ça
    @Override
    public int hashCode() {
        return new Double(numérateur * 1.0 / dénominateur).hashCode();
    }

    @Override
    public Fraction clone() {
        try {
            return (Fraction) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }

    // #### Redéfinition des méthodes de Object
    // #### Redéfinition de toString()
    @Override
    public String toString() {
        if (numérateur == 0 || dénominateur == 1) {
            return "" + numérateur;
        } else {
            return numérateur + "/" + dénominateur;
        }
    }

    /* ####  Plus besoin de cette méthode, toString est là pour ça
     public void affiche() {
     if (numérateur == 0 || dénominateur == 1) {
     System.out.print("" + numérateur);
     } else {
     System.out.print(numérateur + "/" + dénominateur);
     }
     } */
    // #### Plus de réduction
    private static int pgcd(int a, int b) {
        int r = a % b;
        while (r != 0) {
            a = b;
            b = r;
            r = a % b;
        }
        return b;
    }
    /*
     private static int ppcm(int a, int b) {
     return (a * b) / pgcd(a, b);
     } */

    public Fraction(int numérateur, int dénominateur) throws DénominateurNul {
        if (dénominateur == 0) {
            throw new DénominateurNul();
        }
        if (dénominateur < 0) {
            this.numérateur = -numérateur;
            this.dénominateur = -dénominateur;
        } else {
            this.numérateur = numérateur;
            this.dénominateur = dénominateur;
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
            throw new InternalError();
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
            return new Fraction(numérateur * f.dénominateur + f.numérateur * dénominateur / f.dénominateur, dénominateur * f.dénominateur);
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
