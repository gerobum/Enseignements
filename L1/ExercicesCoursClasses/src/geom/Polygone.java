package geom;
// Une classe Polygone avec :
public class Polygone {
    // Un tableau de points en attributs.
    private Point[] sommets;
    
    /* La possibilité de le construire à partir de trois points et d’un tableau
    de points (parce qu’un polygone a au moins trois points et éventuellement 
    plus) */
    public Polygone(Point a, Point b, Point c, Point[] tp) {
        sommets = new Point[3 + tp.length];

        sommets[0] = a;
        sommets[1] = b;
        sommets[2] = c;
        for (int i = 0; i < tp.length; ++i) {
            sommets[i + 3] = tp[i];
        }
    }
    // Tous les getters et setters.
    public Point getPoint(int p) {
        return sommets[p];
    }

    public void setPoint(int p, Point a) {
        this.sommets[p] = a;
    }

    // Une méthode pour lui appliquer une translation.
    public void translater(double dx, double dy) {
        for (int i = 0; i < sommets.length; ++i) {
            sommets[i].translater(dx, dy);
        }
    }

    // Une méthode pour lui appliquer une rotation par rapport à l’origine.
    public void tourner(double dtheta) {
        for (int i = 0; i < sommets.length; ++i) {
            sommets[i].tourner(dtheta);
        }
    }

    // Une méthode pour l’afficher sur la sortie standard.
    public void afficher() {
        for (int i = 0; i < sommets.length; ++i) {
            sommets[i].afficher();
        }
    }

    /* Une méthode qui indique si le point passé en paramètre est à l’intérieur 
    de ce polygone (supposé convexe). Un point est à l’intérieur d’un polygone 
    convexe s’il est du même côté de chaque côté de ce polygone */ 
    public boolean contientEnSonInterieur(Point p) {
        boolean pos = p.aGauche(sommets[sommets.length - 1], sommets[0]);
        int i = 1;
        while (i < sommets.length && pos == p.aGauche(sommets[i - 1], sommets[i])) {
            i++;
        }
        return i == sommets.length;
    }

    // Une méthode qui indique si ce polygone est convexe.
    public boolean estConvexe() {
        boolean pos = sommets[2].aGauche(sommets[0], sommets[1]);
        for (int i = 3; i < sommets.length; i++) {
            if (pos != sommets[i].aGauche(sommets[i - 2], sommets[i - 1])) {
                return false;
            }
        }
        return pos == sommets[0].aGauche(sommets[sommets.length - 2], sommets[sommets.length - 1])
            && pos == sommets[1].aGauche(sommets[sommets.length - 1], sommets[0]);
    }
}
