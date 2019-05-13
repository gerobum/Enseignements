/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */

 /*
 Copier la classe Point mutable de l'exercice précédent et la rendre immuable.
 */
package cf.geometrie;

import static java.lang.Math.*;
import tags.ToCheck;

/**
 *
 * @author yvan
 */
/* 
Déclarer la classe "final" ne garantit pas son immuabilité, mais c'est 
recommandé car on veut éviter qu'une classe immuable soit étendue en une classe
mutable.
 */
@ToCheck("Vérifier les modificateurs de la classe Point")
public final class Point {

    /* 
    Si tous les attributs de la classe sont de type simple, une bonne façon de
    s'assurer qu'elle soit immuable est de tous les déclarer "final".
     */
    @ToCheck("Vérifier les attributs x et y")
    public final double x, y;
    @ToCheck("Vérifier les attributs rho et theta")
    public final double rho, theta;

    /*
    Par conséquent, il n'y a pas de risque à ce qu'ils soient publics.
    Mais attention, s'ils n'étaient pas "final", ils devraient être privés.
     */
    @ToCheck("Vérifier vos constructeurs")
    public Point(double x, double y) {
        this(x, y, false);
    }

    @ToCheck("Vérifier vos constructeurs")
    public Point() {
        this(0, 0);
    }

    /*
    Plus besoin des méthodes pfromc() et cfromp() car ces calculs ne sont 
    réalisés qu'une seule fois à la construction.
     */
    public Point(double rhooux, double thetaouy, boolean polaire) {
        if (polaire) {
            if (rhooux < 0) {
                rhooux = -rhooux;
                thetaouy += PI;
            }
            thetaouy %= (2 * PI);
            if (thetaouy < 0) {
                thetaouy += 2 * PI;
            }
            rho = rhooux;
            theta = thetaouy;
            x = rho * cos(theta);
            y = rho * sin(theta);
        } else {
            x = rhooux;
            y = thetaouy;
            double rho = sqrt(x * x + y * y);
            double theta = atan2(y, x);
            theta %= (2 * PI);
            if (theta < 0) {
                theta += 2 * PI;
            }
            this.rho = rho;
            this.theta = theta;
        }
    }

    /*
    Il faut enlever tous les setters.
     */

 /*
    Attention aux setters cachés. translation et rotation en sont.
    Si l'on peut les conserver, ces méthodes doivent retourner une copie
    du point translaté ou pivoté.
     */
    @ToCheck("Vérifier la méthode translation")
    public Point translation(double dx, double dy) {
        return new Point(x + dx, y + dy);
    }

    @ToCheck("Vérifier la méthode rotation")
    public Point rotation(double dtheta) {
        return new Point(rho, (theta + dtheta) % (2 * PI), true);
    }

    @ToCheck
    public void afficher(boolean polaire) {
        if (polaire) {
            System.out.print("[" + rho + ":" + theta + "]");
        } else {
            System.out.print("(" + x + ", " + y + ")");
        }
    }

    @ToCheck
    public void afficher() {
        afficher(false);
    }
}
