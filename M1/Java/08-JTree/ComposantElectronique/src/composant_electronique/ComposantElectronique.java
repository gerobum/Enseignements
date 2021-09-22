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
public abstract class ComposantElectronique {
    public abstract ImageIcon getIcone();
    public abstract String getNom();
    public abstract String getType();
    @Override
    public String toString() {
        return getType();
    }
}
