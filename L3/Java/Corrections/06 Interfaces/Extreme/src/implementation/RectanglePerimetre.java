
package implementation;

import interfaces.Mesurable;
import java.awt.Rectangle;

/**
 *
 * @author yvan
 */
public class RectanglePerimetre extends Rectangle implements Mesurable {

    public RectanglePerimetre(int width, int height) {
        super(width, height);
    }

    @Override
    public int mesure() {
        return 2*(width+height);
    }
}
