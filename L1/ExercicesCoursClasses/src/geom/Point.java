/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geom;

import static java.lang.Math.PI;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

/**
 *
 * @author yvan
 */
public class Point {
    private double x, y, rho, theta;
    
        
    private void c2p() {
        rho = sqrt(x*x+y*y);
        theta = atan2(y, x);        
    }
    
    private void p2c() {
        theta = theta % (2*PI);
        x = rho*cos(theta);
        y = rho*sin(theta);
    }
    
    public Point(double rhooux, double thetaouy, boolean polaire) {
        if (polaire) {
            rho = rhooux;
            theta = thetaouy;
            p2c();
        } else {
            x = rhooux;
            y = thetaouy;
            c2p();
        }
    }
    
    public Point(double x, double y) {
        this(x, y, false);
    }
    
    public Point() {
        this(0, 0, false);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRho() {
        return rho;
    }

    public void setRho(double rho) {
        this.rho = rho;
    }

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }
    
    public void translater(double dx, double dy) {
        x += dx;
        y += dy;
        c2p();
    }
    
    public void tourner(double dtheta) {
        theta += dtheta;
        p2c();
    }
    
    public void afficher() {
        System.out.println("("+x+", "+y+")");
    }
    
    public boolean aGauche(Point a, Point b) {
        Vecteur ap = new Vecteur(a, this);
        Vecteur ab = new Vecteur(a, b);
        return ap.det(ab) < 0;
    }
}
