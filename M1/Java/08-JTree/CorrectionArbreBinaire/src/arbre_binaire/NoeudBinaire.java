/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arbre_binaire;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.tree.TreeNode;

/**
 *
 * @author maillot
 */
public class NoeudBinaire implements TreeNode {

    private NoeudBinaire parent = null;
    private List<NoeudBinaire> liste = new ArrayList<NoeudBinaire>();
    private Object elt;
    public static int GAUCHE = 1;
    public static int DROITE = 2;
    public static int RIEN = 0;
    private boolean feuille;
    private boolean enfantsAutorisés = true;


    public NoeudBinaire() {
        feuille = true;
        elt = "#";
    }

    public NoeudBinaire(NoeudBinaire g, NoeudBinaire d, Object nom) {
        //this.g = g;
        //this.d = d;
        this.feuille = false;
        
        if (g == null)
            g = new NoeudBinaire();
        if (d == null)
            d = new NoeudBinaire();

        liste.add(g);
        g.setParent(this);

        liste.add(d);
        d.setParent(this);


        this.elt = nom;


    }

    private void setParent(NoeudBinaire parent) {
        this.parent = parent;
    }

    public NoeudBinaire getChildAt(int i) {
        return liste.get(i);
    }

    public int getChildCount() {
        if (liste.get(0) == null && liste.get(1) == null) {
            return 0;
        } else if (liste.get(0) != null && liste.get(1) != null) {
            return 2;
        } else {
            return 1;
        }
    }

    public NoeudBinaire getParent() {
        return parent;
    }

    public int getIndex(TreeNode tn) {
        if (tn == liste.get(0)) {
            return GAUCHE;
        } else if (tn == liste.get(1)) {
            return DROITE;
        } else {
            return RIEN;
        }
    }

    public boolean getAllowsChildren() {
        return enfantsAutorisés;
    }

    public boolean isLeaf() {
        return feuille;
    }

    public Enumeration children() {
        return (Enumeration) liste;
    }

    @Override
    public String toString() {
        return elt.toString();
    }
}
