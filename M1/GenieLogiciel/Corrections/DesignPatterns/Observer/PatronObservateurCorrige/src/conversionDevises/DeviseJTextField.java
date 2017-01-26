package conversionDevises;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

/**
 *
 * @author Yvan
 */
public class DeviseJTextField extends JTextField implements Observateur {
    protected String devise;
    protected double valeurPour1Euro;
    protected Euro sujet;
    public DeviseJTextField(String devise, double valeurPour1Euro, Euro sujet) {
        super(30);
        this.devise = devise;
        this.valeurPour1Euro = valeurPour1Euro;
        this.sujet = sujet;
        sujet.ajoute(this);
        setFont(new Font("Georgia", Font.BOLD, 25));
        addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DeviseJTextField source = (DeviseJTextField) e.getSource();
                try {
                    double valeur = Double.parseDouble(source.getText());
                    
                    source.setValeur(valeur);
                } catch (NumberFormatException ne) {
                }
            }
        });
 
    }
    @Override
    public void setSujet(Observé sujet) {
        this.sujet = (Euro) sujet;
    }

    @Override
    public void update() {
        setText(""+sujet.getValeur()*valeurPour1Euro);
    }

    private void setValeur(double valeur) {
        sujet.setValeur(valeur/valeurPour1Euro);
    }
    
}
