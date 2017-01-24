package jdialog.nonmodal;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
        dialogueCouleur = new ChoixDeCouleurs(getContentPane(), message);
        dialogueFonte = new ChoixDeFontes(message);
        getContentPane().add(message);
        setPreferredSize(new Dimension(324, 200));

    }

    private void miseEnPlaceDesEcouteurs() {
        getJMenuBar().getMenu(0).getItem(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogueCouleur.setVisible(true);
                // La boite de dialogue n'étant pas modale, elle ne bloque pas l'appelant          
            }
        });
        getJMenuBar().getMenu(0).getItem(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogueFonte.setVisible(true);
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
