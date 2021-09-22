/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package composant_electronique.images;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author maillot
 */
public class Images {

    public static final ImageIcon brancheFermée;
    public static final ImageIcon feuille;
    public static final ImageIcon brancheOuverte;
    public static final ImageIcon and;
    public static final ImageIcon or;

    static {
        ImageIcon ii = new ImageIcon(Images.class.getResource("CIClosed.jpeg"));
        brancheFermée = new ImageIcon(ii.getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING));
        ii = new ImageIcon(Images.class.getResource("Porte.jpeg"));
        feuille = new ImageIcon(ii.getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING));
        ii = new ImageIcon(Images.class.getResource("CIOpen.jpeg"));
        brancheOuverte = new ImageIcon(ii.getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING));
        ii = new ImageIcon(Images.class.getResource("AND.jpeg"));
        and = new ImageIcon(ii.getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING));
        ii = new ImageIcon(Images.class.getResource("OR.jpeg"));
        or = new ImageIcon(ii.getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING));
    }
}
