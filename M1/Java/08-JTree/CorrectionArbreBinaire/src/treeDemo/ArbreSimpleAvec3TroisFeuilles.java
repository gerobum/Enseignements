/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package treeDemo;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;


/**
 *
 * @author Maillot
 */
public class ArbreSimpleAvec3TroisFeuilles extends JFrame {
    private DefaultTreeModel aTreeModel;
    private JTree aTree;

    public ArbreSimpleAvec3TroisFeuilles() {
        aTreeModel = new DefaultTreeModel(new DefaultMutableTreeNode("Racine"));
        DefaultMutableTreeNode racine = (DefaultMutableTreeNode) aTreeModel.getRoot();
        for(int i = 0; i < 3; i++)
            racine.add(new DefaultMutableTreeNode("feuille "+i));
        aTree = new JTree(aTreeModel);
        getContentPane().add(new JScrollPane(aTree));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        aTreeModel.insertNodeInto(new DefaultMutableTreeNode("Insertion"), racine, 2);
        racine.getFirstLeaf().add(new DefaultMutableTreeNode("feuille"));
        pack();
    }

    public static void main(String[] args) {
        new ArbreSimpleAvec3TroisFeuilles();
    }


}
