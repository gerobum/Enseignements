/*
 * ChoixDeFontes.java
 *
 * Created on 4 f�vrier 2008, 08:49
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package jdialog.modal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Yvan
 */
public class ChoixDeFontes extends JDialog {
 
    private JLabel centre = new JLabel();
    private JPanel sud = new JPanel();
    private JPanel est = new JPanel();

    private JComboBox listePolices = new JComboBox(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
    private JCheckBox gras = new JCheckBox("Gras");
    private JCheckBox italique = new JCheckBox("Italique");
    private JTextField taille = new JTextField("20"); 
    private Font policeChoisie;

    public Font policeChoisie() {
        return policeChoisie;
    }
    
    
    public ChoixDeFontes() {
        //super("Choisir une police de caractères");
        miseEnPage();
        placerLesEcouteurs(); 
                
        pack();
        
        setDefaultCloseOperation(HIDE_ON_CLOSE); 
        // setModal(true); Obsolète
        setModalityType(ModalityType.APPLICATION_MODAL);
    }
    
    private void miseEnPage() {
        
        listePolices.setSelectedItem(0);
        sud.add(listePolices);
        
        
        
        est.setLayout(new GridLayout(0, 1));
        est.add(gras);
        est.add(italique);
        est.add(new JLabel("Taille", JLabel.CENTER));
        est.add(taille);
        
        getContentPane().setBackground(Color.WHITE);
        
        getContentPane().add(est, "East");
        
        String nomFonte = (String)listePolices.getSelectedObjects()[0];
        
        centre.setText(nomFonte);
        
        Font fonte = new Font(nomFonte, Font.PLAIN, 20);
        centre.setFont(fonte);
        centre.setPreferredSize(new Dimension(getFontMetrics(fonte).stringWidth(nomFonte), getFontMetrics(fonte).getHeight()));
        getContentPane().add(centre, "Center");
        getContentPane().add(sud, "South");       
    }    
    private void placerLesEcouteurs() {
        ActionListener action = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String nomFonte = (String) listePolices.getSelectedItem();
                int taille = 20;
                try {
                    taille = Integer.parseInt(ChoixDeFontes.this.taille.getText());
                } catch(NumberFormatException n) {
                    ChoixDeFontes.this.taille.setText("20");
                }
                if (taille < 1) {
                    taille = 1;
                    ChoixDeFontes.this.taille.setText("1");
                } else if (taille > 200) {
                    taille = 200;
                    ChoixDeFontes.this.taille.setText("200");
                }
                    
                
                int style = Font.PLAIN;
                if (ChoixDeFontes.this.gras.isSelected()) {
                    style = Font.BOLD;
                }
                if (ChoixDeFontes.this.italique.isSelected())
                    style = style | Font.ITALIC;
                policeChoisie = new Font(nomFonte, style, taille);
                JLabel centre = ChoixDeFontes.this.centre;
                FontMetrics metrique = getFontMetrics(policeChoisie);
                centre.setFont(policeChoisie)    ;
                centre.setPreferredSize(new Dimension(metrique.stringWidth(nomFonte), metrique.getHeight()));
                centre.setText(nomFonte);
                pack();
                
            }
        };

        listePolices.addActionListener(action);
        gras.addActionListener(action);
        italique.addActionListener(action);
        
        taille.addActionListener(action);
    }

    
}