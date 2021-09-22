/*
 * Creative commons CC BY-NC-SA 2020 Yvan Maillot <yvan.maillot@uha.fr>
 *
 *     Share - You can copy and redistribute the material in any medium or format
 * 
 *     Adapt - You can remix, transform, and build upon the material 
 * 
 * Under the following terms :
 * 
 *     Attribution - You must give appropriate credit, provide a link to the license, 
 *     and indicate if changes were made. You may do so in any reasonable manner, 
 *     but not in any way that suggests the licensor endorses you or your use. 
 * 
 *     NonCommercial — You may not use the material for commercial purposes. 
 * 
 *     ShareAlike — If you remix, transform, or build upon the material, 
 *     you must distribute your contributions under the same license as the original. 
 * 
 * Notices:    You do not have to comply with the license for elements of 
 *             the material in the public domain or where your use is permitted 
 *             by an applicable exception or limitation. 
 * 
 * No warranties are given. The license may not give you all of the permissions 
 * necessary for your intended use. For example, other rights such as publicity, 
 * privacy, or moral rights may limit how you use the material. 
 * 
 * See <https://creativecommons.org/licenses/by-nc-sa/4.0/>.
 */
package edu.uha.fst;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class ModelArbreBDR<T extends Comparable<? super T>> implements TreeModel {

    private Noeud<T> racine;
    private final List<T> valeursEnOrdre = new ArrayList<>();
    private final List<TreeModelListener> listeListener = new ArrayList<>();

    public ModelArbreBDR(Noeud<T> racine) {
        this.racine = racine;
    }

    @Override
    public Noeud<T> getRoot() {
        return racine;
    }

    @Override
    public Noeud<T> getChild(Object o, int i) {
        Noeud<T> noeud = (Noeud<T>) o;
        if (i == 0) {
            return noeud.getGauche();
        } else {
            return noeud.getDroite();
        }
    }

    @Override
    public int getChildCount(Object o) {
        Noeud<T> noeud = (Noeud<T>) o;
        if (noeud.getGauche() == null && noeud.getDroite() == null) {
            return 0;
        } else if (noeud.getGauche() != null && noeud.getDroite() != null) {
            return 2;
        } else {
            return 1;
        }
    }

    public void ajouter(T valeur) {
        Noeud<T> noeud = new Noeud<T>(valeur);
        if (racine == null) {
            racine = noeud;
        } else {
            ajouter(racine, noeud);
        }
        fireTreeStructureChanged(racine);
    }

    private void ajouter(Noeud<T> noeud, Noeud<T> nouveau) {
        if (nouveau.getValeur().compareTo(noeud.getValeur()) < 0) {
            if (noeud.getGauche() == Noeud.vide) {
                noeud.setGauche(nouveau);
            } else {
                ajouter(noeud.getGauche(), nouveau);
            }
        } else {

            if (noeud.getDroite() == Noeud.vide) {
                noeud.setDroite(nouveau);
            } else {
                ajouter(noeud.getDroite(), nouveau);
            }
        }

    }

    @Override
    public boolean isLeaf(Object o) {
        Noeud<T> noeud = (Noeud<T>) o;
        return noeud.getGauche() == null && noeud.getDroite() == null;
    }

    @Override
    public void valueForPathChanged(TreePath tp, Object o) {
    }

    @Override
    public int getIndexOfChild(Object p, Object e) {
   
            Noeud<T> parent = (Noeud<T>) p;
            Noeud<T> enfant = (Noeud<T>) e;
            if (parent.getGauche() == enfant) {
                return 0;
            }
            if (parent.getDroite() == enfant) {
                return 1;
            }
            return -1;

       
    }

    protected void fireTreeStructureChanged(Noeud<T> racine) {
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

    public List<T> getValeursEnOrdre(Noeud<T> n) {
        valeursEnOrdre.clear();

        setValeursEnOrdre(n);
        return valeursEnOrdre;
    }

    private void setValeursEnOrdre(Noeud<T> n) {
        if (!isLeaf(n)) {
            setValeursEnOrdre(n.getGauche());
            valeursEnOrdre.add(n.getValeur());
            setValeursEnOrdre(n.getDroite());
        }
    }
}
