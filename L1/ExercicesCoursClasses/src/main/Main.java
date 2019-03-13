package main;

import geom.Point;
import geom.Polygone;
import geom.Vecteur;
import graphic.Carre;
import graphic.Fenetre;
import java.awt.Color;
import java.util.Random;

public class Main {


    // Des tests
    private static int nextInt(int a, int b) {
        return a + R.nextInt(b - a + 1);
    }

    private static Point nextPoint() {
        return new Point(nextInt(-10, 10) * R.nextDouble(), nextInt(-10, 10) * R.nextDouble());
    }

    private static void devraitEtreAdroite(Point a, Point b, Point c) {
        if (!a.aGauche(b, c)) {
            System.out.println(String.format("(%f, %f) est bien à droite de [(%f, %f),(%f, %f)]",
                    a.getX(),
                    a.getY(),
                    b.getX(),
                    b.getY(),
                    c.getX(),
                    c.getY()));
        } else {
            System.err.println(String.format("(%f, %f) devrait être à droite de [(%f, %f),(%f, %f)]",
                    a.getX(),
                    a.getY(),
                    b.getX(),
                    b.getY(),
                    c.getX(),
                    c.getY()));
        }
    }

    private static void devraitEtreAgauche(Point a, Point b, Point c) {
        if (a.aGauche(b, c)) {
            System.out.println(String.format("(%f, %f) est bien à gauche de [(%f, %f),(%f, %f)]",
                    a.getX(),
                    a.getY(),
                    b.getX(),
                    b.getY(),
                    c.getX(),
                    c.getY()));
        } else {
            System.err.println(String.format("(%f, %f) devrait être à gauche de [(%f, %f),(%f, %f)]",
                    a.getX(),
                    a.getY(),
                    b.getX(),
                    b.getY(),
                    c.getX(),
                    c.getY()));
        }
    }

    private static void testAGauche() {
        System.out.println("AGauche");
        Point a = new Point();
        Point b = new Point(1, -1);
        Point c = new Point(1, 1);
        devraitEtreAgauche(a, b, c);
        devraitEtreAdroite(a, c, b);

        for (int i = 0; i < 100; ++i) {
            b = nextPoint();
            c = nextPoint();

            Vecteur v = new Vecteur(b, c).perpendiculaire();
            a = new Point(b.getX(), b.getY());
            a.translater(v);
            devraitEtreAgauche(a, b, c);
            devraitEtreAdroite(a, c, b);
        }
    }

    private static void testConvexe() {
        int i = 1;
        System.out.println("test Convexe");
        {
            //        *    *    
            //        
            //        *    *
            Point a = new Point(-1, -1);
            Point b = new Point(1, -1);
            Point c = new Point(1, 1);
            Point[] d = {new Point(-1, 1)};

            Polygone p = new Polygone(a, b, c, d);

            if (p.estConvexe()) {
                System.out.println("Le polygone " + i++ + " est bien convexe");
            } else {
                System.err.println("Le polygone " + i++ + " devrait être convexe");
            }
        }
        {
            //        *    
            //      *   *
            //        *    
            Point a = new Point(1, 0);
            Point b = new Point(0, 1);
            Point c = new Point(-1, 0);
            Point[] d = {new Point(0, -1)};

            Polygone p = new Polygone(a, b, c, d);

            if (p.estConvexe()) {
                System.out.println("Le polygone " + i++ + " est bien convexe");
            } else {
                System.err.println("Le polygone " + i++ + " devrait être convexe");
            }
        }
        {
            //           *    
            //        *     *
            //          * *
            Point a = new Point(1, 0);
            Point b = new Point(0, 1);
            Point c = new Point(-1, 0);
            Point[] d = {new Point(-0.5, -1), new Point(0.5, -1)};

            Polygone p = new Polygone(a, b, c, d);

            if (p.estConvexe()) {
                System.out.println("Le polygone " + i++ + " est bien convexe");
            } else {
                System.err.println("Le polygone " + i++ + " devrait être convexe");
            }
        }
        {
            //            *    
            //        *   *   *
            //          *   *
            Point a = new Point(1, 0);
            Point b = new Point(0, 1);
            Point c = new Point(-1, 0);
            Point[] d = {new Point(-0.5, -1), new Point(0, 0), new Point(0.5, -1)};

            Polygone p = new Polygone(a, b, c, d);

            if (!p.estConvexe()) {
                System.out.println("Le polygone " + i++ + " est bien concave");
            } else {
                System.err.println("Le polygone " + i++ + " devrait être concave");
            }
        }
    }
    
    
    private static Random R = new Random();
    private static int NB_TIRAGES = 100_000;
    private static int NB1 = 5000;
    private static int NB2 = 500;
    
    public static void tirageDansCercleCirconscritAuCarre1001m100m1NOK() {
        Fenetre f = new Fenetre(-10, -10, 10, 10);

        Random random = new Random();
        Point a = new Point(10, 0);
        Point b = new Point(0, 10);
        Point c = new Point(-10, 0);
        Point[] d = {new Point(0, -10)};

        double R2 = a.getRho();

        Point p;

        Polygone carré = new Polygone(a, b, c, d);

        int nbDans = 0;
        
        for (int i = 0; i < NB_TIRAGES; ++i) {
            p = new Point(R2 * random.nextDouble(), 2 * Math.PI * random.nextDouble(), true);
            if (carré.contientEnSonInterieur(p)) {
                ++nbDans;
                f.set(p, Color.BLACK);
            } else {
                f.set(p, Color.RED);
            }
        }
        System.out.println("NOK " + nbDans * 1.0 / NB_TIRAGES);
        System.out.println("NOK, PI : " + 2.0 * NB_TIRAGES / nbDans);
        
        
    }

    public static void tirageDansCercleCirconscritAuCarre1001m100m1OK() {
        Fenetre f = new Fenetre(-10, -10, 10, 10);

        Random random = new Random();
        Point a = new Point(10, 0);
        Point b = new Point(0, 10);
        Point c = new Point(-10, 0);
        Point[] d = {new Point(0, -10)};

        double R2 = a.getRho();

        Point p;

        Polygone carré = new Polygone(a, b, c, d);

        int nbDans = 0;
        int nbHors = 0;

        for (int i = 0; i < NB1; ++i) {
            double rayon = R2 * random.nextDouble();
            int n = (int) (NB2*rayon/R2);
            for (int j = 0; j < n; j++) {
                p = new Point(rayon, 2 * Math.PI * random.nextDouble(), true);
                if (carré.contientEnSonInterieur(p)) {
                    nbDans++;
                    f.set(p, Color.BLACK);
                } else {
                    f.set(p, Color.RED);
                    nbHors++;
                }
            }
        }

        System.out.println("OK " + nbDans * 1.0 / (nbDans + nbHors));
        System.out.println("OK, PI : " + 2.0 * (nbDans + nbHors) / nbDans);
 
    }

    public static void tirageDansCercleCirconscritAuCarre11m11m1m11m1OK() {
        Fenetre f = new Fenetre(-15, -15, 15, 15);

        Random random = new Random();
        Point a = new Point(-10, -10);
        Point b = new Point(10, -10);
        Point c = new Point(10, 10);
        Point[] d = {new Point(-10, 10)};

        double R2 = a.getRho();

        Point p;

        Polygone carré = new Polygone(a, b, c, d);

        int nbDans = 0;
        int nbHors = 0;

        for (int i = 0; i < NB1; ++i) {
            double rayon = R2 * random.nextDouble();
            int n = (int) (NB2*rayon/R2);
            for (int j = 0; j < n; j++) {
                p = new Point(rayon, 2 * Math.PI * random.nextDouble(), true);
                if (carré.contientEnSonInterieur(p)) {
                    nbDans++;
                    f.set(p, Color.BLACK);
                } else {
                    nbHors++;
                    f.set(p, Color.RED);
                }
            }
        }
        System.out.println("OK " + nbDans * 1.0 / (nbDans+nbHors));
        System.out.println("OK : PI = " + 2.0 * (nbDans+nbHors) / nbDans);
    }
    
    public static void tirageDansCercleCirconscritAuCarre11m11m1m11m1NOK() {
        Fenetre f = new Fenetre(-15, -15, 15, 15);

        Random random = new Random();
        Point a = new Point(-10, -10);
        Point b = new Point(10, -10);
        Point c = new Point(10, 10);
        Point[] d = {new Point(-10, 10)};

        double R2 = a.getRho();

        Point p;

        Polygone carré = new Polygone(a, b, c, d);

        int nbDans = 0;

        for (int i = 0; i < NB_TIRAGES; ++i) {
            p = new Point(R2 * random.nextDouble(), 2 * Math.PI * random.nextDouble(), true);
            if (carré.contientEnSonInterieur(p)) {
                ++nbDans;
                f.set(p, Color.BLACK);
            } else {
                f.set(p, Color.RED);
            }
        }
        System.out.println("NOK " + nbDans * 1.0 / NB_TIRAGES);
        System.out.println("NOK, PI : " + 2.0 * NB_TIRAGES / nbDans);
        
        
    }

    public static void main(String[] args) {

        testAGauche();
        testConvexe();

        //tirageDansCercleCirconscritAuCarre1001m100m1OK();
        tirageDansCercleCirconscritAuCarre1001m100m1NOK();
        //tirageDansCercleCirconscritAuCarre11m11m1m11m1OK();
        tirageDansCercleCirconscritAuCarre11m11m1m11m1NOK();

    }
}
