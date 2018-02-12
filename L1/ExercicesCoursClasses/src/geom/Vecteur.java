
package geom;


public class Vecteur {
    private double x, y;

    public Vecteur(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public Vecteur() {
        this(0, 0);
    }
    
    public Vecteur(Point a, Point b) {
        this(b.getX() - a.getX(), b.getY() - a.getY());
    }
    
    public double determinant(Vecteur v) {
        return this.x*v.y - v.x*this.y;
    }
}
