/*
 * ChoixCouleurFonte.java
 */
package jdialog.modal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Yvan
 */
public class ChoixCouleurFonte extends JFrame {

    private final JLabel message = new JLabel("<html>Vous avez demandé la police ?<br><br>Veuillez patienter</html>", JLabel.CENTER);
    private ChoixDeCouleurs dialogueCouleur;
    private ChoixDeFontes dialogueFonte;
    private JMenuItem itemCouleur;
    private JMenuItem itemPolice;

    public ChoixCouleurFonte() {
        super("Choix de la couleur et de la fonte");
        miseEnPlaceDuMenu();
        miseEnPlaceDesComposants();
        miseEnPlaceDesEcouteurs();
        miseEnPlaceUI();
    }

    private void miseEnPlaceDuMenu() {
        setJMenuBar(new JMenuBar());
        getJMenuBar().add(new JMenu("Changements"));
        itemCouleur = new JMenuItem("Couleur");
        itemPolice = new JMenuItem("Police");
        getJMenuBar().getMenu(0).add(itemCouleur);
        getJMenuBar().getMenu(0).add(itemPolice);
    }

    private void miseEnPlaceDesComposants() {
        getContentPane().add(message);
        setPreferredSize(new Dimension(324, 200));

        dialogueCouleur = new ChoixDeCouleurs(getContentPane().getBackground());
        dialogueFonte = new ChoixDeFontes(message.getFont());
    }

    private void miseEnPlaceDesEcouteurs() {
        itemCouleur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("avant");
                dialogueCouleur.setVisible(true);
                System.out.println("après");
                Color couleurFond = dialogueCouleur.couleurChoisie();
                Color couleurTexte = new Color(255 - couleurFond.getRed(), 255 - couleurFond.getGreen(), 255 - couleurFond.getBlue());
                getContentPane().setBackground(couleurFond);
                message.setForeground(couleurTexte);
                pack();

            }
        });
        itemPolice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogueFonte.setVisible(true);
                message.setText("<html>Vous avez demandé la police ?<br><br>Veuillez patienter</html>");
                message.setFont(dialogueFonte.policeChoisie());
                pack();
            }
        });
    }

    private void miseEnPlaceUI() {
        setVisible(true);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ChoixCouleurFonte();
    }
}
