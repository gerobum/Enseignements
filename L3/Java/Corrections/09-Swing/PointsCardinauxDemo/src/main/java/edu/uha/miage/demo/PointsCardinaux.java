/*
 * Creative commons CC BY-NC-SA 2020 Yvan Maillot <yvan.maillot@uha.fr>
 *
 *     Share - You can copy and redistribute the material in any medium or format
 * 
 *     Adapt - You can remix, transform, and build upon the material 
 * 
 * Under the following terms :
 * 
 *     Attribution - You must give appropriate credit, provide a link to the license, 
 *     and indicate if changes were made. You may do so in any reasonable manner, 
 *     but not in any way that suggests the licensor endorses you or your use. 
 * 
 *     NonCommercial — You may not use the material for commercial purposes. 
 * 
 *     ShareAlike — If you remix, transform, or build upon the material, 
 *     you must distribute your contributions under the same license as the original. 
 * 
 * Notices:    You do not have to comply with the license for elements of 
 *             the material in the public domain or where your use is permitted 
 *             by an applicable exception or limitation. 
 * 
 * No warranties are given. The license may not give you all of the permissions 
 * necessary for your intended use. For example, other rights such as publicity, 
 * privacy, or moral rights may limit how you use the material. 
 * 
 * See <https://creativecommons.org/licenses/by-nc-sa/4.0/>.
 */
package edu.uha.miage.demo;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PointsCardinaux extends JFrame {

    private JButton nord, sud, est, ouest;
    private JLabel centre;

    public PointsCardinaux() {
        super("Démonstration de l'exo \"Les points cardinaux\"");

        miseEnPlaceDesComposants();
        miseEnPlacesDesEcouteurs();
        miseEnPlaceUI();

    }

    private void miseEnPlaceDesComposants() {
        nord = new JButton("Nord");
        sud = new JButton("Sud");
        est = new JButton("Est");
        ouest = new JButton("Ouest");
        centre = new JLabel("Centre", JLabel.CENTER);
        centre.setPreferredSize(new Dimension(300, 300));
        getContentPane().add(nord, "North");
        getContentPane().add(sud, "South");
        getContentPane().add(est, "East");
        getContentPane().add(ouest, "West");
        getContentPane().add(centre, "Center");
    }

    private void miseEnPlacesDesEcouteurs() {
        ActionListener clic = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centre.setText(((JButton) e.getSource()).getText());
            }
        };
        nord.addActionListener(clic);
        est.addActionListener(clic);
        ouest.addActionListener(clic);
        sud.addActionListener(clic);
    }

    private void miseEnPlaceUI() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }
}
