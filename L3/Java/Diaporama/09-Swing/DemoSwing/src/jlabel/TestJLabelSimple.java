
package jlabel;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TestJLabelSimple extends JFrame {
 public TestJLabelSimple() {
   JLabel label = new JLabel("La belle frame", JLabel.CENTER);
   label.setForeground(Color.white);
   label.setBackground(Color.blue);
   label.setOpaque(true);         
         
   getContentPane().add(label);
   
   getContentPane().setLayout(null);
   label.setBounds(10, 10, 300, 100);
   
   setVisible(true);
   pack();
   setDefaultCloseOperation(EXIT_ON_CLOSE);
 }
  public static void main(String[] args) {
    new TestJLabelSimple();
  }
}
