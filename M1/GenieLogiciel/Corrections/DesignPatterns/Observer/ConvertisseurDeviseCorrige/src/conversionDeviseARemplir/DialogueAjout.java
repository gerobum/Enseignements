package conversionDeviseARemplir;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author maillot
 */
public class DialogueAjout extends JDialog {
    private final JButton ok = new JButton("Ok");
    private final JButton annuler = new JButton("Annuler");
    private JTextField devise = new JTextField();
    private JTextField prixPour1Euro = new JTextField();
    private final JCheckBox choixSlider = new JCheckBox("Sous forme de slider", false);
    private final JPanel centre = new JPanel();
    private final JPanel sud = new JPanel();
    private boolean reponse = false;
    public DialogueAjout() {
        setModal(true);
        centre.setLayout(new GridLayout(3, 2));
        centre.add(new JLabel("Entrez une devise"));
        centre.add(devise);
        centre.add(new JLabel("Entrez sa valeur pour 1 euro"));
        centre.add(prixPour1Euro);
        centre.add(new JLabel(""));
        centre.add(choixSlider);
        
        add(centre, "Center");
        
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        
        sud.add(ok);
        sud.add(annuler);
        
        ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Double.parseDouble(prixPour1Euro.getText());
                    if (devise.getText().trim().equals(""))
                        reponse = false;
                    else
                        reponse = true;
                } catch(NumberFormatException ne) {
                    reponse = false;
                } finally {
                    setVisible(false);
                }
            }
        });
        
        annuler.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                reponse = false;
                setVisible(false);
            }
        });
        
        add(sud, "South");
        
        pack();
                       
    }
    public boolean isOk() {
        return reponse;
    }
    public String getDevise() {
        return devise.getText();
    }
    public double getPrixPour1Euro() {
        return Double.parseDouble(prixPour1Euro.getText());
    }
    public boolean getSousFormeDeSlider() {
        return choixSlider.isSelected();
    }
}
