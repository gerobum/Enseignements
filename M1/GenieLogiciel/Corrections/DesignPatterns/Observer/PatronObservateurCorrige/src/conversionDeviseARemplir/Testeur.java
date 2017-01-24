
package conversionDeviseARemplir;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author maillot
 */
public class Testeur extends JFrame {
    // #### Le sujet observé peut être déclaré ici
    private final JButton ajout = new JButton("Ajouter une devise");
    private DialogueAjout dialogue = new DialogueAjout();
    private GridBagConstraints cst = new GridBagConstraints();
    private JPanel centre = new JPanel();    

    public Testeur() {
        super("Ajouter ou supprimer des devises");        
        
        cst.fill = GridBagConstraints.BOTH;
        
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
                        // #### Remplacer le JSlider par un JSlider spécial étant 
                        // #### aussi un observateur observant le sujet.
                        centre.add(new JSlider(), cst);                                                
                    } else {
                        cst.gridy++;
                        cst.gridx = 0;
                        cst.weightx = 6.0;
                        // #### Remplacer le JTextField par un JTextField spécial étant 
                        // #### aussi un observateur observant le sujet.
                        centre.add(new JTextField(30), cst); 
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
    

    
    public static void main(String[] args) {
        new Testeur();
    }
}
