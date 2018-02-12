package geom;

public class Point {

    private double x, y;
    private double rho, theta;

    private void c2p() {
        rho = Math.sqrt(x * x + y * y);
        theta = Math.atan2(y, x);
    }

    private void p2c() {
        theta = theta % 2 * Math.PI;
        x = rho * Math.cos(theta);
        y = rho * Math.sin(theta);
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
        this(0, 0);
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
    
    public void afficher(boolean polaire) {
        if (polaire) {
            System.out.println("["+rho+ ":"+theta+"]");
        } else {
            System.out.println("("+x+ ", "+y+")");
        }
    }
    
    public void afficher() {
        afficher(false);
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
    
    public boolean aGauche(Point a, Point b) {
        Vecteur ap = new Vecteur(a, this);
        Vecteur ab = new Vecteur(a, b);
        
        return ap.determinant(ab)<0;
    }
}
