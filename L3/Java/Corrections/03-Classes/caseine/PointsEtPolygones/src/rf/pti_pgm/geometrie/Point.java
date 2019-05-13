/**
 * @author Yvan Maillot (yvan.maillot@uha.fr)
 */
package rf.pti_pgm.geometrie;

import static java.lang.Math.*;

// Author : Yvan Maillot (yvan.maillot@uha.fr)

/*

Dans le paquetage pti_pgm, écrire une classe IMMUABLE Point qui présente le même
rôle et les mêmes fonctionnalités que la classe mutable Point de l'exercice 
précédent.

Remarque : pti_pgm signifie "point immuable et polygone mutable"

Pour faire ce travail, vous pouvez copier la solution de la classe (mutable) 
Point de l'exercice précédent et la rendre immuable.

Les méthodes translation() et rotation() doivent demeurer. Attention, donc, à 
les écrire de telles sortes que la classe soit immuable.

 */
/**
 * Classe Point pour modéliser des points du plan.
 *
 * @author yvan
 */
public class Point {
    // Attention de bien respecter les noms d'attributs et de méthodes proposés.

    // TODO Déclarer les coordonnées cartésiennes x et y du point en choisissant les bons niveaux de visibilité
    public final double x, y;

    // TODO Déclarer les coordonnées polaires rho et theta du point en choisissant les bons niveaux de visibilité
    public final double rho, theta;

    // TODO Écrire le constructeur par défaut tel que x=0 et y=0
    public Point() {
        this(0,0);
    }

    // TODO Écrire un constructeur à deux paramètres de type double pour x et y
    public Point(double x, double y) {
        this(x, y, false);
    }

    /* TODO Écrire un constructeur pour créer un point par ses coordonnées polaires.
    Comme les coordonnées cartésiennes sont deux valeurs de type double 
    et que les coordonnées polaires c'est la même chose, il va falloir faire
    preuve d'un peu d'actuce.
    
    Rappel : 
    1. Pour calculer les coordonnées polaires à partir des coordonnées cartésiennes
        
        rho = sqrt(x*x+y*y);
        theta = atan2(y, x); 
        theta %= (2*PI);
        if (theta < 0)
            theta += 2*PI;   
    
    2. Pour calculer les coordonnées cartésiennes à partir des coordonnées polaires
            
        if (rho < 0) {
            rho = -rho;
            theta += PI;
        }
        theta %= (2*PI);
        if (theta < 0)
            theta += 2*PI;
        x = rho*cos(theta);
        y = rho*sin(theta);
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

    /* TODO Écrire une méthode translation pour la translation d'un point, mais attention.
    à respecter son immuabilité.
     */
    public Point translation(double dx, double dy) {
        return new Point(x+dx, y+dy);
    }

    /* TODO Écrire une méthode pour la rotation d'un point, mais attention.
    à respecter son immuabilité.
     */
    public Point rotation(double dtheta) {
        return new Point(rho, theta+dtheta, true);
    }

    public void afficher(boolean polaire) {
        // TODO afficher ce point sur la sortie standard 
        //      polaire => [<rho>:<theta>] 
        //      !polaire => (<x>, <y>) 
        System.out.println(String.format("(%f, %f)", x, y));
    }

    public void afficher() {
        // TODO affichage par défaut est cartésien
        System.out.println(String.format("(%f, %f)", x, y));
    }
}
