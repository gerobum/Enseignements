
package classes;

import interfaces.Mesurable;

/**
 *
 * @author yvan
 */
public class Extreme {
    private Mesurable minimum, maximum;

    public Extreme(Mesurable mesurable) {
        minimum = maximum = mesurable;
    }

    public Mesurable getMaximum() {
        return maximum;
    }

    public Mesurable getMinimum() {
        return minimum;
    }

    public void ajouter(Mesurable mesurable) {
        if (mesurable.mesure() < minimum.mesure())
            minimum = mesurable;
        else if (mesurable.mesure() > maximum.mesure())
            maximum = mesurable;
    }
}
