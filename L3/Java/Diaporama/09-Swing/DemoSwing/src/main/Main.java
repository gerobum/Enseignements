/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author yvan
 */
public class Main {
  public static void main(String[] args) {
    JFrame jf = new JFrame();
    JButton jb = new JButton("B");
    JButton jb1 = new JButton("A1");
    JButton jb2 = new JButton("A2");
    JButton jb3 = new JButton("A3");
    JButton jb4 = new JButton("A4");
    jb.setLayout(new GridLayout(2, 2));
    jb.add(jb1);
    jb.add(jb2);
    jb.add(jb3);
    jb.add(jb4);
    jf.getContentPane().add(jb);
    jf.setVisible(true);
    jf.pack();
  }
}
