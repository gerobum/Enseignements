/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package double_vision;

import double_vision.dialogue.CreerUnePersonne;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

/**
 *
 * @author maillot
 */
public class UnArbre extends JFrame {

  // -- 8 -------------------
  // private Personne racine;
  // -- 8 -------------------
  private JTree arbre;
  private JLabel info;
  private JButton ajout = new JButton("+");
  private JButton suppr = new JButton("-");
  private CreerUnePersonne creerUnePersonne = new CreerUnePersonne();
  private Personne dernierSelection;

  public UnArbre() {
    super("Un arbre r�actif");

    miseEnPage();
    miseEnPlaceDesEcouteurs();
  }

  private Personne getDescendance() {
    Personne Andrea = new Personne("Andréa", 67);
    Personne Genevieve = new Personne("Geneviève", 42);
    Personne Colette = new Personne("Colette", 27);
    Andrea.ajouterEnfant(Genevieve);
    Andrea.ajouterEnfant(Colette);
    Andrea.ajouterEnfant(new Personne("Michel", 45));

    Genevieve.ajouterEnfant(new Personne("Thierry", 22));

    Colette.ajouterEnfant(new Personne("Franeoise", 8));
    Colette.ajouterEnfant(new Personne("Patrick", 8));



    return Andrea;
  }

  private void miseEnPage() {
    Descendance dtm = new Descendance(getDescendance());

    arbre = new JTree(dtm);
    // --8-----------------
    arbre.setEditable(true);
    dtm.addTreeModelListener(new TreeModelListener() {
      @Override
      public void treeNodesChanged(TreeModelEvent tme) {
        // R�cup�ration du noeud � changer
        Personne node = (Personne) (tme.getTreePath().getLastPathComponent());
        // R�cup�ration de la cha�ne tap�e
        String texte = (String) tme.getSource();
        Pattern p = Pattern.compile("\\s*(\\p{Alnum}+)\\s*(\\p{Digit}+)\\s*");
        Matcher m = p.matcher(texte);
        if (m.matches()) {
          node.setNom(m.group(1));
          node.setAge(Integer.parseInt(m.group(2)));
        } else {
          creerUnePersonne.setVisible(true);
          if (creerUnePersonne.isOk()) {
            node.setNom(creerUnePersonne.getNom());
            node.setAge(creerUnePersonne.getAge());
          } else {
            node.setNom(dernierSelection.getNom());
            node.setAge(dernierSelection.getAge());
          }
        }
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
    // --8-----------------
    info = new JLabel("----");

    info.setFont(info.getFont().deriveFont(30f));
    // ----------------------------------------------------------
    DefaultTreeCellRenderer dtcr = new DefaultTreeCellRenderer();

    dtcr.setFont(
            new JLabel().getFont().deriveFont(30f));
    arbre.setCellRenderer(dtcr);
    // ----------------------------------------------------------

    getContentPane().add(new JScrollPane(arbre));
    getContentPane().add(info, "North");
    JPanel p = new JPanel();

    p.add(ajout);

    p.add(suppr);

    getContentPane().add(p, "South");
    setVisible(
            true);
    pack();

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private void miseEnPlaceDesEcouteurs() {
    arbre.addTreeSelectionListener(new TreeSelectionListener() {
      @Override
      public void valueChanged(TreeSelectionEvent tse) {

        dernierSelection = (Personne) tse.getPath().getLastPathComponent();
        info.setText(dernierSelection.toString());
      }
    });

    ajout.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ae) {
        TreePath path = arbre.getSelectionPath();

        if (((Descendance) (arbre.getModel())).getRoot().getAge() == -1) {
          creerUnePersonne.setVisible(true);
          if (creerUnePersonne.isOk()) {
            Descendance dtm = (Descendance) arbre.getModel();
            ((Descendance) (arbre.getModel())).setRoot(new Personne(creerUnePersonne.getNom(), creerUnePersonne.getAge()));
            arbre.updateUI();
          }
        } else if (path != null) {
          Personne n = (Personne) path.getLastPathComponent();
          creerUnePersonne.setVisible(true);
          if (creerUnePersonne.isOk()) {
            n.ajouterEnfant(new Personne(creerUnePersonne.getNom(), creerUnePersonne.getAge()));
            arbre.updateUI();
          }
        }
      }
    });

    suppr.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ae) {
        TreePath path = arbre.getSelectionPath();
        if (path != null) {
          Personne n = (Personne) path.getLastPathComponent();
          if (n.getPere() == null) {
            Descendance dtm = (Descendance) arbre.getModel();
            dtm.setRoot(new Personne("", -1));
            arbre.setSelectionPath(null);
          } else {
            n.getPere().retirerEnfant(n);
          }
          arbre.updateUI();
        }

      }
    });
  }

  public static void main(String[] args) {
    new UnArbre();
  }
}
