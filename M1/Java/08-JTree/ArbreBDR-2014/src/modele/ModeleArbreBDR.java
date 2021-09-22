/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import arbre.Arbre;
import arbre.NoeudBinaire;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author yvan
 */
public class ModeleArbreBDR<T extends Comparable<? super T> & Serializable> implements TreeModel {

  private List<TreeModelListener> listeListener = new ArrayList<>();
  private Arbre<T> arbre;

  public ModeleArbreBDR() {
    arbre = new Arbre<>();
  }

  @Override
  public Object getRoot() {
    return arbre.getRacine();
  }
  

  @Override
  public Object getChild(Object parent, int index) {
    NoeudBinaire<T> noeud = (NoeudBinaire<T>) parent;
    if (index == 0) {
      return noeud.getGauche();
    } else  {
      return noeud.getDroit();
    }
  }
  public void ajouter(T v) {
    arbre.ajouter(v);
    fireTreeStructureChanged(arbre.getRacine());
  }

  @Override
  public int getChildCount(Object parent) {
    NoeudBinaire<T> noeud = (NoeudBinaire<T>) parent;
    if (noeud.getGauche() == null && noeud.getDroit() == null) {
      return 0;
    } else {
      return 2;
    }
  }

  @Override
  public boolean isLeaf(Object parent) {
    return parent == NoeudBinaire.vide;
  }

  @Override
  public void valueForPathChanged(TreePath path, Object newValue) {
  }

  @Override
  public int getIndexOfChild(Object parent, Object child) {
    if (parent == null || child == null) {
      return -1;
    }
    NoeudBinaire<T> p = (NoeudBinaire<T>) parent;
    NoeudBinaire<T> e = (NoeudBinaire<T>) child;
    if (p.getGauche() == e) {
      return 0;
    } else if (p.getDroit() == e) {
      return 1;
    } else {
      return -1;
    }
  }

  private void fireTreeStructureChanged(NoeudBinaire<T> racine) {
    TreeModelEvent e = new TreeModelEvent(this, new Object[]{racine});
    for (TreeModelListener tml : listeListener) {
      tml.treeStructureChanged(e);
    }
  }

  @Override
  public void addTreeModelListener(TreeModelListener tl) {
    listeListener.add(tl);
  }

  @Override
  public void removeTreeModelListener(TreeModelListener tl) {
    listeListener.remove(tl);
  }
}
