
package geom;

// Une classe Vecteur avec
public class Vecteur {
    // Des attributs x et y de type double.
    private double x, y;
    // La possibilité de le construire en donnant x et y
    public Vecteur(double x, double y) {
        this.x = x;
        this.y = y;
    }
    // La possibilité de construire le vecteur nul par défaut (0,0)
    public Vecteur() {
        this(0, 0);
    }
    // La possibilité de le construire en donnant deux points
    public Vecteur(Point a, Point b) {
        this(b.getX() - a.getX(), b.getY() - a.getY());
    }
    // 
    public double determinant(Vecteur v) {
        return this.x*v.y - v.x*this.y;
    }
    /* Une méthode qui retourne le déterminant de ce vecteur avec un autre passé 
    en paramètre */
    public Vecteur perpendiculaire() {
        return new Vecteur(-y, x);
    }
    // Les getters sont nécessaires puisque x et y sont privés.
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    
}
