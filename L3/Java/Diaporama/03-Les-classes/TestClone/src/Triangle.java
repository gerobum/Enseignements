
/**
 *
 * @author yvan
 */
public class Triangle {

    private final Point a;
    private Point b, c;
    
    public void setB(Point b) {
        this.b = b;
    }

    public void setC(Point c) {
        this.c = c;
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    public Point getC() {
        return c;
    }

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    protected Triangle clone() throws CloneNotSupportedException {
        Triangle t = (Triangle) super.clone();
        //t.a = a.clone();
        return t;
    }

}
