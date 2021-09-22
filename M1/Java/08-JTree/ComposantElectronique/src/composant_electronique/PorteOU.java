/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package composant_electronique;

import composant_electronique.images.Images;
import javax.swing.ImageIcon;

/**
 *
 * @author maillot
 */
public class PorteOU extends ComposantSimple {

    @Override
    public String getType() {
        return "OU";
    }
    

    @Override
    public ImageIcon getIcone() {
        return Images.or;
    }
}
