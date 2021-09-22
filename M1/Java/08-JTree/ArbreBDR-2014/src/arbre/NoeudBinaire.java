/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arbre;

import java.io.Serializable;

public class NoeudBinaire<T extends Comparable<? super T> & Serializable> {

  private T info;
  private NoeudBinaire<T> gauche, droit;
  public static final NoeudBinaire vide = new NoeudBinaire();

  private NoeudBinaire() {
    info = null;
    gauche = null;
    droit = null;
  }

  public NoeudBinaire(T info) {
    this.info = info;
    gauche = vide;
    droit = vide;
  }

  public T getInfo() {
    return info;
  }

  public NoeudBinaire<T> getGauche() {
    return gauche;
  }

  public NoeudBinaire<T> getDroit() {
    return droit;
  }

  public void ajouter(T info) {
    if (info.compareTo(this.info) < 0) {
      if (gauche == vide) {
        gauche = new NoeudBinaire<>(info);
      } else {
        gauche.ajouter(info);
      }
    } else {
      if (droit == vide) {
        droit = new NoeudBinaire<>(info);
      } else {
        droit.ajouter(info);
      }
    }
  }

  @Override
  public String toString() {
    if (info == null) {
      return "";
    } else {
      return info.toString();
    }
  }
  
  private static void parcours(NoeudBinaire n, StringBuilder sb) {
    if (n != vide) {
      parcours(n.gauche, sb);
      sb.append(n.info).append(' ');
      parcours(n.droit, sb);
    }
  }

  public String parcours() {
    StringBuilder sb = new StringBuilder();
    parcours(this, sb);
    return sb.toString();
  }
  
}
