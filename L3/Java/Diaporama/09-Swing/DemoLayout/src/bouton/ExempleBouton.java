/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bouton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author yvan
 */
public class ExempleBouton extends JFrame {
  private JButton bouton = new JButton("0 fois");
  public ExempleBouton() {
    getContentPane().add(bouton);
    bouton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        Scanner sc = new Scanner(b.getText());
        int n = sc.nextInt();
        b.setText((n+1) + " fois");
      }
    });
    
    pack();
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }
  public static void main(String[] args) {
    new ExempleBouton();
  }
}
