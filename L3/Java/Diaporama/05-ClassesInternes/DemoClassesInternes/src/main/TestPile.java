/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import pile.Pile;

/**
 *
 * @author yvan
 */
public class TestPile {
  public static void main(String[] args) {
   
    Pile p = new Pile();
    p.empiler(1);
    p.empiler(2);
    p.empiler(3);
    
    // i est un it�rateur de pile
    Pile.It�rateur i;
    // Le seul moyen de cr�er un tel it�rateur.
    i = p.premier();
    // Affichage du contenu de la pile
    while(i.encore()) {
      System.out.println(i.suivant());
    }
  
  }
}
