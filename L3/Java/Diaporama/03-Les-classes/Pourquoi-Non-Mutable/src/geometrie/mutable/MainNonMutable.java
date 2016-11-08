/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrie.mutable;

import frame.Maison;


public class MainNonMutable {

    public static void main(String[] args) {
        Point p13 = new Point(1, 3);
        Point p02 = new Point(0, 2);
        Point p22 = new Point(2, 2);
        Point p00 = new Point(0, 0);
        Point p20 = new Point(2, 0);

        Triangle t = new Triangle(p20, p13, p22);
        Carre c = new Carre(p00, p20, p22, p02);

        Maison maison = new Maison(c, t);
    }
}
