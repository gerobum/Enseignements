/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package composant_electronique;

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
public class ModelArbreComposant implements TreeModel {

    private ComposantElectronique racine;
    private List<TreeModelListener> listeListener = new ArrayList<TreeModelListener>();

    public ModelArbreComposant(ComposantElectronique racine) {
        this.racine = racine;
    }

    @Override
    public ComposantElectronique getRoot() {
        return racine;
    }

    @Override
    public ComposantElectronique getChild(Object o, int i) {
        try {
            Composant ce = (Composant) o;
            return ce.getComposant(i);
        } catch (Throwable ex) {
            return null;
        }
    }

    @Override
    public int getChildCount(Object o) {
        try {
            Composant ce = (Composant) o;
            return ce.nbComposant();
        } catch (Throwable ex) {
            return 0;
        }
    }

    public void enlever(Composant père, ComposantElectronique noeud) {
        père.retirer(noeud);
        fireTreeStructureChanged(père);
    }

    public void ajouter(Composant noeud, ComposantElectronique nouveau) {
        noeud.ajouter(nouveau);
        fireTreeStructureChanged(nouveau);
    }

    @Override
    public boolean isLeaf(Object o) {
        return o instanceof ComposantSimple;
    }

    @Override
    public void valueForPathChanged(TreePath tp, Object o) {
    }

    @Override
    public int getIndexOfChild(Object p, Object e) {
        try {
        Composant pere = (Composant) p;
        for(int i = 0; i < pere.nbComposant(); i++) {
            if (pere.getComposant(i) == e) {
                return i;
            }
        }
        } catch (Throwable ex) {
            return -1;
        }
        return -1;
    }

    private void fireTreeStructureChanged(ComposantElectronique racine) {
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
