/*
 Copier la classe Point immuable du paquetage point_immuable_polygone_mutable 
dans un paquetage appelé point_immuable_polygone_immuable
 */
package cf.pmi_pgi.geometrie;

import static java.lang.Math.*;

/**
 *
 * @author yvan
 */
/* 
Déclarer la classe "final" ne garantit pas son immuabilité, mais c'est 
recommandé car on veut éviter qu'une classe immuable soit étendue en une classe
mutable.
*/
public final class Point {
    /* 
    Si tous les attributs de la classe sont de type simple, une bonne façon de
    s'assurer qu'elle soit immuable est de tous les déclarer "final".
    */
    public final double x, y;
    public final double rho, theta;
    /*
    Par conséquent, il n'y a pas de risque à ce qu'ils soient publics.
    Mais attention, s'ils n'étaient pas "final", ils devraient être privés.
    */

    public Point(double x, double y) {
        this(x, y, false);
    }

    public Point() {
        this(0, 0);
    }

    /*
    Plus besoin des méthodes p2c() et c2p() car ces calculs ne sont réalisés
    qu'une seule fois à la construction.
    */
    public Point(double roux, double touy, boolean polaire) {
        if (polaire) {
            rho = roux;
            theta = touy;
            x = rho * cos(theta);
            y = rho * sin(theta);
        } else {
            x = roux;
            y = touy;
            rho = sqrt(x * x + y * y);
            theta = atan2(y, x);
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
    public Point translation(double dx, double dy) {
        return new Point(x+dx, y+dy);
    }

    public Point rotation(double dtheta) {
        return new Point(rho, (theta+dtheta)%(2*PI), true);
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
