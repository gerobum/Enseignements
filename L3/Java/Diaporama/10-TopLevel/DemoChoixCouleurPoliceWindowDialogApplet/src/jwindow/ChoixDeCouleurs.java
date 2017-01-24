package jwindow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JWindow;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * La même chose que la classe ChoixDeCouleurs du paquetage jframe, mais cette
 * fois à l'intérieur d'un JWindow plutôt qu'un JFrame
 */
public class ChoixDeCouleurs extends JWindow {

    private JPanel centre, sud;
    private JSlider rouge, vert, bleu;

    public ChoixDeCouleurs() {
        // Il n'y a pas de titre dans un JWindow donc impossible de conserver ceci :
        // super("Choisir une couleur");

        miseEnPlaceDesComposants();
        miseEnPlaceDesEcouteurs();
        miseEnPlaceUI();
    }

    // Organiser les composants 
    private void miseEnPlaceDesComposants() {
        // Création du panneau qui montre la couleur
        centre = new JPanel();
        // Au départ les sliders seront les trois à zéro. 
        // Ca correspond à la couleur noire.
        centre.setBackground(Color.BLACK);
        // Détermination d'une dimension gérée au mieux par le gestionnaire de placement
        centre.setPreferredSize(new Dimension(200, 200));
        // Création du panneau dans lequel sera placé les sliders.
        sud = new JPanel();
        // Création de trois sliders, variant de 0 à 255 et dont le curseur est positonné à 0
        rouge = new JSlider(0, 255, 0);
        vert = new JSlider(0, 255, 0);
        bleu = new JSlider(0, 255, 0);
        // Modifiaction de leur couleur de fond en correspondance avec les composantes qu'ils représentent
        rouge.setBackground(Color.RED);
        vert.setBackground(Color.GREEN);
        bleu.setBackground(Color.BLUE);

        // Placement des panneaux 
        getContentPane().add(centre, "Center");
        getContentPane().add(sud, "South");
        // Détermination du gestionnaire de placement pour le panneau dans lequel se trouvera les sliders
        sud.setLayout(new GridLayout(3, 1));
        // Placement des sliders dans le panneau "sud"
        sud.add(rouge);
        sud.add(vert);
        sud.add(bleu);
    }

    // Rendre les sliders sensibles aux événements provoquées par le déplacement des curseurs des sliders.
    private void miseEnPlaceDesEcouteurs() {
        // Déclaration d'une instance de ChangeListener
        // Remarquez la définition en ligne de la méthode stateChanged.
        // En effet, ChangeListener est une interface qui ne peut donc ętre instanciée.
        // Ce qui est fait ici, c'est la définition en ligne d'une classe anonyme qui implémente ChangeListener.
        // Cette classe anonyme a la particularité d'ętre interne à la classe ChoixDeCouleurs
        // En tant que telle, elle accčde aux membres privés de la classe dans laquelle elle est incluse.
        ChangeListener action = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                centre.setBackground(new Color(
                        // Remarquez ici la syntaxe ChoixDeCouleurs.this qui permet d'atteindre
                        // l'objet dans lequel celui-ci est imbriqué. 
                        ChoixDeCouleurs.this.rouge.getValue(),
                        ChoixDeCouleurs.this.vert.getValue(),
                        ChoixDeCouleurs.this.bleu.getValue()));
            }
        };

        rouge.addChangeListener(action);
        vert.addChangeListener(action);
        bleu.addChangeListener(action);
    }

    private void miseEnPlaceUI() {
        setVisible(true);
        pack();
        // Il n'y a pas non plus de bouton pour quitter la fenêtre
        // setDefaultCloseOperation(EXIT_ON_CLOSE);          
    }

    public static void main(String[] args) {
        new ChoixDeCouleurs();
    }
}
