package conversionDevises;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * DeviseJSlider est un JSlider mais aussi un observateur. Sa modification
 * entraîne le changement d'état d'un observé qui entraîne en cascade la
 * modification de tous les états des observateurs qui surveillement le même
 * observé.
 *
 * Par ailleurs, le changement d'état de l'observé entraîne le changement du
 * slider.
 *
 * @author maillot
 */
public class DeviseJSlider extends JSlider implements Observateur {

    protected String devise;
    protected double valeurPour1Euro;
    protected Euro sujet;

    /**
     * Constructeur
     *
     * @param devise
     * @param valeurPour1Euro
     * @param sujet
     */
    public DeviseJSlider(String devise, double valeurPour1Euro, Euro sujet) {
        super(0, 1000);
        this.devise = devise;
        this.valeurPour1Euro = valeurPour1Euro;
        this.sujet = sujet;
        sujet.ajoute(this);

        addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                DeviseJSlider source = (DeviseJSlider) e.getSource();
                if (getValueIsAdjusting()) {
                    source.setValeur(getValue());
                }
            }
        });

    }

    @Override
    public void setSujet(Observé sujet) {
        this.sujet = (Euro) sujet;
    }

    @Override
    public void update() {
        setValue((int) (sujet.getValeur() * valeurPour1Euro));
    }

    private void setValeur(double valeur) {
        sujet.setValeur(valeur / valeurPour1Euro);
    }
}
