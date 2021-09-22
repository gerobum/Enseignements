/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arbre_binaire;

import java.util.ArrayList;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author maillot
 */
public class NoeudBDRFromTN extends DefaultMutableTreeNode {
    
    private List<Comparable> valeursEnOrdre = new ArrayList<Comparable>();
    private static Comparable[] modele = new Comparable[0];

    public NoeudBDRFromTN(Comparable userObject, boolean allowsChildren) {
        super(userObject, allowsChildren);
    }

    public NoeudBDRFromTN(Comparable userObject) {
        super(userObject);
    }

    public NoeudBDRFromTN() {
    }

    public Comparable getValeur() {
        return (Comparable) userObject;
    }

    public void add(Comparable valeur) {
        if (userObject == null) {
            userObject = valeur;
            add(new NoeudBDRFromTN());
            add(new NoeudBDRFromTN());
        } else {
            Comparable v = (Comparable) userObject;

            if (valeur.compareTo(v) < 0) {
   
                    NoeudBDRFromTN n = (NoeudBDRFromTN) getChildAt(0);
                    n.add(valeur);

            } else {                
  
                    NoeudBDRFromTN n = (NoeudBDRFromTN) getChildAt(1);
                    n.add(valeur);
            }
        }

    }
    
    public Comparable[] getValeursEnOrdre(NoeudBDRFromTN n) {
        valeursEnOrdre.clear();
        
        setValeursEnOrdre(n);
        return (Comparable[]) valeursEnOrdre.toArray(modele);
    }

    private void setValeursEnOrdre(NoeudBDRFromTN n) {
        if (!n.isLeaf()) {
            setValeursEnOrdre((NoeudBDRFromTN)n.getChildAt(0));
            valeursEnOrdre.add(n.getValeur());
            setValeursEnOrdre((NoeudBDRFromTN)n.getChildAt(1));
        }
    }
}
