package conversionDevises.patron;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

/**
 *
 * @author Yvan
 */
public class TextFieldObservateur extends JTextField implements Observateur {
    private final String devise;
    private final double valeurPour1€;
    private Euro sujet;
    
    public TextFieldObservateur(String devise, double valeurPour1€, Euro sujet) {
        super(30);
        this.devise = devise;
        this.valeurPour1€ = valeurPour1€;
        this.sujet = sujet;
        sujet.ajoute(this);
        setFont(new Font("Georgia", Font.BOLD, 25));
        addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                TextFieldObservateur source = (TextFieldObservateur) e.getSource();
                try {
                    double valeur = Double.parseDouble(source.getText());
                    
                    source.setValeur(valeur);
                } catch (NumberFormatException ne) {
                }
            }
        });
        update();
    }
    @Override
    public void setSujet(Sujet sujet) {
        this.sujet = (Euro) sujet;
    }

    @Override
    public void update() {
        setText(sujet.getValeur()*valeurPour1€+"");
    }

    private void setValeur(double valeur) {
        sujet.setValeur(valeur/valeurPour1€);
    }
    
}
