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
public class LanceFrame {
  public static void main(String[] args) {
    // Lancement de "ma frame spécialisée"
    new MaFrame();
    // Créetion et lancement d'une autre
    JFrame frame = new JFrame("Une autre frame");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.pack();
  }
}
