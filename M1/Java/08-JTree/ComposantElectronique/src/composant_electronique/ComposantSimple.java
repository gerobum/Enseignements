/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package composant_electronique;

import javax.swing.ImageIcon;

/**
 *
 * @author maillot
 */
public class ComposantSimple extends ComposantElectronique {

    @Override
    public ImageIcon getIcone() {
        return null;
    }

    @Override
    public String getNom() {
        return "";
    }

    @Override
    public String getType() {
        return "Composant simple";
    }
    
}
