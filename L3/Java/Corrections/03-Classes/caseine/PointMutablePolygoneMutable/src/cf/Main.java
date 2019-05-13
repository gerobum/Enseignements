/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package cf;

import cf.geometrie.Polygone;
import checker.Checker;

/**
 *
 * @author yvan
 */
public class Main {
    public static void main(String[] args) {
        /*Point p11 = new Point(1, 1);
        p11.afficher();
        p11.afficher(true);
        for (int i = 0; i < 8; i++) {
            System.out.println();
            p11.rotation(PI/4);
            p11.afficher();
            p11.afficher(true);
        }*/
        Checker chk = new Checker(Polygone.class);
        System.out.println(chk);
    }
}
