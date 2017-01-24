/*
 * ChoixDeFontes.java
 */

package jwindow;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 **
 * La même chose que la classe ChoixDeFontes du paquetage jframe, mais cette 
 * fois à l'intérieur d'un JWindow plutôt qu'un JFrame
 
 * @author Yvan Maillot
 */
public class ChoixDeFontes extends JWindow {

    private JLabel centre = new JLabel();
    private JPanel sud = new JPanel();
    private JPanel est = new JPanel();

    private JComboBox listePolices = new JComboBox(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
    private JCheckBox gras = new JCheckBox("Gras");
    private JCheckBox italique = new JCheckBox("Italique");
    private JTextField taille = new JTextField("20"); // Par défaut la taille sera 20
    

    public ChoixDeFontes() {
        // Pas de titre
        // super("Choisir une police de caractères");
        miseEnPage();
        placerLesEcouteurs();                
    }
    // Organiser les composants
    private void miseEnPage() {
        // Par défaut, la première fonte de la liste est sélectionnée
        listePolices.setSelectedItem(0);
        sud.add(listePolices);
        // Un gestionnaire de placement de type GridLayout(0, 1)
        // positionnera ses composants sur une colonne et autant 
        // de lignes que nécessaire.
        est.setLayout(new GridLayout(0, 1));
        est.add(gras);
        est.add(italique);
        est.add(new JLabel("Taille", JLabel.CENTER));
        est.add(taille);
        
        getContentPane().setBackground(Color.WHITE);
        
        getContentPane().add(est, "East");
        // On récupère le nom de la fonte sélectionnée
        String nomFonte = (String)listePolices.getSelectedObjects()[0];
        // pour le placer dans le label
        centre.setText(nomFonte);
        // Détermination de la fonte au départ et de ses dimensions
        Font fonte = new Font(nomFonte, Font.PLAIN, 20);
        centre.setFont(fonte);
        centre.setPreferredSize(new Dimension(getFontMetrics(fonte).stringWidth(nomFonte), getFontMetrics(fonte).getHeight()));
        getContentPane().add(centre, "Center");
        getContentPane().add(sud, "South");
        setVisible(true);
        pack();
        // Pas de bouton pour quitter
        // setDefaultCloseOperation(EXIT_ON_CLOSE);        
    }    
    private void placerLesEcouteurs() {
        ActionListener action = new ActionListener() {
            /**
             * Détermine la fonte selectionnée, sa graisse, son style, sa taille
             * en consultat les différentes options choisies et effectue les modifications
             * en conséquence en recalculant notamment la dimension occupée par la nouvelle écriture.
             * Remarquez la gestion des exceptions pour contrôler les erreurs de saisie dans le champ
             * de saisie.
             **/
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
                FontMetrics métrique = getFontMetrics(fonte);
                centre.setFont(fonte)    ;
                centre.setPreferredSize(new Dimension(métrique.stringWidth(nomFonte), métrique.getHeight()));
                centre.setText(nomFonte);
                pack();
                
            }
        };

        listePolices.addActionListener(action);
        gras.addActionListener(action);
        italique.addActionListener(action);
        // Un actionListener pour un champ de saisie sera invoqué lorsqu'on appuie sur "enter"
        taille.addActionListener(action);
    }
    
    public static void main(String[] args) {
        new ChoixDeFontes();
    }
    
}
