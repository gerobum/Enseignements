/*
 Copier la classe Polygone immuable du paquetage point_immuable_polygone_immuable 
dans un paquetage appelé point_mutable_polygone_immuable et la laisser immuable
alors qu'elle manipule des points mutables (autant dire d'avance que ce n'est pas
une bonne idée, même si c'est possible).
 */
package exo5.point_mutable_polygone_immuable;

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
    /*
    Bien enlever les setters (évidemment)
    */
    /*
    Attention, translation et rotation ne doivent pas modifier le polygone. Il
    faut donc en créant un nouveau et le retourner.
    Par ailleurs, les méthodes translation et rotation des points, elles, 
    modifient les points.    
    */
    public Polygone translation(double dx, double dy) {
        int i = 0;
        Point a = new Point(sommets[i].getX(), sommets[i].getY());
        a.translation(dx, dy);
        ++i;
        Point b = new Point(sommets[i].getX(), sommets[i].getY());
        b.translation(dx, dy);
        ++i;
        Point c = new Point(sommets[i].getX(), sommets[i].getY());
        c.translation(dx, dy);
        
        Point[] lpoints = new Point[sommets.length-3];
        for(i = 3; i < sommets.length; i++) {
            lpoints[i-3] = new Point(sommets[i].getX(), sommets[i].getY());
            lpoints[i-3].translation(dx, dy);
        }
        return new Polygone(
                a,
                b,
                c,
                lpoints
        );
    }
    /*
    On commence à voir les limites. Ca devient relativement compliqué.
    Nous verrons plus tard la méthode clone() qui simplifie un peu les choses.
    Mais ça reste quand même compliqué.
    */
    
    public Polygone rotation(double dtheta) {
        int i = 0;
        Point a = new Point(sommets[i].getX(), sommets[i].getY());
        a.rotation(dtheta);
        ++i;
        Point b = new Point(sommets[i].getX(), sommets[i].getY());
        b.rotation(dtheta);
        ++i;
        Point c = new Point(sommets[i].getX(), sommets[i].getY());
        c.rotation(dtheta);
        
        Point[] lpoints = new Point[sommets.length-3];
        for(i = 3; i < sommets.length; i++) {
            lpoints[i-3] = new Point(sommets[i].getX(), sommets[i].getY());
            lpoints[i-3].rotation(dtheta);
        }
        return new Polygone(
                a,
                b,
                c,
                lpoints
        );
    }
    
    /*
    Rien à changer dans afficher() et nbSommets()
    */
    public void afficher(boolean polaire) {
        for(Point p : sommets) {
            p.afficher(polaire);
        }
        System.out.println();
    }
    
    public void afficher() {
        afficher(false);
    }
    
    public int nbSommets() {
        return sommets.length;
    }
    
    /*
    Nous avons eu quelques difficultés à écrire les méthodes translations et 
    rotations qui se sont révélées pas très naturelles.
    
    Mais en plus, l'apparent anodine getSommet(int) se révèle un piège.
    En effet, en retourne sommets[i], on retourne la référence d'un Point
    qui, lui, est mutable. Rien n'empêche un utilisateur d'écrire
    
    triangle.getSommet(0).setX(0);
    
    Ce getter, tel qu'il est écrit rend la classe Polygone mutable.
    Il faut le modifier.
    */
    public Point getSommet(int i) {
        // Avant 
        // return sommets[i];
        // Maintenant
        return new Point(sommets[i].getX(), sommets[i].getY());
    }
    /* 
    Encore une fois, nous atteignons les limites. On voit que c'est lourd 
    à écrire, piégeux et peu performant.
  
    */
    /* 
    Et ce n'est pas fini, car le constructeur lui-même est piégeux. Comme on lui
    envoie des points mutables, on ne sait pas du tout ce que l'utilisateur en fera.
    Il peut très les utiliser dans son programme pour autre chose et être amené
    à un moment ou un autre de les modifier. Pourquoi s'en priverait-il ? Ils 
    sont mutables.
    */
    public Polygone(Point a, Point b, Point c, Point... lp) {
        sommets = new Point[3+lp.length];
        // Il ne faut pas copier a, mais une copie de a
        sommets[0] = new Point(a.getX(), a.getY());
        // Et c'est la même pour les autres.
        sommets[1] = new Point(b.getX(), b.getY());
        sommets[2] = new Point(c.getX(), c.getY());
        int k = 3;
        for (Point p : lp) {
            sommets[k++] = new Point(p.getX(), p.getY());
        }
    }
    /*
    Cette fois, notre classe Polygone est bien immuable. Mais à quel prix ?
    
    Ce travail laborieux et risqué est à éviter.
    
    IL NE FAUT PAS TENTER DE RENDRE IMMUABLE UNE CLASSE QUI CONTIENT DES REFERENCES
    D'OBJETS MUTABLES.
    */
}
