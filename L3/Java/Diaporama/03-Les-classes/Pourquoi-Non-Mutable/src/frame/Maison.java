
package frame;


import geometrie.Ecran;
import geometrie.mutable.Triangle;
import geometrie.mutable.Carre;
import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import javax.swing.JFrame;

/**
 *
 * @author yvan
 */
public class Maison extends JFrame {
    private final Carre carre;
    private final Triangle triangle;
    private final Ecran ecran;
    

    public Maison(Carre carre, Triangle triangle) {
        this.carre = carre;
        this.triangle = triangle;
        ecran = new Ecran(600, -0.2, -0.2, 3.2, 3.2);
        
        setPreferredSize(new Dimension(ecran.COTE, ecran.COTE));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D G = (Graphics2D) g;
        G.transform(new AffineTransform(ecran.COTE/2.0, -ecran.COTE/2.0, 0, 0, ecran.COTE/2.0, ecran.COTE/2.0));
        G.setStroke(new BasicStroke(0.1f));
        G.drawLine(0, 0, 100, 100);
        G.draw(new Arc2D.Double(0,0,0.5,0.5,0,300,Arc2D.CHORD));
        ecran.setGraphics(g);
        //g.fillOval(100, 100, 100, 100);
        for(int i = 0; i < 3; ++i) {
            ecran.dessinerPoint(triangle.getPoint(i));
            System.out.println(triangle.getPoint(i));
        }
    }

    public Carre getCarre() {
        return carre;
    }

    public Triangle getTriangle() {
        return triangle;
    }
    

    
    
    
    
}
