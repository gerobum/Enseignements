/*
Simple copie de Point mutable (rien à changer)
 */
package cf.ptm_pgi.geometrie;

import static java.lang.Math.*;

/**
 * Classe Point pour modéliser des points du plan cartésien.
 * 
 * @author yvan
 */
public class Point {
   
    private double x, y;
    private double rho, theta;
    
    private void c2p() {
        rho = sqrt(x*x+y*y);
        theta = atan2(y, x);        
    }
    
    private void p2c() {
        theta = theta % (2*PI);
        x = rho*cos(theta);
        y = rho*sin(theta);
    }
    
    public Point(double roux, double touy, boolean polaire) {
        if (polaire) {
            rho = roux;
            theta = touy;
            p2c();
        } else {
            x = roux;
            y = touy;
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
        c2p();
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
        c2p();
    }

    public double getRho() {
        return rho;
    }

    public void setRho(double rho) {
        this.rho = rho;
        p2c();
    }

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta = theta;
        p2c();
    }
    
    public void translation(double dx, double dy) {
        x += dx;
        y += dy;
        c2p();
    }
    
    public void rotation(double dtheta) {
        theta += dtheta;
        p2c();
    }
    
    public void afficher(boolean polaire) {
        if (polaire) {
            System.out.print("[" + rho + ":" + theta + "]");
        } else {
            System.out.print("(" + x + ", " + y + ")");
        }
    }
    
    public void afficher() {
        afficher(false);
    }
}
