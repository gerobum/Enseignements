package exo1;

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
        super("Les points cardinaux");

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

    public static void main(String[] args) {
        new PointsCardinaux();
    }

}
