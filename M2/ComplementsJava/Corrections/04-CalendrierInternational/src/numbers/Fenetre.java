package numbers;

import javax.swing.JFrame;
/**
 *
 * @author maillot
 */
public class Fenetre extends JFrame {
    public Fenetre() {
        getContentPane().add(new LocalizedNumbers());
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new Fenetre();
    }
}
