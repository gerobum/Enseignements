/*
 * ChoixDeFontes.java
 */
package exo4;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ChoixDeFontes extends JFrame {

    // Remarquez que les attributs peuvent être créés ici. 
    private JLabel centre = new JLabel();
    private final JPanel sud = new JPanel();
    private final JPanel est = new JPanel();
    // Remplir la "boite à liste" des noms de fontes existantes dans l'environnement
    private final JComboBox listePolices = new JComboBox(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
    private final JCheckBox gras = new JCheckBox("Gras");
    private final JCheckBox italique = new JCheckBox("Italique");
    private final JTextField taille = new JTextField("20"); // Par défaut la taille sera 20

    /**
     * Crée une JFrame qui permet de voir les différentes polices de caractères
     * qui existent dans l'environnement. Une boite à liste permet de choisir
     * une police de caractères Un champ de saisie autorise le choix de la
     * taille Deux cases à cocher permettent de sélectionner de changer la
     * graisse de la police et de la mettre en italique ou non.
     *
     */
    public ChoixDeFontes() {
        super("Choisir une police de caractères");

        miseEnPlaceDesComposants();
        miseEnPlaceDesEcouteurs();
        miseEnPlaceUI();
    }

    // Organiser les composants
    private void miseEnPlaceDesComposants() {
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
        String nomFonte = (String) listePolices.getSelectedObjects()[0];
        // pour le placer dans le label
        centre.setText(nomFonte);
        // Détermination de la fonte au départ et de ses dimensions
        Font fonte = new Font(nomFonte, Font.PLAIN, 20);
        centre.setFont(fonte);
        centre.setPreferredSize(new Dimension(getFontMetrics(fonte).stringWidth(nomFonte), getFontMetrics(fonte).getHeight()));
        getContentPane().add(centre, "Center");
        getContentPane().add(sud, "South");
    }

    private void miseEnPlaceDesEcouteurs() {
        ActionListener action = new ActionListener() {
            /**
             * Détermine la fonte selectionnée, sa graisse, son style, sa taille
             * en consultat les différentes options choisies et effectue les
             * modifications en conséquence en recalculant notamment la
             * dimension occupée par la nouvelle écriture. Remarquez la gestion
             * des exceptions pour contrôler les erreurs de saisie dans le champ
             * de saisie.
             *
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomFonte = (String) listePolices.getSelectedItem();
                int taille = 20;
                try {
                    taille = Integer.parseInt(ChoixDeFontes.this.taille.getText());
                } catch (NumberFormatException n) {
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
                if (ChoixDeFontes.this.italique.isSelected()) {
                    style = style | Font.ITALIC;
                }
                Font fonte = new Font(nomFonte, style, taille);
                // -------------------------------------------
                // Si on veut éviter d'utiliser FontMetrics
                // -------------------------------------------
                // getContentPane().remove(centre);
                // centre = new JLabel(nomFonte, JLabel.CENTER);
                // getContentPane().add(centre, "Center");
                // -------------------------------------------
                centre.setFont(fonte);
                centre.setText(nomFonte);
                FontMetrics métrique = getFontMetrics(fonte);
                centre.setPreferredSize(new Dimension(métrique.stringWidth(nomFonte), métrique.getHeight()));
                pack();
            }
        };

        listePolices.addActionListener(action);
        gras.addActionListener(action);
        italique.addActionListener(action);
        // Un actionListener pour un champ de saisie sera invoqué lorsqu'on appuie sur "enter"
        taille.addActionListener(action);
    }

    private void miseEnPlaceUI() {

        setVisible(true);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new ChoixDeFontes();
    }

}
