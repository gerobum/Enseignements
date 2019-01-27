/*
Une classe Polygone qui possède
 1. un tableau de points en attributs.
 2. La possibilité de le construire à partir d’une liste d’au moins trois points
 3. Les getters et setters
 4. Une méthode pour lui appliquer une translation.
 5. Une méthode pour lui appliquer une rotation par rapport à l’origine.
 6. Une méthode pour l’afficher sur la sortie standard.


 */
package exo5.point_mutable_polygone_mutable;

/**
 *
 * @author yvan
 */
public class Polygone {

    private final Point[] sommets;

    public Polygone(Point a, Point b, Point c, Point... lp) {
        sommets = new Point[3 + lp.length];

        sommets[0] = a;
        sommets[1] = b;
        sommets[2] = c;
        
        int k = 3;
        for (Point p : lp) {
            sommets[k++] = p;
        }
    }

    public Point getSommet(int i) {
        return sommets[i];
    }

    public void setSommet(int i, Point p) {
        sommets[i] = p;
    }

    public int nbSommets() {
        return sommets.length;
    }

    public void translation(double dx, double dy) {
        for (Point p : sommets) {
            p.translation(dx, dy);
        }
    }

    public void rotation(double dtheta) {
        for (Point p : sommets) {
            p.rotation(dtheta);
        }
    }

    public void afficher(boolean polaire) {
        for (Point p : sommets) {
            p.afficher(polaire);
        }
        System.out.println();
    }

    public void afficher() {
        afficher(false);
    }
}
