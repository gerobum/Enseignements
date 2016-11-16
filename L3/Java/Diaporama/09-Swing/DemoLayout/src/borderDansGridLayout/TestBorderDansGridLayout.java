/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package borderDansGridLayout;


import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author yvan
 */
public class TestBorderDansGridLayout extends JFrame {
     private JPanelBorder a1, a2, a3, a4;
    private GridLayout gridLayout = new GridLayout(2,2,10,10);

   

    public TestBorderDansGridLayout() {
        super("Test BorderLayout");
        a1 = new JPanelBorder(Color.BLUE);
        a2 = new JPanelBorder(Color.RED);
        a3 = new JPanelBorder(Color.YELLOW);
        a4 = new JPanelBorder(Color.GREEN);

        getContentPane().setLayout(gridLayout);


        placerLesElements();


        pack();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TestBorderDansGridLayout();
            }
        });
    }

    private void placerLesElements() {
        getContentPane().add(a1);
        getContentPane().add(a2);
        getContentPane().add(a3);
        getContentPane().add(a4);

    }


 
}
