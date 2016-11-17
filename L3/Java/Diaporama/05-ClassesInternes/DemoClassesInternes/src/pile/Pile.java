/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pile;

/**
 *
 * @author yvan
 */
public class Pile {
  /* Une classe interne statique (donc de classe) pour les chaînons de la liste 
   * car c'est bien d'une classe statique dont on a besoin ici, mais uniquement 
   * ? l'intérieur de notre pile.
   * 
   * Remarquez qu'elle est privée donc innaccessible de l'extérieur */
  private static class Pilon {
    private double elt;
    private Pilon suivant;

    private Pilon(double elt, Pilon suivant) {
      this.elt = elt;
      this.suivant = suivant;
    }
  }
  private Pilon sommet;
  public Pile() {
    sommet = null;
  }
  public double depiler() {
    double r = sommet.elt;
    sommet = sommet.suivant;
    return r;
  } 
  public void empiler(double elt) {
    sommet = new Pilon(elt, sommet);
  } 
  public boolean vide() {
    return sommet == null;
  }
  
  /* Une classe interne (non statique donc d'instance) pour les itérateurs car 
   * c'est bien d'une classe interne d'instance dont on a besoin. En effet un 
   * itérateur doit correspondre ? une (instance de) pile pour la parcourir. 
  
     Remarquez qu'elle est publique mais son constructeur est privé.
  */
  public class Itérateur {
    private Pilon crt;

    private Itérateur() {
      crt = sommet; // équivalent ? : crt = Pile.this.sommet;
    }
    // Retourne l'élément sous l'itérateur et passe au suivant.
    public double suivant() {
      double t = crt.elt;  crt = crt.suivant; return t;
    }
    // Faux si en fin de liste
    public boolean encore() {
      return crt != null;
    }
  }

  public Itérateur premier() {
    return new Itérateur();
  }
}
