/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arbre_binaire_corrige;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author maillot
 */
public class ModeleArbreBDR<T extends Comparable<? super T> & Serializable> implements TreeModel {

  private NoeudBinaire<T> racine;
  private List<T> valeursEnOrdre = new ArrayList<>();
  private List<TreeModelListener> listeListener = new ArrayList<>();

  public ModeleArbreBDR(NoeudBinaire<T> racine) {
    this.racine = racine;
  }

  @Override
  public NoeudBinaire<T> getRoot() {
    return racine;
  }

  @Override
  public NoeudBinaire<T> getChild(Object o, int i) {
    NoeudBinaire<T> noeud = (NoeudBinaire<T>) o;
    if (i == 0) {
      return noeud.getFilsGauche();
    } else {
      return noeud.getFilsDroit();
    }
  }

  @Override
  public int getChildCount(Object o) {
    NoeudBinaire<T> noeud = (NoeudBinaire<T>) o;
    if (noeud.getFilsGauche() == null && noeud.getFilsDroit() == null) {
      return 0;
    } else if (noeud.getFilsGauche() != null && noeud.getFilsDroit() != null) {
      return 2;
    } else {
      return 1;
    }
  }

  public void ajouter(T valeur) {
    NoeudBinaire<T> noeud = new NoeudBinaire<T>(valeur);
    if (racine == null) {
      racine = noeud;
    } else {

      ajouter(racine, noeud);

    }
    fireTreeStructureChanged(racine);
  }

  private void ajouter(NoeudBinaire<T> n, NoeudBinaire<T> nouveau) {
    if (nouveau.getValeur().compareTo(n.getValeur()) < 0) {
      if (n.getFilsGauche().estVide()) {
        n.setFilsGauche(nouveau);
      } else {
        ajouter(n.getFilsGauche(), nouveau);
      }
    } else {

      if (n.getFilsDroit().estVide()) {
        n.setFilsDroit(nouveau);
      } else {
        ajouter(n.getFilsDroit(), nouveau);
      }
    }

  }

  @Override
  public boolean isLeaf(Object o) {
    NoeudBinaire<T> noeud = (NoeudBinaire<T>) o;
    //return noeud.getFilsGauche() == null && noeud.getFilsDroit() == null;
    return noeud.getValeur() == null;
  }

  @Override
  public void valueForPathChanged(TreePath tp, Object o) {
  }

  @Override
  public int getIndexOfChild(Object p, Object e) {

    NoeudBinaire<T> parent = (NoeudBinaire<T>) p;
    NoeudBinaire<T> enfant = (NoeudBinaire<T>) e;
    if (parent.getFilsGauche() == enfant) {
      return 0;
    }
    if (parent.getFilsDroit() == enfant) {
      return 1;
    }
    return -1;


  }

  protected void fireTreeStructureChanged(NoeudBinaire<T> racine) {
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

  public List<T> getValeursEnOrdre(NoeudBinaire<T> n) {
    valeursEnOrdre.clear();

    setValeursEnOrdre(n);
    return valeursEnOrdre;
  }

  private void setValeursEnOrdre(NoeudBinaire<T> n) {
    if (!isLeaf(n)) {
      setValeursEnOrdre(n.getFilsGauche());
      valeursEnOrdre.add(n.getValeur());
      setValeursEnOrdre(n.getFilsDroit());
    }
  }
}
