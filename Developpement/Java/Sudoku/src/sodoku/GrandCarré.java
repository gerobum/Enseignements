/*
 * GrandCarr�.java
 *
 * Created on 29 mars 2006, 10:50
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package sodoku;

import java.util.HashSet;

/**
 *
 * @author Yvan Maillot
 */
public class GrandCarr� implements Cloneable {
    
    /** Creates a new instance of GrandCarr� */
    private PetitCarr�[][] damier;
    
    public GrandCarr�() { 
        damier = new PetitCarr�[9][9];
        for(int i = 0; i < 9; i++) 
            for(int j = 0; j < 9; j++) 
                damier[i][j] = new PetitCarr�(i,j, this);
        for(int i = 0; i < 9; i++) 
            for(int j = 0; j < 9; j++) {
                damier[i][j].ligne = ligne(i, j);
                damier[i][j].colonne = colonne(i, j);
                damier[i][j].carr� = carr�(i, j);
            }
    }
    public void mise_�_jour_des_boutons() {
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                damier[i][j].mise_�_jour();
    }
    public GrandCarr� copie() {
        GrandCarr� grand_carr� = new GrandCarr�();
        grand_carr�.damier = new PetitCarr�[9][9];
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++) 
                grand_carr�.damier[i][j] = damier[i][j].copie();
        
        for(int i = 0; i < 9; i++) 
            for(int j = 0; j < 9; j++) {
                grand_carr�.damier[i][j].ligne = grand_carr�.ligne(i, j);
                grand_carr�.damier[i][j].colonne = grand_carr�.colonne(i, j);
                grand_carr�.damier[i][j].carr� = grand_carr�.carr�(i, j);
            }  
        return grand_carr�;
    }
    public boolean fini() {
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                if (damier[i][j].valeurs_possible().size()!=1)
                    return false;
        return true;
    }
    public boolean erreur() {
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                if (damier[i][j].valeurs_possible().size()==0)
                    return true;
        return false;
    }    
    public GrandCarr� clone() throws CloneNotSupportedException {
        GrandCarr� grand_carr� = (GrandCarr�) super.clone();
        grand_carr�.damier = damier.clone();
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++) {
                grand_carr�.damier[i][j] = damier[i][j].clone();
                grand_carr�.damier[i][j].grand_carr� = grand_carr�;
            }
        for(int i = 0; i < 9; i++) 
            for(int j = 0; j < 9; j++) {
                grand_carr�.damier[i][j].ligne = grand_carr�.ligne(i, j);
                grand_carr�.damier[i][j].colonne = grand_carr�.colonne(i, j);
                grand_carr�.damier[i][j].carr� = grand_carr�.carr�(i, j);
            }        
        return grand_carr�;
    }
    private PetitCarr�[] ligne(int l, int c) {
        PetitCarr�[] L = new PetitCarr�[8];
        int k=0;
        for(int i = 0; i < c; i++) 
            L[k++] = damier[l][i];
        for(int i = c+1; i < 9; i++)
            L[k++] = damier[l][i];
        return L;
    }
    private PetitCarr�[] colonne(int l, int c) {
        PetitCarr�[] C = new PetitCarr�[8];
        int k=0;
        for(int i = 0; i < l; i++) 
            C[k++] = damier[i][c];
        for(int i = l+1; i < 9; i++)
            C[k++] = damier[i][c];
        return C;
    } 
    private PetitCarr�[] carr�(int l, int c) {
        int linf, lsup, cinf, csup;
        if (l < 3) {
            linf = 0;
            lsup = 3;
        } else if (l > 5) {
            linf = 6;
            lsup = 9;
        } else {
            linf = 3;
            lsup = 6;
        }
        if (c < 3) {
            cinf = 0;
            csup = 3;
        } else if (c > 5) {
            cinf = 6;
            csup = 9;
        } else {
            cinf = 3;
            csup = 6;
        }  
        PetitCarr�[] C = new PetitCarr�[8];
        int k = 0;
        for(int i = linf; i < lsup; i++)
            for(int j = cinf; j < csup; j++) {
                if (i != l || j != c) 
                    C[k++] = damier[i][j];
            }
        return C;
    }
    public HashSet<Integer> valeurs_possibles(int l, int c) {
        return damier[l][c].valeurs_possible();
    }
    public PetitCarr� getCarr�(int l, int c) {
        return damier[l][c];
    }
    public void nouvelle_partie() {
        int[] tous = {1,2,3,4,5,6,7,8,9};
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                   damier[i][j].setValeurs(tous);
            }
        }
    }
    public boolean r�cure() {
        boolean changement;
        do {
            changement = false;
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++) {
                HashSet<Integer> possibilit� = damier[i][j].valeurs_possible();
                if (possibilit�.size() == 0)
                    return false;

                if (possibilit�.size() > 1) {
                    for(PetitCarr� c : getCarr�(i,j).carr�) {
                        possibilit�.removeAll(c.valeurs_possible());
                    }
                    if (possibilit�.size() == 1) {
                        changement = true;
                        getCarr�(i,j).setValeur(possibilit�.iterator().next());
                    }
                     
                }
                //if (possibilit�.size() == 0)
                //    return false;

                possibilit� = damier[i][j].valeurs_possible();

                if (possibilit�.size() > 1) {
                    for(PetitCarr� c : getCarr�(i,j).ligne) {
                        possibilit�.removeAll(c.valeurs_possible());
                    }
                    if (possibilit�.size() == 1) {
                        changement = true;
                        getCarr�(i,j).setValeur(possibilit�.iterator().next());
                    }
                     
                }            
                //if (possibilit�.size() == 0)
                //    return false;

                possibilit� = damier[i][j].valeurs_possible();

                if (possibilit�.size() > 1) {
                    for(PetitCarr� c : getCarr�(i,j).colonne) {
                        possibilit�.removeAll(c.valeurs_possible());
                    }
                    if (possibilit�.size() == 1) {
                        changement = true;
                        getCarr�(i,j).setValeur(possibilit�.iterator().next());            
                    }
                } 
                //if (possibilit�.size() == 0)
                //    return false;

            }
        } while(changement);
        return true;
    }
}
