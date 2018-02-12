package main;

import geom.Point;
import geom.Polygone;
import java.util.Random;


public class Main {
    public static void main(String[] args) {
        final double R2 = Math.sqrt(2);
        final int NB_TIRAGES = 10_000_000;
        Random random = new Random();
        Point a = new Point(-1, -1);
        Point b = new Point(1, -1);
        Point c = new Point(1, 1);
        Point[] d = {new Point(-1, 1)};
        
        Point p;
        
        p = new Point();
        System.out.println(p.aGauche(a, b));
        System.out.println(p.aGauche(b, c));
        
        
        Polygone carré = new Polygone(a, b, c, d);
        System.out.println(carré.entoure(p));
        
        int nbDans = 0;
        
        for(int i = 0; i < NB_TIRAGES; ++i) {
            p = new Point(R2*random.nextDouble(), 2*Math.PI*random.nextDouble(), true);
            if (carré.entoure(p)) {
                ++nbDans;
            }
        }
        
        System.out.println(nbDans*100.0/NB_TIRAGES);
        
    }
}
