/*
 Copier la classe Polygone mutable du paquetage point_immuable_polygone_mutable 
dans un paquetage appelé point_immuable_polygone_immuable et la rendre immuable
 */
package cf.pmi_pgi.geometrie;

import cf.pmi_pgi.geometrie.Point;

/**
 *
 * @author yvan
 */
// final...
public final class Polygone {
    /* 
    final OK, mais pas suffisant car si ça empêche la modification du tableau
    lui-même, cela n'empêche pas la modification de son contenu.
    Le tableau sommets doit nécessairement être privé.
    */
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
    
    /*
    Bien enlever les setters (évidemment)
    */
    
    public int nbSommets() {
        return sommets.length;
    }
    /*
    Attention, translation et rotation ne doivent plus modifier le polygone
    mais en retourner un nouveau translaté ou pivoté.
    */
    public Polygone translation(double dx, double dy) {
        Point[] lpoints = new Point[sommets.length-3];
        for(int i = 3; i < sommets.length; i++) {
            lpoints[i-3] = sommets[i].translation(dx, dy);
        }
        return new Polygone(
                sommets[0].translation(dx, dy),
                sommets[1].translation(dx, dy),
                sommets[2].translation(dx, dy),
                lpoints
        );
    }
    
    public Polygone rotation(double dtheta) {
        Point[] lpoints = new Point[sommets.length-3];
        for(int i = 3; i < sommets.length; i++) {
            lpoints[i-3] = sommets[i].rotation(dtheta);
        }
        return new Polygone(
                sommets[0].rotation(dtheta),
                sommets[1].rotation(dtheta),
                sommets[2].rotation(dtheta),
                lpoints
        );
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
