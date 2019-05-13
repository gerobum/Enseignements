/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package cf1;

/**
 *
 * @author yvan
 */
public class Triangle {

    private Segment c1, c2, c3;

    public Triangle(Segment c1, Segment c2, Segment c3) {
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
    }

    public Segment getC1() {
        return c1;
    }

    public void setC1(Segment c1) {
        this.c1 = c1;
    }

    public Segment getC2() {
        return c2;
    }

    public void setC2(Segment c2) {
        this.c2 = c2;
    }

    public Segment getC3() {
        return c3;
    }

    public void setC3(Segment c3) {
        this.c3 = c3;
    }

    public double getPerimetre() {
        return c1.getLongueur() + c2.getLongueur() + c3.getLongueur();
    }

    public Point getBaryCentre() {
        Point a = c1.getP1();
        Point b = c2.getP1();
        Point c = c3.getP1();

        return new Point((a.getX() + b.getX() + c.getX()) / 3, (a.getY() + b.getY() + c.getY()) / 3);
    }

    public double getSurface() {
        double a = c1.getLongueur();
        double b = c2.getLongueur();
        double c = c3.getLongueur();
        double p = a + b + c;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}
