/**
 * Les classes Sujet et Observateur que nous avons écrites existent en fait
 * déjà dans l'API Java. Il s'agit de l'interface Observer (qui est notre Observateur)
 * et la classe Observable (qui est notre Sujet abstrait)
 */
package conversionDevisesAvecAPI;

import conversionDevisesAvecAPI.patron.Euro;
import conversionDevisesAvecAPI.patron.TextFieldObservateur;
import conversionDevisesAvecAPI.patron.SliderObservateur;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author maillot
 */
public class Testeur extends JFrame {

    private Euro euro = new Euro();
    private final JButton ajout = new JButton("Ajouter une devise");
    private final DialogueAjout dialogue = new DialogueAjout();
    private final JPanel centre = new JPanel();
    
    private final Font font = new Font("Georgia", Font.BOLD, 25);
    private GridBagConstraints cst = new GridBagConstraints();

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

    private void lireFichierDeDevises(String nomFichier) {
        try {
            Scanner in = new Scanner(conversionDevises.Testeur.class.getResourceAsStream(nomFichier));
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

    private void ajoutDUneDevise(boolean sousFormeDeSlider, String nomDevise, double valeurDevise) {
        final JPanel panneau = new JPanel(new BorderLayout());
        JPanel panneauPourBouton = new JPanel();
        JButton fin = new JButton("x");
        panneauPourBouton.add(fin);
        panneau.add(panneauPourBouton, "West");
        if (sousFormeDeSlider) {
            cst.gridx = 0;
            cst.gridy++;
            cst.weightx = 6.0;

            final SliderObservateur ds = new SliderObservateur(nomDevise, valeurDevise, euro);

            panneau.add(ds);

            fin.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    euro.deleteObserver(ds);
                    centre.remove(panneau);
                    centre.validate();
                }
            });

            centre.add(panneau, cst);

        } else {
            cst.gridy++;
            cst.gridx = 0;
            cst.weightx = 6.0;

            final TextFieldObservateur ds = new TextFieldObservateur(nomDevise, valeurDevise, euro);

            panneau.add(ds);

            fin.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    euro.deleteObserver(ds);
                    centre.remove(panneau);
                    centre.validate();
                }
            });

            centre.add(panneau, cst);

            cst.gridx = 1;
            cst.weightx = 1.0;

            centre.add(new JLabel(nomDevise), cst);
        }
    }

    public static void main(String[] args) {
        new Testeur();
    }
}
