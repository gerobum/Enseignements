/**
    Les classes Sujet et Observateur que nous avons écrites existent en fait
 * déjà dans l'API Java. Il s'agit de l'interface Observer (qui est notre Observateur)
 * et la classe Observable (qui est notre Sujet abstrait)
 */
package conversionDevisesAvecAPI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author maillot
 */
public class Testeur extends JFrame {

    private Euro euro = new Euro();
    private JButton ajout = new JButton("Ajouter une devise");
    private DialogueAjout dialogue = new DialogueAjout();
    private GridBagConstraints cst = new GridBagConstraints();
    private JPanel centre = new JPanel();
    private int nLignes = 0;

    public Testeur() {
        super("Ajouter ou supprimer des devises");

        cst.fill = GridBagConstraints.BOTH;
        ajout.setFont(new Font("Georgia", Font.BOLD, 25));
        int y = 0;

        centre.setLayout(new GridBagLayout());

        getContentPane().add(centre, "Center");
        getContentPane().add(ajout, "South");
        ajout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dialogue.setVisible(true);
                if (dialogue.isOk()) {
                    ajoutDUneDevise(dialogue.getSousFormeDeSlider(), dialogue.getDevise(), dialogue.getPrixPour1Euro());
                    pack();
                }
            }
        });




        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public Testeur(boolean europe) {
        this();
        if (europe) {
            /*
            EUR - Euro  	--------  	DEM - Mark allemand  	1.95583
            FRF - Franc français 	6.55957 	NLG - Florin hollandais 	2.20371
            BEF/LUF - Franc belge 	40.3399 	FIM - Mark finlandais 	5.94573
            ITL - Lire italienne 	1936.27 	ATS - Schilling autrichien 	13.7603
            ESP - Peseta espagnole 	166.386 	IEP - Livre irlandaise 	0.787564
            PTE - Escudo portugais 	200.482 	GRD - Drachme grec 	340.750
             */
            String nom;
            double valeur;
            ajoutDUneDevise(false, "€", 1);
            ajoutDUneDevise(true, "€", 1);
            ajoutDUneDevise(false, "Franc français", 6.55957);
            ajoutDUneDevise(true, "Franc français", 6.55957);
            nom = "Franc belge";
            valeur = 40.3399;
            ajoutDUneDevise(false, nom, valeur);
            ajoutDUneDevise(true, nom, valeur);

            ajoutDUneDevise(false, "Lire italienne", 1936.27);
            ajoutDUneDevise(true, "Lire italienne", 1936.27);
            nom = "Peseta espagnole";
            valeur = 166.386;
            ajoutDUneDevise(false, nom, valeur);
            ajoutDUneDevise(true, nom, valeur);

            nom = "Escudo portugais";
            valeur = 200.482;
            ajoutDUneDevise(false, nom, valeur);
            ajoutDUneDevise(true, nom, valeur);

            nom = "Mark allemand";
            valeur = 1.95583;
            ajoutDUneDevise(false, nom, valeur);
            ajoutDUneDevise(true, nom, valeur);

            nom = "Florin hollandais";
            valeur = 2.20371;
            ajoutDUneDevise(false, nom, valeur);
            ajoutDUneDevise(true, nom, valeur);

            nom = "Mark finlandais";
            valeur = 5.94573;
            ajoutDUneDevise(false, nom, valeur);
            ajoutDUneDevise(true, nom, valeur);

            nom = "Schilling autrichien";
            valeur = 13.7603;
            ajoutDUneDevise(false, nom, valeur);
            ajoutDUneDevise(true, nom, valeur);

            nom = "Livre irlandaise";
            valeur = 0.787564;
            ajoutDUneDevise(false, nom, valeur);
            ajoutDUneDevise(true, nom, valeur);

            nom = "Drachme grec";
            valeur = 340.750;
            ajoutDUneDevise(false, nom, valeur);
            ajoutDUneDevise(true, nom, valeur);
        }
        pack();
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
            
            final DeviseJSlider ds = new DeviseJSlider(nomDevise, valeurDevise, euro);
            
            
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
            
            final DeviseJTextField ds = new DeviseJTextField(nomDevise, valeurDevise, euro);
         
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
