/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arbre_binaire;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import javax.swing.tree.TreeNode;

/**
 *
 * @author maillot
 */
public class NoeudBinaireUnaire implements TreeNode {

    private NoeudBinaireUnaire parent = null;
    private List<NoeudBinaireUnaire> liste = new ArrayList<NoeudBinaireUnaire>();
    private String nom;
    public static int GAUCHE = 1;
    public static int DROITE = 2;
    public static int RIEN = 0;
    private boolean feuille = false;
    private boolean enfantsAutorisés = true;
    

    public NoeudBinaireUnaire(NoeudBinaireUnaire g, NoeudBinaireUnaire d, String nom, boolean feuille) {
        //this.g = g;
        //this.d = d;
        this.feuille = feuille;

        if (g != null) {
            liste.add(g);
            g.setParent(this);
        }

        if (d != null) {
            liste.add(d);
            d.setParent(this);
        }

        this.nom = nom;


    }

    private void setParent(NoeudBinaireUnaire parent) {
        this.parent = parent;
    }

    public NoeudBinaireUnaire getChildAt(int i) {
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

    public NoeudBinaireUnaire getParent() {
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
        return nom;
    }
}
