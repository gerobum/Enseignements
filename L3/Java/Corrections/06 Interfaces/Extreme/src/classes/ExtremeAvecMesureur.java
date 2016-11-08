
package classes;

import interfaces.Mesureur;

/**
 *
 * @author yvan
 */
public class ExtremeAvecMesureur {
    private final Mesureur mesureur;
    private Object minimum, maximum;

    public ExtremeAvecMesureur(Mesureur mesureur, Object objet) {
        this.mesureur = mesureur;
        this.minimum = maximum = objet;
    }

    public Object getMaximum() {
        return maximum;
    }

    public Object getMinimum() {
        return minimum;
    }


    public void ajouter(Object objet) {
        if (mesureur.mesure(objet) < mesureur.mesure(minimum))
            minimum = objet;
        else if (mesureur.mesure(objet) > mesureur.mesure(maximum))
            maximum = objet;
    }

}
