/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gridBagLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
public class TestGridBagLayout extends JFrame {

  private JLabel a00, a01, a02, a11, a22;
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
  private GridBagLayout gridLayout = new GridBagLayout();

  private void definirLesElements() {
    a00.setOpaque(true);
    a00.setBackground(Color.BLUE.darker().darker());
    a00.setForeground(Color.WHITE);
    a01.setOpaque(true);
    a01.setBackground(Color.RED.darker().darker());
    a01.setForeground(Color.WHITE);
    a02.setOpaque(true);
    a02.setBackground(Color.GREEN.darker());
    a02.setForeground(Color.WHITE);
    a11.setOpaque(true);
    a11.setBackground(Color.PINK.darker());
    a11.setForeground(Color.WHITE);
    a22.setOpaque(true);
    a22.setBackground(Color.ORANGE);
    a22.setForeground(Color.WHITE);
  }

  public TestGridBagLayout() {
    super("Test BorderLayout");
    a00 = new JLabel("", JLabel.CENTER);
    a01 = new JLabel("", JLabel.CENTER);
    a02 = new JLabel("", JLabel.CENTER);
    a11 = new JLabel("", JLabel.CENTER);
    a22 = new JLabel("", JLabel.CENTER);

    definirLesElements();

    getContentPane().setLayout(gridLayout);

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
        new TestGridBagLayout();
      }
    });
  }

  private void placerLesElements() {
    GridBagConstraints gbc = new GridBagConstraints();
    //gbc.gridwidth = GridBagConstraints.REMAINDER;
    //gbc.gridheight = GridBagConstraints.REMAINDER;   
    
    //gbc.fill = GridBagConstraints.BOTH;
    //gbc.fill = GridBagConstraints.HORIZONTAL;
    //gbc.fill = GridBagConstraints.VERTICAL;
    //gbc.fill = GridBagConstraints.NONE;
    
    gbc.gridx = 0;
    gbc.gridy = 0;
    getContentPane().add(a00, gbc);
    
      
    
    gbc.fill = GridBagConstraints.BOTH;
    //gbc.fill = GridBagConstraints.HORIZONTAL;
    //gbc.fill = GridBagConstraints.VERTICAL;
    //gbc.fill = GridBagConstraints.NONE;
    
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.weightx = 2;
   

    getContentPane().add(a02, gbc);
    
      
    
    //gbc.fill = GridBagConstraints.BOTH;
    //gbc.fill = GridBagConstraints.HORIZONTAL;
    //gbc.fill = GridBagConstraints.VERTICAL;
    gbc.fill = GridBagConstraints.NONE;
    
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.gridwidth=2;
    
    
    getContentPane().add(a01, gbc);
      
    
    //gbc.fill = GridBagConstraints.BOTH;
    //gbc.fill = GridBagConstraints.HORIZONTAL;
    //gbc.fill = GridBagConstraints.VERTICAL;
    //gbc.fill = GridBagConstraints.NONE;
    gbc.gridwidth=1;
    gbc.gridx = 1;
    gbc.gridy = 1;
    
    getContentPane().add(a11, gbc);
      
    
    //gbc.fill = GridBagConstraints.BOTH;
    //gbc.fill = GridBagConstraints.HORIZONTAL;
    //gbc.fill = GridBagConstraints.VERTICAL;
    //gbc.fill = GridBagConstraints.NONE;
    
    gbc.gridx = 2;
    gbc.gridy = 2;
    
    getContentPane().add(a22, gbc);
    
    a00.setPreferredSize(new Dimension(100, 200));
    a01.setPreferredSize(new Dimension(45, 50));
    a22.setPreferredSize(new Dimension(250, 250));
    a02.setPreferredSize(new Dimension(50, 10));
    a11.setPreferredSize(new Dimension(150, 25));

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
        switch (key) {
          case "Name":
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
            getContentPane().remove(a00);
            item.setText("Remettre le nord");

            break;
          case "Remettre le nord":
            getContentPane().add(a00, "North");
            item.setText("Enlever le nord");

            break;
          case "Enlever le sud":
            getContentPane().remove(a01);
            item.setText("Remettre le sud");

            break;
          case "Remettre le sud":
            getContentPane().add(a01, "South");
            item.setText("Enlever le sud");

            break;
          case "Enlever l'est":
            getContentPane().remove(a02);
            item.setText("Remettre l'est");

            break;
          case "Remettre l'est":
            getContentPane().add(a02, "East");
            item.setText("Enlever l'est");

            break;
          case "Enlever l'ouest":
            getContentPane().remove(a11);
            item.setText("Remettre l'ouest");

            break;
          case "Remettre l'ouest":
            getContentPane().add(a11, "West");
            item.setText("Enlever l'est");

            break;
          case "Enlever le centre":
            getContentPane().remove(a22);
            item.setText("Remettre le centre");

            break;
          case "Remettre le centre":
            getContentPane().add(a22, "Center");
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
            a00.setPreferredSize(dimension);

            break;
          case "Dimensionner le sud":
            a01.setPreferredSize(dimension);



            break;
          case "Dimensionner l'est":
            a02.setPreferredSize(dimension);



            break;
          case "Dimensionner l'ouest":
            a11.setPreferredSize(dimension);



            break;
          case "Dimensionner le centre":
            a22.setPreferredSize(dimension);



            break;
        }
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



        pack();
      }
    };

  }
}
