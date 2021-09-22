/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package composant_electronique;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author maillot
 */
public class Composant extends ComposantElectronique {
    private List<ComposantElectronique> liste = new ArrayList<ComposantElectronique>();
    private ImageIcon icone;
    private String nom;
    
    public void ajouter(ComposantElectronique ce) {
        liste.add(ce);
    }
    
    public void retirer(ComposantElectronique ce) {
        liste.remove(ce);
    }
    
    public ComposantElectronique getComposant(int i) {
        return liste.get(i);
    }
    
    public int nbComposant() {
        return liste.size();
    }
    
    @Override
    public ImageIcon getIcone() {
        return icone;
    }

    @Override
    public String getNom() {
        return nom;
    }


    @Override
    public String getType() {
        return "Composé";
    }
}
