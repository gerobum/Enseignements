/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbre;

import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author maillot
 */
public class Arbre<T extends Comparable<? super T> & Serializable> {

  private NoeudBinaire<T> racine;


  public Arbre() {
  }

  public void ajouter(T info) {
    if (racine == null) {
      racine = new NoeudBinaire<>(info);
    } else {
      racine.ajouter(info);
    }
  }

  public NoeudBinaire<T> getRacine() {
    return racine;
  }

  @Override
  public String toString() {
    if (racine == null) {
      return "";
    } else {
      StringBuilder sb = new StringBuilder();
      racine.parcours();
      return sb.toString();
    }
    
  }

  public static void main(String[] args) {
    Arbre<Integer> arbre = new Arbre<>();
    Integer[] ti = {4, 8, 9, 7, 6, 2, 1, 3, 5, 0};

    //Scanner clavier = new Scanner(System.in);

    //while (true) {
    //    System.out.println(arbre);
    //    arbre.ajouter(clavier.nextInt());
    //}
    for (Integer i : ti) {
      System.out.println(arbre);
      arbre.ajouter(i);
    }
  }
}
