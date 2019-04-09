/**
 * @author Yvan Maillot (yvan.maillot@uha.fr)
 */

package rf.ptm_pgm.geometrie;


// Author : Yvan Maillot (yvan.maillot@uha.fr)

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
import static java.lang.Math.*;

/**
 * Classe Point pour modéliser des points du plan.
 *
 * @author yvan
 */
public class Point {
    // Attention de bien respecter les noms d'attributs et de méthodes proposés.

    // TODO Déclarer les coordonnées cartésiennes x et y du point en choisissant les bons niveaux de visibilité

    // TODO Déclarer les coordonnées polaires rho et theta du point en choisissant les bons niveaux de visibilité
    
    /* TODO Écrire une méthode d'instance privée cfromp qui calcule 
            les coordonnées polaires à partir des coordonnées cartésiennes 
       Le calcul est le suivant:
        rho = sqrt(x * x + y * y);
        theta = atan2(y, x);
    */  
    
    /* TODO Écrire une méthode d'instance privée pfromc qui calcule 
            les coordonnées cartésiennes à partir des coordonnées polaires 
       Le calcul est le suivant:
        x = rho * cos(theta);
        y = rho * sin(theta);
    */  
    // À vous de vous en servir à bon escient.

    // TODO Écrire le constructeur par défaut tel que x=0 et y=0
    
    // TODO Écrire un constructeur à deux paramètres de type double pour x et y
    
    // TODO Écrire un constructeur pour créer un point par ses coordonnées polaires.
    // Comme les coordonnées cartésiennes sont deux valeurs de type double 
    // et que les coordonnées polaires c'est la même chose, il va falloir faire
    // preuve d'un peu d'actuce.
    
    // TODO définir les getters : getX(), getY(), getRho(), getTheta()
    
    // TODO définir les setters : setX(), setY(), setRho(), setTheta() 
    // Attention aux pièges.
    
    
    public void translation(double dx, double dy) {
        // TODO Écrire la translation de ce point d'un déplacement dx et dy.
    }

    public void rotation(double dtheta) {
        // TODO Écrire la rotation de ce point d'un angle dtheta par rapport à l'origine.
    }

    public void afficher(boolean polaire) {
        // TODO afficher ce point sur la sortie standard 
        //      polaire => [<rho>:<theta>] 
        //      !polaire => (<x>, <y>) 
    }

    public void afficher() {
        // TODO affichage par défaut est cartésien
    }
}
