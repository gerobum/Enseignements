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
    
    // i est un itérateur de pile
    Pile.Itérateur i;
    // Le seul moyen de créer un tel itérateur.
    i = p.premier();
    // Affichage du contenu de la pile
    while(i.encore()) {
      System.out.println(i.suivant());
    }
  
  }
}
