/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrie.non_mutable;

public class Triangle {
  private Point[] point = new Point[3];

  public Triangle(Point a, Point b, Point c) { 
    point[0] = a;
    point[1] = b;
    point[2] = c;
  }
  public Point getPoint(int i) {
    return point[i%point.length];
  }
  public void setPoint(int i, Point p) {
    point[i%point.length] = p;
  }
}
