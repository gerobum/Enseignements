package equation;

import exceptions.NulCoefException;
import static java.lang.Math.*;
import static java.lang.Double.*;

/**
 *
 * @author yvan
 */
public class Equation {

    public final int nbRacines;
    public final double x1, x2;
    public final int a, b, c;

    public Equation(int a, int b, int c) throws NulCoefException {
        if (a == 0) {
            throw new NulCoefException();
        }

        this.a = a;
        this.b = b;
        this.c = c;

        double delta = b * b - 4 * a * c;

        if (delta < 0) {
            nbRacines = 0;
            x1 = x2 = NaN;
        } else if (delta == 0) {
            nbRacines = 1;
            x1 = -b / (2.0 * a);
            x2 = NaN;
        } else {
            nbRacines = 2;
            x1 = (-b - sqrt(delta)) / (2 * a);
            x2 = (-b + sqrt(delta)) / (2 * a);
        }
    }

    @Override
    public String toString() {
        return a + "x^2 + " + b + "x + " + c + " = 0";
    }
}
