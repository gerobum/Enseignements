/*
 * ChoixDeCouleurs.java
 */
package jframe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Permet de visualiser les couleurs en faisant varier leur composantes rouge,
 * verte et bleue à l'aide de 3 glisseurs (sliders)
 *
 * @author Yvan Maillot
 */
public class ChoixDeCouleurs extends JFrame {

    private JPanel centre, sud;
    private JSlider rouge, vert, bleu;

    /**
     * Crée une JFrame dont le titre est "Choisir une couleur" dans laquelle se
     * trouvent trois glisseurs (JSlider) qui permettent de faire varier les
     * composantes rouge, verte et bleue de la couleur du panneau qui se trouve
     * au centre.
     *
     */
    public ChoixDeCouleurs() {
        // Appel du constructeur de la super-classe avec le titre choisi
        super("Choisir une couleur");
        // Deux méthodes privées qui permettent d'organiser les actions du constructeur
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
        // Création du panneau dans lequel seront placés les sliders.
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
        // Détermination du gestionnaire de placement pour le panneau dans lequel se trouveront les sliders
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
        // En effet, ChangeListener est une interface qui ne peut donc être instanciée.
        // Ce qui est fait ici, c'est définir en ligne une classe anonyme qui implémente ChangeListener.
        // Cette classe anonyme a la particularité d'être interne à la classe ChoixDeCouleurs
        // En tant que telle, elle accède aux membres privés de la classe dans laquelle elle est incluse.
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

        // Rendre la frame visible
        setVisible(true);
        // Dimensionner la frame
        pack();
        // Prévoir l'arrêt du processus lorqu'on clique sur la croix
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new ChoixDeCouleurs();
    }

}
