package geometrie.non_mutable;

public class Triangle {

    private final Point[] POINT = new Point[3];

    public Triangle(Point a, Point b, Point c) {
        POINT[0] = a;
        POINT[1] = b;
        POINT[2] = c;
    }

    public Point getPoint(int i) {
        return POINT[i];
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
