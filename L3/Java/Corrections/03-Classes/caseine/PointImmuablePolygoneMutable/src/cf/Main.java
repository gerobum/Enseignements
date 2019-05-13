/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package cf;

import caseinedev.IntrospectionUtilities;
import cf.geometrie.Point;
import checker.Checker;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Math.PI;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author yvan
 */
public class Main {
    public static void main(String[] args) throws IOException, FileNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Point p11 = new Point(1, 1);
        p11.afficher();
        p11.afficher(true);
        for (int i = 0; i < 8; i++) {
            System.out.println();
            p11.rotation(PI/4);
            p11.afficher();
            p11.afficher(true);
        }
        Checker chk = new Checker(Point.class);
        System.out.println(chk);
        System.out.println(IntrospectionUtilities.getFromMethodTASystemOut(Point.class, p11, "afficher"));
        //System.out.println(IntrospectionUtilities.getFromMethodTASystemOut(c, PI, methodName, args)(Point.class, p11, "afficher"));
    }
}
