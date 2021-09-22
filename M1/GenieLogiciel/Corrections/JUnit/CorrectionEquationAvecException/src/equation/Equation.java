package equation;

import exceptions.NulCoefException;
import static java.lang.Math.*;
import static java.lang.Double.*;

/**
 *
 * @author yvan
 */
public class Equation {

    private int a, b, c;
    private int rootsCount;
    private double x1;
    private double x2;

    public Equation(int a, int b, int c) throws NulCoefException {
        if (a == 0) {
            throw new NulCoefException();
        }

        this.a = a;
        this.b = b;
        this.c = c;

        double delta = b * b - 4 * a * c;

        if (delta < 0) {
            rootsCount = 0;
            x1 = x2 = NaN;
        } else if (delta == 0) {
            rootsCount = 1;
            x1 = -b / (2.0 * a);
            x2 = NaN;
        } else {
            rootsCount = 2;
            x1 = (-b - sqrt(delta)) / (2 * a);
            x2 = (-b + sqrt(delta)) / (2 * a);
        }
    }

    public int getRootsCount() {
        return rootsCount;
    }

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }

    @Override
    public String toString() {
        return a + "x^2 + " + b + "x + " + c + " = 0";
    }
}
