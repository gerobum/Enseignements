/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrie;

import geometrie.mutable.Point;
import java.awt.Graphics;

/**
 *
 * @author yvan
 */
public class Ecran {
    public final int COTE;
    public final double xmin, xmax, ymin, ymax;
    private Graphics graphics;

    public Ecran(int COTE, double xmin, double ymin, double xmax, double ymax) {
        this.COTE = COTE;
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
    }
    private Pixel conversion(double x, double y) {
        System.out.println(x);
        System.out.println(x*COTE/(xmax-xmin)+COTE/(xmax-xmin));
        System.out.println(-y*COTE/(ymax-ymin)+COTE/(ymax-ymin));
        return new Pixel((int)(x*COTE/(xmax-xmin)+COTE/(xmax-xmin)), (int)(-y*COTE/(ymax-ymin)+COTE/(ymax-ymin)));
    }

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }
    
    public void dessinerPoint(Point pt) {
        dessinerPoint(pt.getX(), pt.getY());
    }
    
    public void dessinerPoint(double x, double y) {
        Pixel p = conversion(x, y);
        System.out.println(p.x+","+p.y);
        graphics.drawOval(p.x-10, p.y-10, p.x+10, p.y+10);
    }
    
}
