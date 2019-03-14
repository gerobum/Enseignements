package main;

import geom.Point;
import geom.Polygone;
import geom.Vecteur;
import graphic.Carre;
import graphic.Fenetre;
import java.awt.Color;
import static java.lang.Math.PI;
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
    
    
    private static final Random R = new Random();
    private static final int NB_TIRAGES = 1_000_000;
    private static final double C = 1;
    
/* Écrire programme qui définit un carré et approxime par simulation la 
    probabilité qu’un point tiré au hasard dans le cercle circonscrit au carré 
    soit aussi dans le carré.*/
    
    public static void tirageDansCercleCirconscritAuCarre1001m100m1NOK() {
        Fenetre f = new Fenetre(-10, -10, 10, 10);
        // f.setVisible(true);

        Point a = new Point(10, 0);
        Point b = new Point(0, 10);
        Point c = new Point(-10, 0);
        Point[] d = {new Point(0, -10)};

        double R2 = a.getRho();

        Point p;

        Polygone carré = new Polygone(a, b, c, d);

        int nbDans = 0;
        
        for (int i = 0; i < NB_TIRAGES; ++i) {
            p = new Point(R2 * R.nextDouble(), 2 * Math.PI * R.nextDouble(), true);
            if (carré.contientEnSonInterieur(p)) {
                ++nbDans;
                f.set(p, Color.BLACK);
            } else {
                f.set(p, Color.RED);
            }
        }
        double prob = nbDans * 1.0 / NB_TIRAGES;
        
        System.out.println("(NOK) Proba de tirer dans le carré : " + prob*100 + "%");
        System.out.println("(NOK) " + 2.0 * 1.0/prob + " devrait être proche de PI");
        
        
    }
    
    public static void tirageDansCercleCirconscritAuCarre11m11m1m11m1NOK() {
        Fenetre f = new Fenetre(-15, -15, 15, 15);
        // f.setVisible(true);

        Point a = new Point(-10, -10);
        Point b = new Point(10, -10);
        Point c = new Point(10, 10);
        Point[] d = {new Point(-10, 10)};

        double R2 = a.getRho();

        Point p;

        Polygone carré = new Polygone(a, b, c, d);

        int nbDans = 0;

        for (int i = 0; i < NB_TIRAGES; ++i) {
            p = new Point(R2 * R.nextDouble(), 2 * Math.PI * R.nextDouble(), true);
            if (carré.contientEnSonInterieur(p)) {
                ++nbDans;
                f.set(p, Color.BLACK);
            } else {
                f.set(p, Color.RED);
            }
        }
        double prob = nbDans * 1.0 / NB_TIRAGES;
        
        System.out.println("(NOK) Proba de tirer dans le carré : " + prob*100 + "%");
        System.out.println("(NOK) " + 2.0 * 1.0/prob + " devrait être proche de PI");  
        
    }

    public static void tirageDansCercleCirconscritAuCarre1001m100m1OK() {
        Fenetre f = new Fenetre(-10, -10, 10, 10);
        f.setVisible(true);

        Point a = new Point(10, 0);
        Point b = new Point(0, 10);
        Point c = new Point(-10, 0);
        Point[] d = {new Point(0, -10)};

        double R2 = a.getRho();

        Point p;

        Polygone carré = new Polygone(a, b, c, d);

        int nbDans = 0;
        int nbTotal = 0;

        while(nbTotal < NB_TIRAGES) {
            double rayon = R2 * R.nextDouble();
            int n = (int) (C*PI*rayon);
            for (int j = 0; j < n; j++) {
                nbTotal++;
                p = new Point(rayon, 2 * Math.PI * R.nextDouble(), true);
                if (carré.contientEnSonInterieur(p)) {
                    nbDans++;
                    f.set(p, Color.BLACK);
                } else {
                    f.set(p, Color.RED);
                }
            }
        }

        double prob = nbDans * 1.0 / NB_TIRAGES;
        
        System.out.println("(OK) Proba de tirer dans le carré : " + prob*100 + "%");
        System.out.println("(OK) " + 2.0 * 1.0/prob + " devrait être proche de PI");
 
    }

    public static void tirageDansCercleCirconscritAuCarre11m11m1m11m1OK() {
        Fenetre f = new Fenetre(-15, -15, 15, 15);
        f.setVisible(true);

        Point a = new Point(-10, -10);
        Point b = new Point(10, -10);
        Point c = new Point(10, 10);
        Point[] d = {new Point(-10, 10)};

        double R2 = a.getRho();

        Point p;

        Polygone carré = new Polygone(a, b, c, d);

        int nbDans = 0;
        int nbTotal = 0;

        while(nbTotal < NB_TIRAGES) {
            double rayon = R2 * R.nextDouble();
            int n = (int) (C*PI*rayon);
            for (int j = 0; j < n; j++) {
                nbTotal++;
                p = new Point(rayon, 2 * Math.PI * R.nextDouble(), true);
                if (carré.contientEnSonInterieur(p)) {
                    nbDans++;
                    f.set(p, Color.BLACK);
                } else {
                    f.set(p, Color.RED);
                }
            }
        }
        double prob = nbDans * 1.0 / nbTotal;
        
        System.out.println("(OK) Proba de tirer dans le carré : " + prob*100 + "%");
        System.out.println("(OK) " + 2.0 * 1.0/prob + " devrait être proche de PI");
    } 
    
    public static void tirageDansCarre() {
        Fenetre f = new Fenetre(-0.6, -0.6, 0.6, 0.6);
        f.setVisible(true);


        Point p;

        int nbDans = 0;

        for (int i = 0; i < NB_TIRAGES; ++i) {
            p = new Point(R.nextDouble()-0.5, R.nextDouble()-0.5);
            if (p.getRho() < 0.5) {
                ++nbDans;
                f.set(p, Color.BLACK);
            } else {
                f.set(p, Color.RED);
            }
        }
        
        double prob = nbDans * 1.0 / NB_TIRAGES;
        
        System.out.println("(OK) Proba de tirer dans le cercle : " + prob*100 + "%");
        System.out.println("(OK) " + 4*prob + " devrait être proche de PI");
        
    }
    
    public static void main(String[] args) {

        testAGauche();
        testConvexe();

        System.out.println("------------------------");
        tirageDansCercleCirconscritAuCarre1001m100m1NOK();
        System.out.println("------------------------");
        tirageDansCercleCirconscritAuCarre11m11m1m11m1NOK();
        System.out.println("------------------------");
//        tirageDansCercleCirconscritAuCarre11m11m1m11m1OK();
//        System.out.println("------------------------");
//        tirageDansCercleCirconscritAuCarre1001m100m1OK();
//        System.out.println("------------------------");
//        tirageDansCarre();
//        System.out.println("------------------------");

    }
}
