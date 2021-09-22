/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package treeDemo;

import java.io.File;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.basic.BasicTreeUI.TreeSelectionHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Maillot
 */
public class ArborescenceDeFichiers extends JFrame {

    private DefaultTreeModel aTreeModel;
    private JTree aTree;

    public static void remplir(DefaultMutableTreeNode noeud, File rep) {


        if (rep.isDirectory()) {
            for (File f : rep.listFiles()) {
                DefaultMutableTreeNode feuille = new DefaultMutableTreeNode(f.getName());
                noeud.add(feuille);
                remplir(feuille, f);
            }
        }
    }

    public ArborescenceDeFichiers(String chemin) {
        File rep = new File(chemin);
        aTreeModel = new DefaultTreeModel(new DefaultMutableTreeNode(rep));
        DefaultMutableTreeNode racine = (DefaultMutableTreeNode) aTreeModel.getRoot();
        remplir(racine, rep);

        aTree = new JTree(aTreeModel);
        getContentPane().add(new JScrollPane(aTree));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        aTree.addTreeSelectionListener(new TreeSelectionListener() {

            public void valueChanged(TreeSelectionEvent e) {
                System.out.println(e.getPath().getLastPathComponent());
            }
        });
        pack();
    }
    
    public ArborescenceDeFichiers() {
        this(".");
    }

    public static void main(String[] args) {
        new ArborescenceDeFichiers("/media/4D87-F2F5/Travail/Enseignement/2011-2012/M1/Java");
    }
}
