/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package cf1;

/**
 *
 * @author yvan
 */
public class Segment {
    private Point p1, p2;
    
    public Segment(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public double getLongueur() {
        return Math.sqrt((p2.getX()-p1.getX())*(p2.getX()-p1.getX())+(p2.getY()-p1.getY())*(p2.getY()-p1.getY()));
    }

    @Override
    public String toString() {
        return "["+p1+" ; "+p2+"]";
    }
}
