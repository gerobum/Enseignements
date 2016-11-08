package main;

import points.Point1;
import points.Point2;
import points.Point3;
import utilitaire.Test;

/**
 *
 * @author yvan
 */
public class Main {

    // Ce test met en évidence le problème du Point3
    public static void main(String[] args) {
        if (Test.isWellCloneable(new Point1(0, 0))) {
            System.out.println("Point1 : OK");
        } else {
            System.out.println("Point1 : NOK");
        }
        if (Test.isWellCloneable(new Point2(0, 0))) {
            System.out.println("Point2 : OK");
        } else {
            System.out.println("Point2 : NOK");
        }
        if (Test.isWellCloneable(new Point3(0, 0, 0))) {
            System.out.println("Point3 : OK");
        } else {
            System.out.println("Point3 : NOK");
            Point3 p = new Point3(0, 0, 0);
            System.out.println(p.getClass());
        }
    }

}
