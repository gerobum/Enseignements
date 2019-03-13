package graphic;

import geom.Point;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author yvan
 */
public class Carre extends Canvas {

    private double xmin, ymin, xmax, ymax;
    private Set<Couple> points = new HashSet<>();
    private final int width = 500, height = 500;

    private class Couple {
        public final Color c;
        public final Point p;

        public Couple(Color c, Point p) {
            this.c = c;
            this.p = p;
        }                
    }

    public Carre(double xmin, double ymin, double xmax, double ymax) {
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
        setPreferredSize(new Dimension(width, height));

    }

    public synchronized void set(Point p, Color c) {
        points.add(new Couple(c, p));
    }

    @Override
    public synchronized void paint(Graphics g) {
        for (Couple c : points) {
            g.setColor(c.c);
            g.drawLine(convertX(c.p.getX()), convertY(c.p.getY()), convertX(c.p.getX()), convertY(c.p.getY()));
        }
    }

    private int convertX(double x) {
        return (int) ((width / (xmax - xmin)) * (x - xmin));
    }

    private int convertY(double y) {
        return (int) ((height / (ymax - ymin)) * (y - ymin));
    }

}
