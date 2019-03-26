package equation;

import exceptions.NulCoefException;
import static java.lang.Math.*;
import static java.lang.Double.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

/**
 *
 * @author yvan
 */
public class Equation {

    public final int nbRacines;
    public final double x1, x2;
    public final int a, b, c;

    public Equation(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
        
        
        double delta = b*b-4*a*c;
        
        if (delta < 0) {
            nbRacines = 0;
            x1 = x2 = NaN;
        } else if (delta == 0) {
            nbRacines = 1;
            x1 = (-b*1.0)/(2*a);
            x2 = NaN;
        } else {
            nbRacines = 2;
            x1 = (-b-sqrt(delta))/(2*a);
            x2 = (-b+sqrt(delta))/(2*a);
        }
    }

    @Override
    public String toString() {
        return a + "x^2 + " + b + "x + " + c + " = 0";
    }
/*
    public static void main(String[] args) throws NulCoefException {
        Equation e = new Equation(1, -4, 4);
        System.out.println(e + " nb = " + e.nbRacines + " x1 = " + e.x1 + " x2 = " + e.x2);
        final int LIM = 1000;
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal nbdz = BigDecimal.ZERO;
        for (int a = -LIM; a <= LIM; ++a) {
            for (int b = -LIM; b <= LIM; ++b) {
                for (int c = -LIM; c <= LIM; ++c) {
                    total = total.add(BigDecimal.ONE);
                    if (b*b-4*a*c == 0)
                        nbdz = nbdz.add(BigDecimal.ONE);
                }
            }
        }
        System.out.println(total);
        System.out.println(nbdz);
        BigDecimal _1000 = new BigDecimal(1000);
        System.out.println("-> " +  nbdz.divide(total, MathContext.DECIMAL128).multiply(_1000)+ "%%");
    }*/
}
