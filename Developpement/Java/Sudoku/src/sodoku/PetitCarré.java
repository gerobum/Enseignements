/*
 * PetitCarr�.java
 *
 * Created on 28 mars 2006, 19:45
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package sodoku;

import java.awt.Color;
import java.awt.Font;
import java.util.HashSet;

/**
 * Une case du Sudoku
 * @author Yvan Maillot
 */
public class PetitCarr� implements Cloneable {

    private Bouton bouton;
    private HashSet<Integer> possibilit�;
    private int l, c;
    PetitCarr�[] ligne;
    PetitCarr�[] colonne;
    PetitCarr�[] carr�;
    GrandCarr� grand_carr�;
    Font FontePour1Caract�re, Fonte;
    
   public PetitCarr�(int l, int c, GrandCarr� grand_carr�) {
        Fonte = new Font("Bradley Hand ITC", Font.BOLD, 16);
        FontePour1Caract�re = new Font("Bradley Hand ITC", Font.BOLD, 50);
        this.grand_carr� = grand_carr�;
        this.l = l+1;
        this.c = c+1;
        possibilit� = new HashSet<Integer>();
        for(int v = 1; v <= 9; v++) {
            possibilit�.add(v);
        }
    }
    public void mise_�_jour() {
        //bouton.setText(construire_nom());
        construire_nom_bouton();
    }    
    public PetitCarr� copie() {
        PetitCarr� petit_carr� = new PetitCarr�(l, c, null);
        petit_carr�.Fonte = Fonte;
        petit_carr�.FontePour1Caract�re = FontePour1Caract�re;

        petit_carr�.bouton = bouton;
        petit_carr�.possibilit� = new HashSet<Integer>();
        for(Integer i : possibilit�)
            petit_carr�.possibilit�.add(i.intValue());
        petit_carr�.ligne = null;
        petit_carr�.colonne = null;
        petit_carr�.carr� = null;
        
        return petit_carr�;
    }    
    public PetitCarr� clone() throws CloneNotSupportedException {
        PetitCarr� petit_carr� = (PetitCarr�)super.clone();
        if (possibilit�.size() == 1)
            System.out.println("AVANT");
        //petit_carr�.possibilit� = (HashSet<Integer>)possibilit�.clone();
        //petit_carr�.possibilit� = new HashSet<Integer>();
        if (possibilit�.size() == 1)
            System.out.println("APRES");
        
        // Les 4 attributs suivants ne peuvent �tre mis � jour que depuis
        // le grand_carr�
        petit_carr�.ligne = null;
        petit_carr�.colonne = null;
        petit_carr�.carr� = null;
        petit_carr�.grand_carr� = null;
        
        return petit_carr�;
    }    
    public void setBouton(Bouton bouton) {
        this.bouton = bouton;
    }
    
    public HashSet<Integer> valeurs_possible() {
        return (HashSet<Integer>)possibilit�.clone();
    }
    public void setValeurs(int[] v) {
        for(int i = 0; i < v.length; i++) {
            possibilit�.add(v[i]);
        }
        //bouton.setText(construire_nom());
        construire_nom_bouton();
    }    
    public void setValeur(int v) {
        if (possibilit�.size() > 1) {
            possibilit�.clear();
            possibilit�.add(v);
            //bouton.setText(construire_nom());
            construire_nom_bouton();
            nettoie();
        }
    }
    /**
     *Ne doit �tre lanc� que si la case ne contient qu'un seul �l�ment.
     *Elle retire cet �l�ment de toutes les cases de la ligne,
     *de la colonne et du carr� associ� � la case.
     *Retourne faux, si une case se retrouve vide
     **/
    private boolean nettoie_ligne() {
        for(PetitCarr� c : ligne) {
            if (!c.enlever(possibilit�))
                return false;
        }
        return true;
    }
    private boolean nettoie_colonne() {
        for(PetitCarr� c : colonne) {
            if (!c.enlever(possibilit�))
                return false;
        }
        return true;
    }
    private boolean nettoie_carr�() {
        for(PetitCarr� c : carr�) {
            if (!c.enlever(possibilit�))
                return false;
        }
        return true;
    }  
    private boolean nettoie() {
       
        boolean ok = possibilit�.size() == 1 &&
                nettoie_ligne() && 
                nettoie_colonne() && 
                nettoie_carr�();
        return ok;
    }
    
    /**
     * 
     * Enl�ve des possibilit�s d'une case la valeur en argument
     * Rien n'est fait si cette valeur est d�j� absente.
     * Attention : 1 <= e <= 9
     * @param e La valeur � retirer
     */
    public boolean enlever(int e) {
            int size = possibilit�.size();
            possibilit�.remove(e);
            if (size != possibilit�.size()) {
                //bouton.setText(construire_nom());
                construire_nom_bouton();
                if (possibilit�.size() == 0) {
                    bouton.setBackground(Color.RED);
                    return false;
                } else if (possibilit�.size() == 1) {
                    //bouton.setText(construire_nom());
                    construire_nom_bouton();
                    return nettoie();
                } 
            }
            return true;      
    }
    public void construire_nom_bouton() {
        if (possibilit�.size() == 1) {
            bouton.setText(possibilit�.iterator().next().toString());
            bouton.setFont(FontePour1Caract�re);
        } else {
            bouton.setFont(Fonte);
            String nom = "<html>";
            int k = 1;
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if (possibilit�.contains(k)) {
                        nom+=k+"";
                    } else {
                        nom+=" ";
                    }
                    k++;
                }
                nom+="<br>";
            }
            bouton.setText(nom+"</html>");  
        }
    }

    /**
     * 
     * Enl�ve des possibilit�s d'une case la valeur en argument
     * Rien n'est fait si cette valeur est d�j� absente.
     * Attention : 1 <= e <= 9
     * @param e La valeur � retirer
     */
    public boolean enlever(HashSet<Integer> e) {
        int size = possibilit�.size();
        possibilit�.removeAll(e);
        if (size != possibilit�.size()) {
            //bouton.setText(construire_nom());
            construire_nom_bouton();
            if (possibilit�.size() == 0) {
                bouton.setBackground(Color.RED);
                return false;
            }
            else if (possibilit�.size() == 1)  {
                //bouton.setText(construire_nom());
                construire_nom_bouton();
                return nettoie();
            } 
        }
        return true;      
    }
    /** 
     *Ajoute e.
     *Attention : 1 <= e <= 9
     * @param e La valeur � ajouter
     **/
    public void ajouter(int e) {
        possibilit�.add(e);
    }
   
}
