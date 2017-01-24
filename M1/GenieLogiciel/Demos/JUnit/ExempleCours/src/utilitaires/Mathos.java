/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitaires;

import exceptions.RacineCarréeDunNombreNégatif;

/**
 *
 * @author yvan
 */
public class Mathos {

  public static int valeurAbsolue(int x) {
    if (x < 0) {
      return -x;
    } else {
      return x;
    }
  }

  public static double valeurAbsolue(double x) {
    if (x < 0) {
      return -x;
    } else {
      return x;
    }
  }
  public static final double epsilon = 0.0001;

  public static double racineCarrée(double v) {
    double a = 1;
    double b = v;
    while (a - b > epsilon || a - b < -epsilon) {
      a = (a + b) / 2;
      b = v / a;
    }
    return (a + b) / 2;
  }

  public static double racineCarréeProtégée(double v) throws RacineCarréeDunNombreNégatif {
    if (v < 0) throw new RacineCarréeDunNombreNégatif();
    double a = 1;
    double b = v;
    while (a - b > epsilon || a - b < -epsilon) {
      a = (a + b) / 2;
      b = v / a;
    }
    return (a + b) / 2;
  }
}

