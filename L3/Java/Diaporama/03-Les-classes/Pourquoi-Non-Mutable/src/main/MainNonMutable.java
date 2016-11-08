/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import geometrie.non_mutable.Carre;
import geometrie.non_mutable.Triangle;
import geometrie.non_mutable.Point;


public class MainNonMutable {
  public static void main(String[] args) {
    Point p13 = new Point(1, 3);
    Point p02 = new Point(0, 2);
    Point p22 = new Point(2, 2);
    Point p00 = new Point(0, 0);
    Point p20 = new Point(2, 0);

    Triangle t = new Triangle(p20, p13, p22);
    Carre c = new Carre(p00, p20, p22, p02);
    
    // t.getPoint(2).setY(3);
    t.setPoint(2, new Point(2, 3));
  }
}
