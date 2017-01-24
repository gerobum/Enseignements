package dialogue;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Boîte de dialogue pour modifier la dimension de la grille.
 * @author Yvan
 */
public class BoiteDialogueChoixGrille extends JDialog {
    private final JButton boutonValidation;
    private final JButton boutonAnnulation;
    private JSlider sliderChoixNbLignes;
    private JSlider sliderChoixNbColonnes;
    private JLabel labelNbLignes;
    private JLabel labelNbColonnes;
    
    private boolean OK = false;
    
    private final JPanel panneauSud;
    private final JPanel panneauCentre;
    
    // Classe interne, utilisée pour retourner la dimension choisie.
    public class Dimension {
        public final int ligne;
        public final int colonne;
        
        public Dimension(int ligne, int colonne) {
            this.ligne = ligne;
            this.colonne = colonne;
        }
    }
    
    public BoiteDialogueChoixGrille(Frame frame) {
        super(frame, "Dimension du puzzle", true);

        
        boutonValidation = new JButton("Okay");
        boutonValidation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
                OK = true;
            }
        });
        
        boutonAnnulation = new JButton("Annulation");
        boutonAnnulation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
                OK = false;
            }
        });        
        
        labelNbLignes = new JLabel(" 3");
        labelNbColonnes  = new JLabel(" 3");
        
        panneauCentre = new JPanel();
        panneauSud = new JPanel();
        panneauSud.setLayout(new GridLayout(2, 2));

        
        sliderChoixNbLignes = new JSlider(2, 10, 3);
        sliderChoixNbLignes.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                String espace;
                espace = sliderChoixNbLignes.getValue() < 10 ? " " : "";
                labelNbLignes.setText(espace+sliderChoixNbLignes.getValue());
            }
        });
        sliderChoixNbColonnes = new JSlider(2, 10, 3);
        sliderChoixNbColonnes.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                String espace;
                espace = sliderChoixNbColonnes.getValue() < 10 ? " " : "";
                labelNbColonnes.setText(espace+sliderChoixNbColonnes.getValue());
            }
        });
        
        JPanel panelLigne = new JPanel();
        JPanel panelColonne = new JPanel();
        
        panelLigne.add(sliderChoixNbLignes);
        panelLigne.add(labelNbLignes);
        panneauSud.add(panelLigne);
        
        panelColonne.add(sliderChoixNbColonnes);
        panelColonne.add(labelNbColonnes);
        panneauSud.add(panelColonne);
        
        panneauCentre.add(boutonValidation);
        panneauCentre.add(boutonAnnulation);
        
        getContentPane().add(panneauCentre, "Center");
        getContentPane().add(panneauSud, "South");
        
        pack();

    }
    
    public Dimension getDimension() {
        return new Dimension(sliderChoixNbLignes.getValue(), sliderChoixNbColonnes.getValue());
    }
    
    public boolean ok() {
        return OK;
    }
    
}
