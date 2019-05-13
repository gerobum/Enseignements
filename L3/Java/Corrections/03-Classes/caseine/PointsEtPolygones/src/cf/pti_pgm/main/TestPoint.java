
package cf.pti_pgm.main;

import cf.pti_pgm.geometrie.Point;
import checker.Checker;
import static java.lang.Math.*;

/**
 *
 * @author yvan
 */
public class TestPoint {

    public static void main(String[] args) {
        Point p11 = new Point(1, 1);
        p11.afficher();
        p11.afficher(true);
        for (int i = 0; i < 8; i++) {
            System.out.println();
            p11= p11.rotation(PI/4);
            p11.afficher();
            p11.afficher(true);
        }
        Checker chk = new Checker(Point.class);
        System.out.println(chk);
    }
}