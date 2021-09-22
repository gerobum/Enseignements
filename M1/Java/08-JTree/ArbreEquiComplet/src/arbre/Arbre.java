/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbre;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import modele.ModeleAEC;

/**
 *
 * @author yvan
 */
public class Arbre {
  
  public static void main(String[] args) {
    // Création de données organisées hiérarchiquement
    // à l'aide de DefaultMutableTreeNode
    
    // Affichage de l'arbre comme un composant Swing
    JTree arbre = new JTree(new ModeleAEC(3));
    JFrame f = new JFrame("Un arbre simple");
    f.getContentPane().add(arbre);
    f.setVisible(true); 
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.pack();
  }
}
