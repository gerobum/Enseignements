/*
 Écrire une classe Point avec :
 1. des attributs x et y de type double, pour les coordonnées cartésiennes.
 2. Des attributs rho et theta de type double, pour les coordonnées polaires.
 3. La possibilité de le construire par défaut (0,0).
 4. La possibilité de le construire en donnant les coordonnées cartésiennes.
 5. La possibilité de le construire en donnant les coordonnées polaires.
 6. Tous les setters et tous les getters.
 7. Une méthode pour lui appliquer une translation.
 8. Une méthode pour lui appliquer une rotation par rapport à l’origine.
 9. Une méthode pour l’afficher sur la sortie standard.
 */
package exo5.immuable;

import static java.lang.Math.*;

/**
 *
 * @author yvan
 */
public final class Point {

    public final double x, y;
    public final double rho, theta;

    public Point(double x, double y) {
        this(x, y, false);
    }

    public Point() {
        this(0, 0);
    }

    public Point(double roux, double toux, boolean polaire) {
        if (polaire) {
            rho = roux;
            theta = toux;
            x = rho * cos(theta);
            y = rho * sin(theta);
        } else {
            x = roux;
            y = toux;
            rho = sqrt(x * x + y * y);
            theta = atan2(y, x);
        }
    }

    public Point translater(double dx, double dy) {
        return new Point(x+dx, y+dy);
    }

    public Point tourner(double dtheta) {
        return new Point(rho, theta+dtheta, true);
    }

    public void afficher(boolean polaire) {
        if (polaire) {
            System.out.print("[" + rho + ":" + theta + "]");
        } else {
            System.out.print("(" + x + ", " + y + ")");
        }
    }

    public void afficher() {
        afficher(false);
    }
}
