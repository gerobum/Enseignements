/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package implementation;

import interfaces.Mesureur;
import java.awt.Rectangle;

/**
 *
 * @author yvan
 */
public class MesureurRectangleSurface implements Mesureur {

    public int mesure(Object o) {
        return ((Rectangle) o).height * ((Rectangle) o).width;
    }

}
