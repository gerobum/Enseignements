package manipulateur;

import dialogue.BoiteDialogueChoixGrille;
import exception.DimensionTaquinIncorrecte;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import taquin.Taquin;

/**
 *
 * @author Yvan
 */
public class ManipulateurTaquin {

    private JFrame frame;
    private BoiteDialogueChoixGrille choix;
    private Taquin taquin;
    private BufferedImage image = null;

    public ManipulateurTaquin() {

        frame = new JFrame("Jeu du taquin");

        choix = new BoiteDialogueChoixGrille(frame);

        frame.setJMenuBar(new JMenuBar());
        frame.getJMenuBar().add(new JMenu("Action"));
        // Définition de l'item mélanger
        frame.getJMenuBar().getMenu(0).add(new JMenuItem("Mélanger"));
        frame.getJMenuBar().getMenu(0).getItem(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                taquin.mélanger();
            }
        });

        frame.getJMenuBar().getMenu(0).add(new JSeparator());
        // Définition de l'item "Nouvelles dimensions"
        frame.getJMenuBar().getMenu(0).add(new JMenuItem("Dimensions"));

        frame.getJMenuBar().getMenu(0).getItem(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // Appel (bloquant) de la boîte de dialogue "choix de la dimension"
                choix.setVisible(true);
                // L'utilisateur n'a peut être pas validé son choix, la méthode
                // ok() de la boîte de dialogue est là pour le vérifier.
                if (choix.ok()) {
                    try {
                        // Le choix a bien été validé. La dimension est récupérée
                        // sous la forme d'un objet dont la classe est interne
                        // à la boîte de dialogue.
                        BoiteDialogueChoixGrille.Dimension dimension = choix.getDimension();
                        taquin = new Taquin(dimension.ligne, dimension.colonne);
                        if (image != null) {
                            taquin.setImage(image);
                        }
                        frame.getContentPane().removeAll();
                        frame.getContentPane().add(taquin, "Center");
                    } catch (DimensionTaquinIncorrecte ex) {
                    }
                    frame.pack();
                }
            }
        });

        // Définition de l'item "Choix de l'image"
        frame.getJMenuBar().getMenu(0).add(new JMenuItem("Choix de l'image"));
        frame.getJMenuBar().getMenu(0).getItem(3).addActionListener(new ActionListener() {
            // Action à réaliser pour choisir une image
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    // Le nom d'une image est (éventuellement) sélectionné dans un JFileChooser.
                    JFileChooser chooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "gif", "png");

                    chooser.setFileFilter(filter);

                    int returnVal = chooser.showOpenDialog(frame);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        // Chargement de l'image choisie 
                        image = ImageIO.read(chooser.getSelectedFile().getAbsoluteFile());
                        taquin.setImage(image);
                    }

                } catch (IOException ex) {
                }
                frame.pack();
            }
        });

        // Définition de l'item "Enlever l'image"
        frame.getJMenuBar().getMenu(0).add(new JMenuItem("Enlever l'image"));
        frame.getJMenuBar().getMenu(0).getItem(4).addActionListener(new ActionListener() {
            // Action à réaliser pour choisir une image
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    image = null;
                    BoiteDialogueChoixGrille.Dimension dimension = choix.getDimension();
                    taquin = new Taquin(dimension.ligne, dimension.colonne);
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(taquin, "Center");
                    frame.pack();
                } catch (DimensionTaquinIncorrecte ex) {
                }
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            taquin = new Taquin(3, 3);
            frame.getContentPane().add(taquin, "Center");
        } catch (DimensionTaquinIncorrecte ex) {
        }
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

}
