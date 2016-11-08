/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package borderLayout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author yvan
 */
public class TestBorderLayoutColore extends JFrame {

    private JLabel nord, sud, est, ouest, centre;

    private void definirLesElements() {
        nord = new JLabel("Nord", JLabel.CENTER);
        sud = new JLabel("Sud", JLabel.CENTER);
        est = new JLabel("Est", JLabel.CENTER);
        ouest = new JLabel("Ouest", JLabel.CENTER);
        centre = new JLabel("Centre", JLabel.CENTER);
        nord.setOpaque(true);
        nord.setBackground(Color.BLUE.darker().darker());
        nord.setForeground(Color.WHITE);
        sud.setOpaque(true);
        sud.setBackground(Color.RED.darker().darker());
        sud.setForeground(Color.WHITE);
        est.setOpaque(true);
        est.setBackground(Color.GREEN.darker().darker());
        est.setForeground(Color.WHITE);
        ouest.setOpaque(true);
        ouest.setBackground(Color.PINK);
        ouest.setForeground(Color.WHITE);
        centre.setOpaque(true);
        centre.setBackground(Color.ORANGE);
        centre.setForeground(Color.WHITE);
    }

    public TestBorderLayoutColore() {
        super("Test BorderLayout");

        getContentPane().setLayout(new BorderLayout());
        definirLesElements();
        placerLesElements();

        pack();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void placerLesElements() {
        getContentPane().add(nord, "North");
        getContentPane().add(sud, "South");
        getContentPane().add(est, "East");
        getContentPane().add(ouest, "West");
        getContentPane().add(centre, "Center");
        nord.setPreferredSize(new Dimension(1000, 50));
        centre.setPreferredSize(new Dimension(100, 100));
        est.setPreferredSize(new Dimension(100, 25));
    }
}
