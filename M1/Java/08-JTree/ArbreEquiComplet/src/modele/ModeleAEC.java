package modele;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author yvan
 */
public class ModeleAEC implements TreeModel {

    private final int hauteur;

    public ModeleAEC(int h) {
        hauteur = h;
    }

    @Override
    public Object getRoot() {
        return 0;
    }

    @Override
    public boolean isLeaf(Object node) {
        Integer p = (Integer) node;
        return p/10 > hauteur;
    }

    @Override
    public Object getChild(Object parent, int index) {
        if (parent == null) {
            return null;
        }
        if (isLeaf(parent)) {
            return null;
        }
        Integer p = (Integer) parent/10;
        if (index == 0) {
            return new Integer((p + 1)*10);
        }
        if (index == 1) {
            return new Integer((p + 1)*10+1);
        }
        return null;
    }

    @Override
    public int getChildCount(Object parent) {
        return isLeaf(parent) ? 0 : 2;
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        if (parent == null || child == null || isLeaf(parent)) {
            return -1;
        }

        int p = (int) parent;
        int c = (int) child;
        if ((c - 1) / 10 != (p / 10)) {
            return -1;
        }
        return c % 10;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
