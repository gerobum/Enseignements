/*
Écrire une méthode principal main qui
1. crée deux polygones qui se partageant un côté,
2. les affiche,
3. leur applique à chacun la même translation,
4. les affiche.
 */
package cf.pmi_pgi.main;

import cf.pmi_pgi.geometrie.Polygone;
import cf.pmi_pgi.geometrie.Point;

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

        /* 
        Ici ça change car le polygne est devenu immuable. Sa translation (ou sa
        rotation) ne le modifie pas mais retourne un autre polygone.
         */
        carre = carre.translation(100, 100);
        triangle = triangle.translation(100, 100);

        carre.afficher();
        triangle.afficher();

    }
}
