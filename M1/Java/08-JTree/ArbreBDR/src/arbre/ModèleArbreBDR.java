// #### 9/9

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arbre;

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

// #### 1/1 (Q 4.0.1)
public class ModèleArbreBDR<T extends Comparable<? super T> & Serializable> implements TreeModel {

// #### 0.25/0.25 (Q 4.0.2)
    private NoeudBinaire<T> racine;
    private List<TreeModelListener> listeListeners = new ArrayList<TreeModelListener>();

// #### 0.25/0.25 (Q 4.1.1)
    public ModèleArbreBDR(NoeudBinaire<T> racine) {
        if (racine == null) {
            this.racine = NoeudBinaire.vide;
        } else {
            this.racine = racine;
        }
    }
// #### 0.25/0.25 (Q 4.1.2)
    public ModèleArbreBDR() {
        this(null);
    }

// #### 0.25/0.25 (Q 4.2.1)
    @Override
    public NoeudBinaire<T> getRoot() {
        return racine;
    }


// #### 0.5/0.5 (Q 4.2.2)
    @Override
    public NoeudBinaire<T> getChild(Object o, int i) {
        NoeudBinaire<T> pere = (NoeudBinaire<T>) o;
        if (i == 0) {
            return pere.getFilsGauche();
        } else {
            return pere.getFilsDroit();
        }
    }

 
// #### 0.25/0.25 (Q 4.2.3)
   @Override
    public int getChildCount(Object o) {
        NoeudBinaire<T> pere = (NoeudBinaire<T>) o;
        if (pere.getValeur() == null) {
            return 0;
        } else {
            return 2;
        }
    }
   
 
// #### 1/1 (Q 4.2.4)
    @Override
    public int getIndexOfChild(Object o, Object o1) {
        if (o == null || o1 == null) {
            return -1;
        }
        NoeudBinaire<T> pere = (NoeudBinaire<T>) o;
        if (pere.getFilsGauche() == o1) {
            return 0;
        }
        if (pere.getFilsDroit() == o1) {
            return 1;
        }
        return -1;
    }
     
// #### 0.25/0.25 (Q 4.2.5)
    @Override
    public boolean isLeaf(Object o) {
        NoeudBinaire<T> pere = (NoeudBinaire<T>) o;
        return pere.estVide();
    }

    
// #### 0.25/0.25 (Q 4.2.6)

    @Override
    public void addTreeModelListener(TreeModelListener tl) {
        listeListeners.add(tl);
    }
   
// #### 0.25/0.25 (Q 4.2.7)
    @Override
    public void removeTreeModelListener(TreeModelListener tl) {
        listeListeners.remove(tl);
    }
    
    @Override
    public void valueForPathChanged(TreePath tp, Object o) {
    }
  
// #### 1.5/1.5 (Q 4.3.1)
    private void ajouter(NoeudBinaire<T> parent, NoeudBinaire<T> newChild) {
        if (newChild.getValeur().compareTo(parent.getValeur()) < 0) {
            if (parent.getFilsGauche().estVide()) {
                parent.setFilsGauche(newChild);
            } else {
                ajouter(parent.getFilsGauche(), newChild);
            }
        } else {
            if (parent.getFilsDroit().estVide()) {
                parent.setFilsDroit(newChild);
            } else {
                ajouter(parent.getFilsDroit(), newChild);
            }
        }
    }

  
// #### 1/1 (Q 4.3.2)
    public void ajouter(T value) {
        NoeudBinaire<T> newChild = new NoeudBinaire<T>(value);
        if (racine.estVide()) {
            racine = newChild;
        } else {
            ajouter(racine, newChild);
        }
        TreeModelEvent tme = new TreeModelEvent(this, new Object[] {racine});
        for(TreeModelListener tml : listeListeners) {
            tml.treeStructureChanged(tme);
        }
    }
      
// #### 2/2 (Q 4.3.3)
    public List<T> getValeursEnOrdre(NoeudBinaire<T> parent) {
        List<T> valeurs = new ArrayList<T>();
        if (parent != null) {
            parcourir(parent, valeurs);
        }
        return valeurs;
    }
    
    private void parcourir(NoeudBinaire<T> parent, List<T> valeurs) {
        if (!parent.estVide()) {
            parcourir(parent.getFilsGauche(), valeurs);
            valeurs.add(parent.getValeur());
            parcourir(parent.getFilsDroit(), valeurs);
        }
    }
}
