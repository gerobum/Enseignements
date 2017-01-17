/*
1. un tableau de points en attributs.
2. La possibilité de le construire à partir d’une liste d’au moins trois points
3. Les getters et setters
4. Une méthode pour lui appliquer une translation.
5. Une méthode pour lui appliquer une rotation par rapport à l’origine.
6. Une méthode pour l’afficher sur la sortie standard.
 */
package exo5.immuable;

/**
 *
 * @author yvan
 */
public class Polygone {
    private final Point[] points;
    
    public Polygone(Point a, Point b, Point c, Point... d) {
        points = new Point[3+d.length];
        
        points[0] = a;
        points[1] = b;
        points[2] = c;
        
        for(int i = 0; i < d.length; i++) {
            points[i+3] = d[i];
        }
    }

    public Point getPoint(int i) {
        return points[i];
    }
    
    public int nbPoints() {
        return points.length;
    }
    
    public void translater(double dx, double dy) {
        for(int i = 0; i < points.length; i++) {
            points[i] = points[i].translater(dx, dy);
        }
    }
    
    public void tourner(double dtheta) {
        for(int i = 0; i < points.length; i++) {
            points[i] = points[i].tourner(dtheta);
        }
    }
    
    public void afficher(boolean polaire) {
        for(Point p : points) {
            p.afficher(polaire);
        }
        System.out.println();
    }
    
    public void afficher() {
        afficher(false);
    }
}
