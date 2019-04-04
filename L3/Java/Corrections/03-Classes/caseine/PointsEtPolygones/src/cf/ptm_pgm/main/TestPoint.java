package cf.ptm_pgm.main;

import cf.ptm_pgm.geometrie.Point;
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
            p11.rotation(PI/4);
            p11.afficher();
            p11.afficher(true);
        }
    }
}
