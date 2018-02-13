package geom;

public class Polygone {

    private Point[] points;

    public Polygone(Point a, Point b, Point c, Point[] tp) {
        points = new Point[3 + tp.length];

        points[0] = a;
        points[1] = b;
        points[2] = c;
        for (int i = 0; i < tp.length; ++i) {
            points[i + 3] = tp[i];
        }
    }

    public Point getPoint(int p) {
        return points[p];
    }

    public void setPoint(int p, Point a) {
        this.points[p] = a;
    }

    public void translater(double dx, double dy) {
        for (int i = 0; i < points.length; ++i) {
            points[i].translater(dx, dy);
        }
    }

    public void tourner(double dtheta) {
        for (int i = 0; i < points.length; ++i) {
            points[i].tourner(dtheta);
        }
    }

    public void afficher() {
        for (int i = 0; i < points.length; ++i) {
            points[i].afficher();
        }
    }
    
    public boolean entoure(Point p) {
        boolean pos = p.aGauche(points[points.length-1], points[0]);
        int i = 1;
        while(i < points.length && pos == p.aGauche(points[i-1], points[i])) {
            i++;
        }
        return i == points.length;
    }
}
