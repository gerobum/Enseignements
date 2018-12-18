package conversionDevisesAvecAPI.patron;

import java.util.Observable;

public class Euro extends Observable {

  private double valeur;

  public Euro() {
    this(1.0);
  }

  public Euro(double valeur) {
    this.valeur = valeur;
  }

  public Double getValeur() {
    return valeur;
  }

  public void setValeur(double valeur) {
    if (valeur != this.valeur) {
      this.valeur = valeur;
      setChanged();
      notifyObservers();
    }
  }
}
