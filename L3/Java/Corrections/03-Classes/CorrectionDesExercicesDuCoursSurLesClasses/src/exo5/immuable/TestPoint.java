
package exo5.immuable;

import static java.lang.Math.*;

/**
 *
 * @author yvan
 */
public class TestPoint {

    public static void main(String[] args) {
        Point p11 = new Point(1, 1);
        p11.afficher(true);
        p11.afficher(false);
        for (int i = 0; i < 8; i++) {
            p11= p11.tourner(PI/4);
            p11.afficher(true);
            p11.afficher(false);
        }
    }
}
