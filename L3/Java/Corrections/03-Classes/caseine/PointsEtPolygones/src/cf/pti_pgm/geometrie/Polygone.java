/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */

/*
Copier la classe Polygone mutable de l'exercice précédent et la modifier pour 
qu'elle manipule des points immuables.
 */
package cf.pti_pgm.geometrie;

/**
 *
 * @author yvan
 */
public class Polygone {
    private final Point[] sommets;
    
    public Polygone(Point a, Point b, Point c, Point... lp) {
        sommets = new Point[3+lp.length];
        
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

    public void setSommet(Point p, int i) {
        sommets[i] = p;
    }
    
    public int nbSommets() {
        return sommets.length;
    }
    
    public void translation(double dx, double dy) {
        for(int i = 0; i < sommets.length; i++) {
            sommets[i] = sommets[i].translation(dx, dy);
        }
    }
    
    public void rotation(double dtheta) {
        for(int i = 0; i < sommets.length; i++) {
            sommets[i] = sommets[i].rotation(dtheta);
        }
    }
    
    public void afficher(boolean polaire) {
        for(Point p : sommets) {
            p.afficher(polaire);
        }
        System.out.println();
    }
    
    public void afficher() {
        afficher(false);
    }
}
