/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mutable;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yvan
 */
public class Point {

  private double x, y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }

  @Override
  public Point clone() {
    try {
      return (Point) super.clone();
    } catch (CloneNotSupportedException ex) {
      return null;
    }
  }
}
