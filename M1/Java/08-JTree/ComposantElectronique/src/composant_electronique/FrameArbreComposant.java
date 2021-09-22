/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package composant_electronique;

import composant_electronique.images.Images;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

/**
 *
 * @author maillot
 */
public class FrameArbreComposant extends JFrame {

    private JTree arbre;
    private JButton composant;
    private JButton et, ou;
    private JButton retrait;

    public FrameArbreComposant() {
        //TreeModel
        Composant racine = new Composant();

        ModelArbreComposant model = new ModelArbreComposant(racine);
        model.addTreeModelListener(new TreeModelListener() {

            @Override
            public void treeNodesChanged(TreeModelEvent tme) {
            }

            @Override
            public void treeNodesInserted(TreeModelEvent tme) {
            }

            @Override
            public void treeNodesRemoved(TreeModelEvent tme) {
            }

            @Override
            public void treeStructureChanged(TreeModelEvent tme) {
            }
        });

        DefaultTreeCellRenderer dtcr = new DefaultTreeCellRenderer() {

            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
                if (value instanceof ComposantSimple) {
                    setIcon(((ComposantSimple) value).getIcone());
                }

                return this;
            }
        };

        dtcr.setLeafIcon(Images.feuille);
        dtcr.setOpenIcon(Images.brancheOuverte);
        dtcr.setClosedIcon(Images.brancheFermée);
        dtcr.setAutoscrolls(true);
        dtcr.setFont(new JLabel().getFont().deriveFont(25f));

        arbre = new JTree(model);
        arbre.setCellRenderer(dtcr);
        arbre.setShowsRootHandles(true);
        retrait = new JButton("-");

        JScrollPane vueArbre = new JScrollPane(arbre);
        vueArbre.setPreferredSize(new Dimension(50, 100));
        JPanel sud = new JPanel();

        retrait.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    int ls = arbre.getSelectionRows()[0];
                    TreePath ps = arbre.getSelectionPath();
                    Composant père = (Composant) ps.getParentPath().getLastPathComponent();
                    ComposantElectronique noeud = (ComposantElectronique) arbre.getSelectionPath().getLastPathComponent();

                    ModelArbreComposant model = (ModelArbreComposant) arbre.getModel();
                    model.enlever(père, noeud);
                    for(int i = 0; i < arbre.getRowCount(); i++)
                        arbre.expandRow(i);


                } catch (Throwable ex) {
                }
            }
        });

        sud.add(retrait);

        JPanel est = new JPanel(new GridLayout(0, 1));


        composant = new JButton("Composant");

        ActionListener action = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    int row = arbre.getSelectionRows()[0];
                    Composant c = (Composant) arbre.getSelectionPath().getLastPathComponent();
                    ModelArbreComposant model = (ModelArbreComposant) arbre.getModel();

                    if (ae.getSource() == composant) {
                        model.ajouter(c, new Composant());
                    } else if (ae.getSource() == et) {
                        model.ajouter(c, new PorteET());
                    } else if (ae.getSource() == ou) {
                        model.ajouter(c, new PorteOU());
                    }
                    for(int i = 0; i < arbre.getRowCount(); i++)
                        arbre.expandRow(i);
                } catch (Throwable ex) {
                }
            }
        };

        composant.addActionListener(action);

        ou = new JButton("OU");
        et = new JButton("ET");
        ou.addActionListener(action);
        et.addActionListener(action);




        est.add(composant);
        est.add(ou);
        est.add(et);

        JPanel pest = new JPanel();
        pest.add(est);

        getContentPane().add(sud, "North");

        getContentPane().add(pest, "East");

        getContentPane().add(vueArbre);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }

    public static void main(String[] args) {
        new FrameArbreComposant();
    }
}
