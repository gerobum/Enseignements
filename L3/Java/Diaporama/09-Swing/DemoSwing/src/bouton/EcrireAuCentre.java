/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bouton;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class EcrireAuCentre extends JFrame {
  private JLabel centre = new JLabel("", JLabel.CENTER);
  private JButton nord = new JButton("Nord");
  private JButton sud = new JButton("Sud");
  private JButton est = new JButton("Est");
  private JButton ouest = new JButton("Ouest");
  public EcrireAuCentre() {
    getContentPane().add(nord, "North");
    getContentPane().add(sud, "South");
    getContentPane().add(est, "East");
    getContentPane().add(ouest, "West");
    getContentPane().add(centre, "Center");
    centre.setPreferredSize(new Dimension(200, 200));
    
    ActionListener action = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JButton bouton = (JButton) e.getSource();
        centre.setText(bouton.getText());
      }
    };
    nord.addActionListener(action);
    sud.addActionListener(action);
    est.addActionListener(action);
    ouest.addActionListener(action);
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    pack();
  }
  public static void main(String[] args) {
    new EcrireAuCentre();
  }
}
