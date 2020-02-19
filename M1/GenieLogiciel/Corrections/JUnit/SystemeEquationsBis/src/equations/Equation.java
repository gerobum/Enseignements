package equations;

import exceptions.WrongNumberOfValuesException;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Objects;

public class Equation {

    private final ArrayList<Double> a = new ArrayList<>();
    private double r;
    private final double EPSILON = 1e-6;

    public Equation(double r, double ai, double... as) {
        this.r = r;
        a.add(ai);
        for (double v : as) {
            a.add(v);
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.a);
        hash = 11 * hash + (int) (Double.doubleToLongBits(this.r) ^ (Double.doubleToLongBits(this.r) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Equation other = (Equation) obj;
        if (abs(this.r - other.r) > EPSILON) {
            return false;
        }

        if (a.size() != other.a.size()) {
            return false;
        }

        for (int i = 0; i < a.size(); ++i) {

            if (abs(this.a.get(i) - other.a.get(i)) > EPSILON) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(a.get(0)).append("x1");
        for (int i = 1; i < a.size(); ++i) {
            sb.append(" + ").append(a.get(i)).append("x").append(i + 1);
        }
        sb.append(" = ").append(r);
        return sb.toString();
    }

    public double getA(int i) {
        return a.get(i);
    }

    public double getR() {
        return r;
    }

    public void setA(int i, double a) {
        this.a.set(i, a);
    }

    public void setR(double r) {
        this.r = r;
    }

    public void fois(double v) {
        r *= v;
        for (int i = 0; i < a.size(); ++i) {
            a.set(i, a.get(i) * v);
        }
    }

    public void divisePar(double v) {
        fois(1 / v);
    }

    public int nbInconnues() {
        return a.size();
    }

    public double evaluer(double... x) throws WrongNumberOfValuesException {
        if (x.length != a.size()) {
            throw new WrongNumberOfValuesException();
        }
        double r = a.get(0) * x[0];
        for (int i = 1; i < x.length; ++i) {
            r += a.get(i) * x[i];
        }
        return r;
    }

    public void retirer(Equation e, double v) throws WrongNumberOfValuesException {
        if (e.nbInconnues() != nbInconnues()) {
            throw new WrongNumberOfValuesException();
        }
        for (int i = 0; i < a.size(); ++i) {
            a.set(i, a.get(i) - v*e.a.get(i));
        }
        r -= v*e.r;
    }

    public void retirer(Equation e) throws WrongNumberOfValuesException {
        retirer(e, 1);
    }

}
