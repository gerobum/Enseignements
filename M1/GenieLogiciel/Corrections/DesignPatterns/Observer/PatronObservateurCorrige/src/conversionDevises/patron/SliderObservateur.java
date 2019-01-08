package conversionDevises.patron;

import conversionDevises.patron.Euro;
import conversionDevises.patron.Observé;
import conversionDevises.patron.Observateur;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * SliderObservateur est un JSlider mais aussi un observateur. Sa modification
 * entraîne le changement d'état d'un observé qui entraîne en cascade la
 * modification de tous les états des observateurs qui surveillement le même
 * observé.
 *
 * Par ailleurs, le changement d'état de l'observé entraîne le changement du
 * slider.
 *
 * @author maillot
 */
public class SliderObservateur extends JSlider implements Observateur {

    private final String devise;
    private final double valeurPour1€;
    private Euro sujet;

    /**
     * Constructeur
     *
     * @param devise
     * @param valeurPour1€
     * @param sujet
     */
    public SliderObservateur(String devise, double valeurPour1€, Euro sujet) {
        super(0, 5000);
        this.devise = devise;
        this.valeurPour1€ = valeurPour1€;
        this.sujet = sujet;
        sujet.ajoute(this);
        

        addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                SliderObservateur source = (SliderObservateur) e.getSource();
                if (getValueIsAdjusting()) {
                    source.setValeur(getValue());
                }
            }
        });

        update();
    }

    @Override
    public void setSujet(Observé sujet) {
        this.sujet = (Euro) sujet;
    }

    @Override
    public void update() {
        setValue((int) (sujet.getValeur() * valeurPour1€));
    }

    private void setValeur(double valeur) {
        sujet.setValeur(valeur / valeurPour1€);
    }
}
