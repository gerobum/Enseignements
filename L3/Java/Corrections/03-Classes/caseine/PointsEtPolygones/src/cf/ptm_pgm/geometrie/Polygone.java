/*
Écrire une classe (mutable) Polygone avec:

    1. Un attribut de type tableau de points.
    2. La possibilité de le construire à partir d’une liste d’au moins trois points.
    3. Une méthode qui retourne le nombre de sommets de ce polygone
    4. Un getter "public Point getSommet(int i)" qui retourne le ième sommet.
    5. Un setter "public void setSommet(Point p, int i) qui affecte le ième à p.
    6. Une méthode pour lui appliquer une translation.
    7. Une méthode pour lui appliquer une rotation par rapport à l’origine.
    8. Une méthode pour l’afficher sur la sortie standard
 */
package cf.ptm_pgm.geometrie;

/**
 *
 * @author yvan
 */
public class Polygone {

    
    private Point[] sommets;

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
    public int nbSommets() {
        return sommets.length;
    }

    public Point getSommet(int i) {
        return sommets[i];
    }

    public void setSommet(int i, Point p) {
        sommets[i] = p;
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

    public String toString() {
        String r = "";
        for (Point p : sommets) {
            r += p;
        }
        return r;
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
