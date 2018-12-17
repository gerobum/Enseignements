package conversionDevisesAvecAPI.patron;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderObservateur extends JSlider implements Observer {

  private final double valeurPour1Euro;
  private double valeur;
  private final Euro sujet;

  public SliderObservateur(String devise, double valeurPour1Euro, Euro sujet) {
    super(0, 1000, 0);
    this.valeurPour1Euro = valeurPour1Euro;
    // Le double lien entre l'observeur et l'observé est fait 
    // dans les deux lignes qui suivent.
    this.sujet = sujet;
    sujet.addObserver(this);

    addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        SliderObservateur source = (SliderObservateur) e.getSource();
        if (source.getValueIsAdjusting()) {
          source.setValeur(getValue());
        }
      }
    });
  }

  private void setValeur(double valeur) {
    sujet.setValeur(valeur / valeurPour1Euro);

  }

  @Override
  public void update(Observable o, Object arg) {
    valeur = sujet.getValeur() * valeurPour1Euro;
    setValue((int) (valeur));
  }
}