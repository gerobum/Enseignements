package conversionDevisesAvecAPI;

import java.util.Observable;

/**
 * L'Observé est un Euro. La valeur que l'on considère est une valeur "double"
 * que l'on affichera dans la devise choisie.
 *
 * @author Yvan
 */
public class Euro extends Observable {

  private double valeur;

  public Euro() {
    this(1.0);
  }

  public Euro(double valeur) {
    this.valeur = valeur;
  }

  /**
   * La méthode redéfinie transforme le type de retour que l'on sait etre un
   * Double. Elle est également rendue publique.
   */
  public Double getValeur() {
    return valeur;
  }

  public void setValeur(double valeur) {
    if (valeur != this.valeur) {
      this.valeur = (Double) valeur;
      setChanged();
      notifyObservers();
    }
  }
}
