/**
 * @author Yvan Maillot (yvan.maillot@uha.fr)
 */
/*
Écrire une méthode principal main qui
1. crée deux polygones qui se partageant un côté,
2. les affiche,
3. leur applique à chacun la même translation,
4. les affiche.
 */
package cf.ptm_pgm.main;

import cf.ptm_pgm.geometrie.Point;
import cf.ptm_pgm.geometrie.Polygone;

/**
 *
 * @author yvan
 */
public class TestPolygone {

    public static void main(String[] args) {
        Point p00, p04, p44, p40, p23;
        p00 = new Point(0, 0);
        p04 = new Point(0, 4);
        p44 = new Point(4, 4);
        p40 = new Point(4, 0);
        p23 = new Point(2, 3);

        Polygone carre = new Polygone(p00, p04, p44, p40);
        Polygone triangle = new Polygone(p04, p23, p44);

        carre.afficher();
        triangle.afficher();

        System.out.println("-----------------");

        carre.translation(100, 100);
        triangle.translation(100, 100);

        carre.afficher();
        triangle.afficher();

        System.out.println(
                "\n\nObservons que le segment partagé par le carré et le triangle\n"
                + "a été translaté deux fois.\n\n"
                + "En conséquence de quoi, une fois translaté, "
                + "le carré n'en est plus un.\n");

        System.out.println(
                "Ce comportement est normal. "
               + "En effet, ce sont les références des points\n"
               + "qui sont partagés. Les points p04 et p44 "
               + "sont bien les mêmes dans le triangle\net le carré.\n");
        
        
        System.out.println(
                "Ce comportement aurait pu être évité, si la "
               + "classe Point avait été immuable.\n\n"
               + "Rendre la classe Point immuable est l'objet "
               + "de la suite de l'exercice.");
    }
}
