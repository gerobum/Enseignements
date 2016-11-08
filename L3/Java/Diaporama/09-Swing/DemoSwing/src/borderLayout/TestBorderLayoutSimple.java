
package borderLayout;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class TestBorderLayoutSimple extends JFrame {
    public TestBorderLayoutSimple() {
        super("Test BorderLayout");
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new JLabel("Au nord les corons"), "North");
        getContentPane().add(new JLabel("On dirait le sud"), "South");
        getContentPane().add(new JLabel("A l'est, l'Eden"), "East");
        getContentPane().add(new JLabel("A l'ouest rien de nouveau"), "West");
        getContentPane().add(new JLabel(" Le centre du monde "), "Center");   
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
