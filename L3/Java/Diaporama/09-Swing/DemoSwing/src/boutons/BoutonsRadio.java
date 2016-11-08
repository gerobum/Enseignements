/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package boutons;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

/**
 *
 * @author yvan
 */
public class BoutonsRadio extends JFrame implements ActionListener{
  private JRadioButton rb1, rb2, rb3;
  private ButtonGroup rb;
  private JLabel sud;
  public BoutonsRadio() {
    rb1 = new JRadioButton("1");
    rb2 = new JRadioButton("2");
    rb3 = new JRadioButton("3");
    rb = new ButtonGroup();
    rb.add(rb1);
    rb.add(rb2);
    rb.add(rb3);
    sud = new JLabel("", JLabel.CENTER);
    setLayout(new FlowLayout());
    getContentPane().add(rb1);
    getContentPane().add(rb2);
    getContentPane().add(rb3);
    getContentPane().add(sud);
    rb1.addActionListener(this);
    rb2.addActionListener(this);
    rb3.addActionListener(this);
    
    
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    pack();
  }

  @Override
  public void actionPerformed(ActionEvent ae) {
    Enumeration<AbstractButton> elements = rb.getElements();
    while(elements.hasMoreElements()) {
      AbstractButton ab = elements.nextElement();
      if (ab.isSelected()) {
        sud.setText(ab.getText());
        return;
      }
    }
  }
  
  public static void main(String[] args) {
    new BoutonsRadio();
  }
}
