
package equation;

import exceptions.NulCoefException;

/**
 *
 * @author yvan
 */
public class Equation {

    public final int nbRacines;
    public final Double x1, x2;
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
            x1 = x2 = null;
        } else if (delta == 0) {
            nbRacines = 1;
            x1 = x2 = -b * 1.0 / (2 * a);
        } else {
            nbRacines = 2;
            double rdelta = Math.sqrt(delta);
            x1 = (-b * 1.0 + rdelta) / (2 * a);
            x2 = (-b * 1.0 - rdelta) / (2 * a);
        }
    }

    @Override
    public String toString() {
        return a + "x^2 + " + b + "x + " + c + " = 0";
    }
}
