/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardLayout;



import java.awt.CardLayout;
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
import javax.swing.SwingUtilities;

/**
 *
 * @author yvan
 */
public class TestCardLayout extends JFrame {
     private final JLabel nord, sud, est, ouest, centre;
    private JMenuBar menuBar;
    private JMenu actions;
    private JMenuItem itemNord;
    private Action afficher;
    private JMenuItem itemSud;
    private JMenuItem itemEst;
    private JMenuItem itemOuest;
    private JMenuItem itemCentre;
    private final CardLayout cardLayout = new CardLayout();

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

    public TestCardLayout() {
        super("Test CardLayout");
        nord = new JLabel("", JLabel.CENTER);
        sud = new JLabel("", JLabel.CENTER);
        est = new JLabel("", JLabel.CENTER);
        ouest = new JLabel("", JLabel.CENTER);
        centre = new JLabel("", JLabel.CENTER);

        definirLesElements();

        getContentPane().setLayout(cardLayout);

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
                new TestCardLayout();
            }
        });
    }

    private void placerLesElements() {
        getContentPane().add(nord, "nord");
        getContentPane().add(sud, "sud");
        getContentPane().add(est, "est");
        getContentPane().add(ouest, "ouest");
        getContentPane().add(centre, "centre");
        nord.setPreferredSize(new Dimension(100, 200));
        sud.setPreferredSize(new Dimension(45, 50));
        centre.setPreferredSize(new Dimension(250, 250));
        est.setPreferredSize(new Dimension(50, 10));
        ouest.setPreferredSize(new Dimension(75, 25));
        cardLayout.setHgap(10);
        cardLayout.setVgap(10);

    }

    private void definirLesMenus() {

        definirLesListener();

        setJMenuBar(new JMenuBar());
        menuBar = new JMenuBar();
        actions = new JMenu("Actions");
        menuBar.add(actions);
        itemNord = new JMenuItem(afficher);
        itemNord.setText("Afficher le nord");
        actions.add(itemNord);
        itemSud = new JMenuItem(afficher);
        itemSud.setText("Afficher le sud");
        actions.add(itemSud);
        itemEst = new JMenuItem(afficher);
        itemEst.setText("Afficher l'est");
        actions.add(itemEst);
        itemOuest = new JMenuItem(afficher);
        itemOuest.setText("Afficher l'ouest");
        actions.add(itemOuest);
        itemCentre = new JMenuItem(afficher);
        itemCentre.setText("Afficher le centre");
        actions.add(itemCentre);
        
        getJMenuBar().add(menuBar);
    }

    private void definirLesListener() {
        afficher = new Action() {
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
                    case "Afficher le nord":
                        cardLayout.next(getContentPane());
                        break;

                    case "Afficher le sud":
                        cardLayout.show(getContentPane(), "sud");
                        break;

                    case "Afficher l'est":
                        cardLayout.show(getContentPane(), "est");

                        break;

                    case "Afficher l'ouest":
                        cardLayout.show(getContentPane(), "ouest");

                        break;

                    case "Afficher le centre":
                        cardLayout.show(getContentPane(), "centre");

                        break;

                }
                pack();
            }
        };
    }

 
}

