/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geom;

/**
 *
 * @author yvan
 */
public class Vecteur {
    private double x, y;

    public Vecteur(double x, double y) {
        this.x = x;
        this.y = y;
    }
        
    public Vecteur(Point a, Point b) {
        this(b.getX() - a.getX(), b.getY() - a.getY());
    }
    
    public double det(Vecteur v) {
        return x * v.y - v.x * y;
    }
    
}
