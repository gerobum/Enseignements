
package graphic;

import geom.Point;
import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author yvan
 */
public class Fenetre extends JFrame {

    private double xmin, ymin, xmax, ymax;
    private Carre carre;
    
    public Fenetre(double xmin, double ymin, double xmax, double ymax) {
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
        
        carre = new Carre(xmin, ymin, xmax, ymax);
        
        getContentPane().add(carre);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // setVisible(true);
        pack();
    }
    
    public void set(Point p, Color c) {
        carre.set(p, c);
    }
    
    
}
