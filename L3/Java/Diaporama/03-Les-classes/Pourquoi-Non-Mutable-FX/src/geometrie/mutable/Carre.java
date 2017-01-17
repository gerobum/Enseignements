/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrie.mutable;

public class Carre {
  private Point[] point = new Point[4];

  public Carre(Point a, Point b, Point c, Point d) { 
    point[0] = a;
    point[1] = b;
    point[2] = c;
    point[3] = d;
  }
  public Point getPoint(int p) {
    return point[p%point.length];
  } 
  public void setPoint(int i, Point p) {
    point[i%point.length] = p;
  } 
}
