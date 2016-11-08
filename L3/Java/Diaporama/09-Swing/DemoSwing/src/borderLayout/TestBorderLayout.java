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
public class TestBorderLayout extends JFrame {

    private JLabel nord, sud, est, ouest, centre;
    private JMenuBar menuBar;
    private JMenu actions;
    private JMenuItem itemNord;
    private Action enleverMettre;
    private JMenuItem itemSud;
    private JMenuItem itemEst;
    private JMenuItem itemOuest;
    private JMenuItem itemCentre;
    private Action dimensionner;
    private JMenuItem itemDimNord;
    private JMenuItem itemDimSud;
    private JMenuItem itemDimEst;
    private JMenuItem itemDimOuest;
    private JMenuItem itemDimCentre;
    private Action modifierLesIntervalles;
    private JMenuItem intervalles;
    private JMenuItem pack;
    private BorderLayout borderLayout = new BorderLayout();

    private void definirLesElements() {
        nord.setOpaque(true);
        nord.setBackground(Color.BLUE.darker().darker());
        nord.setForeground(Color.WHITE);
        sud.setOpaque(true);
        sud.setBackground(Color.RED.darker().darker());
        sud.setForeground(Color.WHITE);
        est.setOpaque(true);
        est.setBackground(Color.GREEN.darker());
        est.setForeground(Color.WHITE);
        ouest.setOpaque(true);
        ouest.setBackground(Color.PINK.darker());
        ouest.setForeground(Color.WHITE);
        centre.setOpaque(true);
        centre.setBackground(Color.ORANGE);
        centre.setForeground(Color.WHITE);
    }

    public TestBorderLayout() {
        super("Test BorderLayout");
        nord = new JLabel("Nord", JLabel.CENTER);
        sud = new JLabel("Sud", JLabel.CENTER);
        est = new JLabel("Est", JLabel.CENTER);
        ouest = new JLabel("Ouest", JLabel.CENTER);
        centre = new JLabel("Centre", JLabel.CENTER);

        definirLesElements();

        getContentPane().setLayout(borderLayout);

        placerLesElements();

        definirLesMenus();



        pack();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TestBorderLayout testBorderLayout = new TestBorderLayout();
            }
        });
    }

    private void placerLesElements() {
        getContentPane().add(nord, "North");
        getContentPane().add(sud, "South");
        getContentPane().add(est, "East");
        getContentPane().add(ouest, "West");
        getContentPane().add(centre, "Center");
        nord.setPreferredSize(new Dimension(0, 25));
        sud.setPreferredSize(new Dimension(0, 50));
        centre.setPreferredSize(new Dimension(250, 250));
        est.setPreferredSize(new Dimension(50, 25));
        ouest.setPreferredSize(new Dimension(50, 25));
        borderLayout.setHgap(10);
        borderLayout.setVgap(10);

    }

    private void definirLesMenus() {

        definirLesListener();

        setJMenuBar(new JMenuBar());
        menuBar = new JMenuBar();
        actions = new JMenu("Actions");
        menuBar.add(actions);
        itemNord = new JMenuItem(enleverMettre);
        itemNord.setText("Enlever le nord");
        actions.add(itemNord);
        itemSud = new JMenuItem(enleverMettre);
        itemSud.setText("Enlever le sud");
        actions.add(itemSud);
        itemEst = new JMenuItem(enleverMettre);
        itemEst.setText("Enlever l'est");
        actions.add(itemEst);
        itemOuest = new JMenuItem(enleverMettre);
        itemOuest.setText("Enlever l'ouest");
        actions.add(itemOuest);
        itemCentre = new JMenuItem(enleverMettre);
        itemCentre.setText("Enlever le centre");
        actions.add(itemCentre);


        itemDimNord = new JMenuItem(dimensionner);
        itemDimNord.setText("Dimensionner le nord");
        actions.add(itemDimNord);
        itemDimSud = new JMenuItem(dimensionner);
        itemDimSud.setText("Dimensionner le sud");
        actions.add(itemDimSud);
        itemDimEst = new JMenuItem(dimensionner);
        itemDimEst.setText("Dimensionner l'est");
        actions.add(itemDimEst);
        itemDimOuest = new JMenuItem(dimensionner);
        itemDimOuest.setText("Dimensionner l'ouest");
        actions.add(itemDimOuest);
        itemDimCentre = new JMenuItem(dimensionner);
        itemDimCentre.setText("Dimensionner le centre");
        actions.add(itemDimCentre);

        intervalles = new JMenuItem(modifierLesIntervalles);
        intervalles.setText("Définir les espaces entre les éléments");

        actions.add(intervalles);

        pack = new JMenuItem(new Action() {
            @Override
            public Object getValue(String key) {
                switch(key) {
                    case "Name" :
                        return "Agencer les éléments";
                }
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {
            }

            @Override
            public boolean isEnabled() {
                return true;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {
            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                pack();
            }
        });
        
        actions.add(pack);

        getJMenuBar().add(menuBar);
        setNames();
    }

    private void definirLesListener() {
        enleverMettre = new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {
            }

            @Override
            public void setEnabled(boolean b) {
            }

            @Override
            public boolean isEnabled() {
                return true;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {
            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                JMenuItem item = (JMenuItem) e.getSource();
                switch (item.getText()) {
                    case "Enlever le nord":
                        getContentPane().remove(nord);
                        item.setText("Remettre le nord");

                        break;
                    case "Remettre le nord":
                        getContentPane().add(nord, "North");
                        item.setText("Enlever le nord");

                        break;
                    case "Enlever le sud":
                        getContentPane().remove(sud);
                        item.setText("Remettre le sud");

                        break;
                    case "Remettre le sud":
                        getContentPane().add(sud, "South");
                        item.setText("Enlever le sud");

                        break;
                    case "Enlever l'est":
                        getContentPane().remove(est);
                        item.setText("Remettre l'est");

                        break;
                    case "Remettre l'est":
                        getContentPane().add(est, "East");
                        item.setText("Enlever l'est");

                        break;
                    case "Enlever l'ouest":
                        getContentPane().remove(ouest);
                        item.setText("Remettre l'ouest");

                        break;
                    case "Remettre l'ouest":
                        getContentPane().add(ouest, "West");
                        item.setText("Enlever l'est");

                        break;
                    case "Enlever le centre":
                        getContentPane().remove(centre);
                        item.setText("Remettre le centre");

                        break;
                    case "Remettre le centre":
                        getContentPane().add(centre, "Center");
                        item.setText("Enlever le sud");

                        break;
                }
                pack();
            }
        };
        dimensionner = new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {
            }

            @Override
            public void setEnabled(boolean b) {
            }

            @Override
            public boolean isEnabled() {
                return true;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {
            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                JMenuItem item = (JMenuItem) e.getSource();
                int hauteur = 0;
                int largeur = 0;
                boolean ok = true;
                while (ok) {
                    try {
                        hauteur = Integer.parseInt(JOptionPane.showInputDialog("Donner la hauteur : "));
                        ok = false;
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "Entrez un entier");
                        ok = true;
                    }
                }
                ok = true;
                while (ok) {
                    try {
                        largeur = Integer.parseInt(JOptionPane.showInputDialog("Donner la largeur : "));
                        ok = false;
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "Entrez un entier");
                        ok = true;
                    }
                }

                Dimension dimension = new Dimension(largeur, hauteur);
                switch (item.getText()) {
                    case "Dimensionner le nord":
                        nord.setPreferredSize(dimension);

                        break;
                    case "Dimensionner le sud":
                        sud.setPreferredSize(dimension);



                        break;
                    case "Dimensionner l'est":
                        est.setPreferredSize(dimension);



                        break;
                    case "Dimensionner l'ouest":
                        ouest.setPreferredSize(dimension);



                        break;
                    case "Dimensionner le centre":
                        centre.setPreferredSize(dimension);



                        break;
                }
                setNames();
                pack();
            }
        };
        modifierLesIntervalles = new Action() {
            @Override
            public Object getValue(String key) {
                switch (key) {
                    case "Name":
                        return "Modifier les intervalles";
                }
                return null;
            }

            @Override
            public void putValue(String key, Object value) {
            }

            @Override
            public void setEnabled(boolean b) {
            }

            @Override
            public boolean isEnabled() {
                return true;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {
            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                JMenuItem item = (JMenuItem) e.getSource();
                int hauteur = 0;
                int largeur = 0;
                boolean ok = true;
                while (ok) {
                    try {
                        hauteur = Integer.parseInt(JOptionPane.showInputDialog("Donner la hauteur entre intervalles : "));
                        ok = false;
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "Entrez un entier");
                        ok = true;
                    }
                }
                ok = true;
                while (ok) {
                    try {
                        largeur = Integer.parseInt(JOptionPane.showInputDialog("Donner la largeur entre intervalles : "));
                        ok = false;
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "Entrez un entier");
                        ok = true;
                    }
                }


                borderLayout.setHgap(hauteur);
                borderLayout.setVgap(largeur);

                pack();
            }
        };

    }

    private void setNames() {
        nord.setText("Nord ("+nord.getPreferredSize().height+", "+nord.getPreferredSize().width+")");
        sud.setText("Sud ("+sud.getPreferredSize().height+", "+sud.getPreferredSize().width+")");
        est.setText("Est ("+est.getPreferredSize().height+", "+est.getPreferredSize().width+")");
        ouest.setText("Ouest ("+ouest.getPreferredSize().height+", "+ouest.getPreferredSize().width+")");
        centre.setText("Centre ("+centre.getPreferredSize().height+", "+centre.getPreferredSize().width+")");
    }
}
