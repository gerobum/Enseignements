/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

import arbre.NoeudBinaire;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import modele.ModeleArbreBDR;

/**
 *
 * @author maillot
 */
public class FrameArbreBDR extends JFrame {

  private JTextField entrer = new JTextField(3);
  private JLabel enOrdre = new JLabel(" ", JLabel.LEFT);
  private JTree arbre;
  ModeleArbreBDR<Integer> modele;

  public FrameArbreBDR() {
    super("Arbre binaire de recherche");
    JPanel nord = new JPanel();
    nord.add(new JLabel("Un entier", JLabel.RIGHT));
    nord.add(entrer);

    DefaultTreeCellRenderer dtcr = new DefaultTreeCellRenderer();
    dtcr.setLeafIcon(null);
    dtcr.setOpenIcon(null);
    dtcr.setClosedIcon(null);

    dtcr.setAutoscrolls(true);

    modele = new ModeleArbreBDR<Integer>();

    arbre = new JTree(modele);

    arbre.setCellRenderer(dtcr);

    getContentPane().add(nord, "North");
    getContentPane().add(new JScrollPane(arbre));
    getContentPane().add(enOrdre, "South");

    miseEnPlaceDesEcouteurs();



    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    pack();
  }

  private void miseEnPlaceDesEcouteurs() {

    addWindowListener(new WindowListener() {
      @Override
      public void windowOpened(WindowEvent we) {
      }

      @Override
      public void windowClosing(WindowEvent we) {
      }

      @Override
      public void windowClosed(WindowEvent we) {
      }

      @Override
      public void windowIconified(WindowEvent we) {
      }

      @Override
      public void windowDeiconified(WindowEvent we) {
      }

      @Override
      public void windowActivated(WindowEvent we) {
      }

      @Override
      public void windowDeactivated(WindowEvent we) {
      }
    });

    arbre.addTreeSelectionListener(new TreeSelectionListener() {
      @Override
      public void valueChanged(TreeSelectionEvent tse) {
        NoeudBinaire<Integer> n = (NoeudBinaire<Integer>) tse.getPath().getLastPathComponent();

        enOrdre.setText(n.parcours());
      }
    });
    entrer.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ae) {

        modele.ajouter(Integer.parseInt(entrer.getText()));

        System.out.println(modele.getRoot());
      }
    });

    modele.addTreeModelListener(new TreeModelListener() {
      @Override
      public void treeNodesChanged(TreeModelEvent tme) {
        System.out.println("treeNodesChanged");
      }

      @Override
      public void treeNodesInserted(TreeModelEvent tme) {
        System.out.println("treeNodesInserted");
      }

      @Override
      public void treeNodesRemoved(TreeModelEvent tme) {
        System.out.println("treeNodesRemoved");
      }

      @Override
      public void treeStructureChanged(TreeModelEvent tme) {
        System.out.println("treeStructureChanged");

        for (int i = 0; i < arbre.getRowCount(); i++) {
          arbre.expandRow(i);
        }

      }
    });
  }

  public static void main(String[] args) {
    new FrameArbreBDR();
  }
}
