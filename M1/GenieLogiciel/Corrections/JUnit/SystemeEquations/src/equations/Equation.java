package equations;

import java.util.Arrays;

public class Equation {

    private final double[] X;

    public Equation(int nbInconnues) {
        X = new double[nbInconnues + 1];
    }

    public void set(int c, double v) {
        X[c] = v;
    }

    public double get(int c) {
        return X[c];
    }

    private void fois(double v) {
        for (int i = 0; i < X.length; i++) {
            X[i]*=v;
        }
    }

    public void mettreA1(int i) {
        fois(1/X[i]);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Arrays.hashCode(this.X);
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
        final Equation o = (Equation) obj;
        if (o.X.length != X.length)
            return false;
        double coef = X[X.length-1];
        double ocoef = o.X[o.X.length-1];
        
        for(int i = 0; i < X.length; ++i) {
            if (Math.abs(X[i]*ocoef - o.X[i]*coef)>1e-10)
                return false;
        }
        return true;
    }

    
}
