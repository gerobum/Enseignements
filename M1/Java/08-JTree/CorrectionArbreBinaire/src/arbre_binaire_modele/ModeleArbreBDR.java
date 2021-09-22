/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arbre_binaire_modele;

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
    List<TreeModelListener> listeListeners = new ArrayList<TreeModelListener>();

    public ModeleArbreBDR() {
        this(null);
    }
    
    

    public ModeleArbreBDR(NoeudBinaire<T> racine) {
        if (racine == null) {
            this.racine = NoeudBinaire.vide;
        } else {
            this.racine = racine;
        }
    }

    @Override
    public NoeudBinaire<T> getRoot() {
        return racine;
    }

    @Override
    public NoeudBinaire<T> getChild(Object o, int i) {
        NoeudBinaire<T> n = (NoeudBinaire<T>) o;
        if (i == 0) {
            return n.getFilsGauche();
        } else {
            return n.getFilsDroit();
        }
    }

    @Override
    public int getChildCount(Object o) {
        NoeudBinaire<T> n = (NoeudBinaire<T>) o;
        if (n.getFilsDroit() == null && n.getFilsGauche() == null) {
            return 0;
        } else //if (n.getFilsDroit() != null && n.getFilsGauche() != null) {
            return 2;
        /*} else {
            return 1;
        }*/
    }

    @Override
    public boolean isLeaf(Object o) {
        NoeudBinaire<T> n = (NoeudBinaire<T>) o;
        return n.getFilsGauche() == null && n.getFilsDroit() == null;
    }

    @Override
    public void valueForPathChanged(TreePath tp, Object o) {
    }

    @Override
    public int getIndexOfChild(Object o, Object o1) {
        try {
            NoeudBinaire<T> pere = (NoeudBinaire<T>) o;
            NoeudBinaire<T> enfant = (NoeudBinaire<T>) o1;
            if (pere.getFilsGauche() == enfant) {
                return 0;
            }
            if (pere.getFilsDroit() == enfant) {
                return 1;
            }
            return -1;
        } catch (Throwable ex) {
            return -1;
        }
    }

    @Override
    public void addTreeModelListener(TreeModelListener tl) {
        listeListeners.add(tl);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener tl) {
        listeListeners.remove(tl);
    }

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

    public void ajouter(T value) {
        NoeudBinaire<T> n = new NoeudBinaire<>(value);
        if (racine.estVide()) {
            racine = n;
        } else {
            ajouter(racine, n);
        }

        TreeModelEvent tme = new TreeModelEvent(this, new Object[]{racine});
        for (TreeModelListener tml : listeListeners) {
            tml.treeStructureChanged(tme);
        }
    }

    public List<T> getValeursEnOrdre(NoeudBinaire<T> n) {
        List<T> liste = new ArrayList<T>();
        if (n != null) {
            parcours(n, liste);
        }
        return liste;
    }

    private void parcours(NoeudBinaire<T> n, List<T> liste) {
        if (!n.estVide()) {
            parcours(n.getFilsGauche(), liste);
            liste.add(n.getValeur());
            parcours(n.getFilsDroit(), liste);
        }
    }
}
