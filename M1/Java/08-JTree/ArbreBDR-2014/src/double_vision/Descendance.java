/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package double_vision;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author yvan.test
 */
public class Descendance implements TreeModel {

    private Personne racine;
    private List<TreeModelListener> listeners = new ArrayList<TreeModelListener>();
    

    public Descendance(Personne racine) {
        this.racine = racine;
    }

    public void setRoot(Personne racine) {
        this.racine = racine;
    }    

    @Override
    public Personne getRoot() {
        return racine;
    }

    @Override
    public Personne getChild(Object o, int i) {
        Personne p = (Personne) o;
        if (i < 0 || i > p.getNbEnfants()) {
            return null;
        }
        return p.getEnfant(i);
    }

    @Override
    public int getChildCount(Object o) {
        Personne p = (Personne) o;
        return p.getNbEnfants(); 
    }

    @Override
    public boolean isLeaf(Object o) {
        Personne p = (Personne) o;
        return p.getNbEnfants()==0;
    }

    @Override
    public void valueForPathChanged(TreePath tp, Object o) {
        TreeModelEvent tme = new TreeModelEvent(o, tp);
        for(TreeModelListener tml : listeners) {
            tml.treeNodesChanged(tme);
        }
    }

    @Override
    public int getIndexOfChild(Object parent, Object enfant) {
        if (parent == null || enfant == null) return -1;
        Personne p= (Personne) parent;
        Personne e= (Personne) enfant;
        for(int i = 0; i < p.getNbEnfants(); i++)
            if (p.getEnfant(i)==e)
                return i;
        return -1;
    }

    @Override
    public void addTreeModelListener(TreeModelListener tl) {
        listeners.add(tl);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener tl) {
        listeners.remove(tl);
    }
}
