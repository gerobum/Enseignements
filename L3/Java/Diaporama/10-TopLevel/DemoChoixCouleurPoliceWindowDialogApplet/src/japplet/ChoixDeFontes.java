/*
 * ChoixDeFontes.java

 */

package japplet;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Yvan
 */
public class ChoixDeFontes extends JApplet { 
    private final JLabel centre = new JLabel();
    private final JPanel sud = new JPanel();
    private final JPanel est = new JPanel();

    private final JComboBox listePolices = new JComboBox(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
    private final JCheckBox gras = new JCheckBox("Gras");
    private final JCheckBox italique = new JCheckBox("Italique");
    private final JTextField taille = new JTextField("20");
    

    public ChoixDeFontes() {
        //super("Choisir une police de caractères");
        miseEnPlaceDesComposants();
        miseEnPlaceDesEcouteurs();                
    }

    private void miseEnPlaceDesComposants() {

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
    private void miseEnPlaceDesEcouteurs() {
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
                Font fonte = new Font(nomFonte, style, taille);
                JLabel centre = ChoixDeFontes.this.centre;
                FontMetrics metrique = getFontMetrics(fonte);
                centre.setFont(fonte)    ;
                centre.setPreferredSize(new Dimension(metrique.stringWidth(nomFonte), metrique.getHeight()));
                centre.setText(nomFonte);
                //pack();                
            }
        };

        listePolices.addActionListener(action);
        gras.addActionListener(action);
        italique.addActionListener(action);
        taille.addActionListener(action);
    }
}
