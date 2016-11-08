/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jframe;

import javax.swing.JFrame;

/**
 *
 * @author yvan
 */
public class MaFrame extends JFrame {
  public MaFrame() {
    super("La frame de ma vie");
    pack();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
  }
}
