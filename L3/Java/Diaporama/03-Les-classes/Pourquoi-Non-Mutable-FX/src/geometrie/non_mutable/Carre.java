package geometrie.non_mutable;

public class Carre {

    private final Point[] POINT = new Point[4];

    public Carre(Point a, Point b, Point c, Point d) {
        POINT[0] = a;
        POINT[1] = b;
        POINT[2] = c;
        POINT[3] = d;
    }

    public Point getPoint(int p) {
        return POINT[p];
    }

    public void setPoint(int i, Point p) {
        POINT[i] = p;
    }

    public void translate(double dx, double dy) {
        for (int i = 0; i < POINT.length; ++i) {
            POINT[i] = POINT[i].translate(dx, dy);
        }
    }
}
