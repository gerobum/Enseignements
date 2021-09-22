/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algo;

import java.util.Enumeration;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 *
 * @author maillot
 */
public class ArbreAlgo extends JFrame {
    //Where instance variables are declared:

    private JTree tree;
    private static Random random = new Random();

    public ArbreAlgo() {
        
        
 


        DefaultMutableTreeNode top =
                new DefaultMutableTreeNode("La racine");
        createNodes(top);
        tree = new JTree(top) {

            @Override
            public String convertValueToText(Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                //return super.convertValueToText(value, selected, expanded, leaf, row, hasFocus);
                if (selected) {
                    if (leaf) {
                        return "(" + value.toString() + "-)";
                    } else {
                        return value.toString() + "-";
                    }
                } else {
                    if (leaf) {
                        return "(" + value.toString() + "+)";
                    }
                     else {
                        return value.toString() + "+";
                    }
                }
            }
        };

        JScrollPane treeView = new JScrollPane(tree);

        getContentPane().add(treeView);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();


    }

    private void createNodes(DefaultMutableTreeNode top) {
        for (int i = 0; i < 100 + random.nextInt(101); i++) {
            top.add(new DefaultMutableTreeNode((char) ('A' + i)));
        }
    }

    public static void main(String[] args) {
        new ArbreAlgo();
    }
}
