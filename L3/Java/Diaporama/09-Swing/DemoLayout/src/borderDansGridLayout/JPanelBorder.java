/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package borderDansGridLayout;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author yvan
 */
public class JPanelBorder extends JPanel {
    private JLabel nord, sud, est, ouest, centre;
    private BorderLayout borderLayout = new BorderLayout();
    private Color couleur;

    private void definirLesElements() {
        sud.setOpaque(true);
        sud.setBackground(couleur);
        est.setOpaque(true);
        est.setBackground(couleur.darker());
        nord.setOpaque(true);
        nord.setBackground(couleur.darker().darker());
        ouest.setOpaque(true);
        ouest.setBackground(couleur.darker().darker().darker());
        centre.setOpaque(true);
        centre.setBackground(couleur.darker().darker().darker().darker());
    }

    public JPanelBorder(Color couleur) {
        nord = new JLabel("", JLabel.CENTER);
        sud = new JLabel("", JLabel.CENTER);
        est = new JLabel("", JLabel.CENTER);
        ouest = new JLabel("", JLabel.CENTER);
        centre = new JLabel("", JLabel.CENTER);
        this.couleur = couleur;

        definirLesElements();

        setLayout(borderLayout);

        placerLesElements();
    }

  

    private void placerLesElements() {
        add(nord, "North");
        add(sud, "South");
        add(est, "East");
        add(ouest, "West");
        add(centre, "Center");
        nord.setPreferredSize(new Dimension(100, 50));
        sud.setPreferredSize(new Dimension(45, 50));
        centre.setPreferredSize(new Dimension(250, 250));
        est.setPreferredSize(new Dimension(50, 10));
        ouest.setPreferredSize(new Dimension(75, 25));
        borderLayout.setHgap(5);
        borderLayout.setVgap(5);

    }
 
}
