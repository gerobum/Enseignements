package conversionDevises;

import conversionDevises.patron.TextFieldObservateur;
import conversionDevises.patron.SliderObservateur;
import conversionDevises.patron.Euro;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author maillot
 */
public class Testeur extends JFrame {

    // ####
    private Euro euro = new Euro(1.0);
    private JButton ajout = new JButton("Ajouter une devise");
    private DialogueAjout dialogue = new DialogueAjout();
    private GridBagConstraints cst = new GridBagConstraints();
    private JPanel centre = new JPanel();
    private final Font font = new Font("Georgia", Font.BOLD, 25);

    public Testeur() {
        this(null);
    }

    public Testeur(String nomFichier) {

        super("Ajouter ou supprimer des devises");

        cst.fill = GridBagConstraints.BOTH;
        ajout.setFont(font);

        centre.setLayout(new GridBagLayout());

        getContentPane().add(centre, "Center");
        getContentPane().add(ajout, "South");
        ajout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dialogue.setVisible(true);
                if (dialogue.isOk()) {
                    if (dialogue.getSousFormeDeSlider()) {
                        cst.gridx = 0;
                        cst.gridy++;
                        cst.weightx = 6.0;
                        // #### 
                        centre.add(new SliderObservateur(dialogue.getDevise(), dialogue.getPrixPour1Euro(), euro), cst);
                    } else {
                        cst.gridy++;
                        cst.gridx = 0;
                        cst.weightx = 6.0;
                        // #### 
                        centre.add(new TextFieldObservateur(dialogue.getDevise(), dialogue.getPrixPour1Euro(), euro), cst);
                        cst.gridx = 1;
                        cst.weightx = 1.0;
                        JLabel label = new JLabel(dialogue.getDevise());
                        label.setFont(font);
                        centre.add(label, cst);
                    }
                    pack();
                }
            }
        });

        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        if (nomFichier != null) {
            lireFichierDeDevises(nomFichier);

            pack();
        }
    }

    private void ajoutDUneDevise(boolean sousFormeDeSlider, String nomDevise, double valeurDevise) {
        if (sousFormeDeSlider) {
            cst.gridy++;
            cst.gridx = 0;
            cst.weightx = 6.0;
            centre.add(new SliderObservateur(nomDevise, valeurDevise, euro), cst);

        } else {
            cst.gridy++;
            cst.gridx = 0;
            cst.weightx = 6.0;
            centre.add(new TextFieldObservateur(nomDevise, valeurDevise, euro), cst);
            cst.gridx = 1;
            cst.weightx = 1.0;
            JLabel label = new JLabel(nomDevise);
            label.setFont(font);
            centre.add(label, cst);
        }
    }

    private void lireFichierDeDevises(String nomFichier) {
        try {
            Scanner in = new Scanner(new File("/home/yvan/Development/Enseignements/M1/GenieLogiciel/Corrections/DesignPatterns/Observer/PatronObservateurCorrige/src/conversionDevises/europe.devises")/*Testeur.class.getResourceAsStream(nomFichier)*/);
            while (in.hasNextLine()) {
                String line = in.nextLine();
                if (!line.trim().startsWith("#")) {
                    Scanner sin = new Scanner(line);
                    sin.useDelimiter(";\\s*");
                    String nom = sin.next();
                    double taux = sin.nextDouble();
                    ajoutDUneDevise(false, nom, taux);
                    ajoutDUneDevise(true, nom, taux);
                }

            }
        } catch (Exception e) {
            System.err.println(nomFichier + " n'existe pas.");
        }
    }

    public static void main(String[] args) {
        new Testeur();
    }
}
