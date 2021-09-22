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
public class PorteET extends ComposantSimple {

    @Override
    public String getType() {
        return "ET";
    }

    @Override
    public ImageIcon getIcone() {
        return Images.and;
    }
    
    
    
}
