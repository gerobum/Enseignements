/**
 * @author Yvan Maillot (yvan.maillot@uha.fr)
 */

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

/*
TRES TRES IMPORTANT : les responsabilités de cette classe sont

1. de garantir l'intégrité de ses données et en particulier que les coordonnées 
   cartésiennes et polaires soient TOUJOURS cohérentes les unes envers les autres.

2. d'avoir un theta dans [0,2*PI]

3. d'avoir un rho positif (comme il n'est pas d'empêcher la construction d'un
   point avec un rho négatif, ou de mettre rho à jour avec un valeur négative,
   nous devons définir une interprétation d'un rho négatif.

   Nous considérerons que le point de coordonées polaires [-rho:theta] est égal
   au point de coordonnées polaires [rho:(theta+PI)%2*PI]

*/
package cf.ptm_pgm.geometrie;

import static java.lang.Math.*;
import tags.ToCheck;

/**
 * Classe Point pour modéliser des points du plan cartésien.
 * 
 * @author yvan
 */
public class Point {
   
    private double x, y;
    private double rho, theta;
    
    @ToCheck("pfromc")   
    private void pfromc() {
        rho = sqrt(x*x+y*y);
        theta = atan2(y, x); 
        theta %= (2*PI);
        if (theta < 0)
            theta += 2*PI;       
    }
    
    @ToCheck("cfromp")
    private void cfromp() {
        if (rho < 0) {
            rho = -rho;
            theta += PI;
        }
        theta %= (2*PI);
        if (theta < 0)
            theta += 2*PI;
        x = rho*cos(theta);
        y = rho*sin(theta);
    }
    
    public Point(double roux, double touy, boolean polaire) {
        if (polaire) {
            rho = roux;
            theta = touy;
            cfromp();
        } else {
            x = roux;
            y = touy;
            pfromc();
        }
    }

    public Point(double x, double y) {
        this(x, y, false);
    }
    
    public Point() {
        this(0, 0, false);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
        pfromc();
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
        pfromc();
    }

    public double getRho() {
        return rho;
    }

    public void setRho(double rho) {
        this.rho = rho;
        cfromp();
    }

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta = theta;
        cfromp();
    }
    
    public void translation(double dx, double dy) {
        x += dx;
        y += dy;
        pfromc();
    }
    
    public void rotation(double dtheta) {
        theta += dtheta;
        cfromp();
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
    
    @Override
    public String toString() {
        return "("+x+", "+y+")";
    }
}
