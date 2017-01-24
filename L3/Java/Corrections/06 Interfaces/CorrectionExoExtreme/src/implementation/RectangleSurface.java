
package implementation;

import interfaces.Mesurable;
import java.awt.Rectangle;

/**
 *
 * @author yvan
 */
public class RectangleSurface extends Rectangle implements Mesurable {

    public RectangleSurface(int width, int height) {
        super(width, height);
    }

    public int mesure() {
        return this.height*this.width;
    }

}
