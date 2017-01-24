
package conversionDevises;

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
    // ####
    private Euro<Double> euro = new Euro<>(1.0);
    private JButton ajout = new JButton("Ajouter une devise");
    private DialogueAjout dialogue = new DialogueAjout();
    private GridBagConstraints cst = new GridBagConstraints();
    private JPanel centre = new JPanel();

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
                    if (dialogue.getSousFormeDeSlider()) {
                        cst.gridx = 0;
                        cst.gridy++;
                        cst.weightx = 6.0;
                        // #### 
                        centre.add(new DeviseJSlider(dialogue.getDevise(), dialogue.getPrixPour1Euro(), euro), cst);                                                
                    } else {
                        cst.gridy++;
                        cst.gridx = 0;
                        cst.weightx = 6.0;
                        // #### 
                        centre.add(new DeviseJTextField(dialogue.getDevise(), dialogue.getPrixPour1Euro(), euro), cst); 
                        cst.gridx = 1;
                        cst.weightx = 1.0;
                        centre.add(new JLabel(dialogue.getDevise()), cst);                                                
                    }                 
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
            if (sousFormeDeSlider) {
                cst.gridy++;
                cst.gridx = 0;
                cst.weightx = 6.0;
                centre.add(new DeviseJSlider(nomDevise, valeurDevise, euro), cst);                        

            } else {
                cst.gridy++;
                cst.gridx = 0;
                cst.weightx = 6.0;
                centre.add(new DeviseJTextField(nomDevise, valeurDevise, euro), cst); 
                cst.gridx = 1;
                cst.weightx = 1.0;
                centre.add(new JLabel(nomDevise), cst);                                                
            }        
    }
    
    public static void main(String[] args) {
        new Testeur();
    }
}
