package conversionDevisesAvecAPI.patron;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JTextField;

/**
 *
 * @author Yvan
 */
public class TextFieldObservateur extends JTextField implements Observer {
    private final double valeurPour1Euro;
    private double valeur;
    private final Euro sujet;
    public TextFieldObservateur(String devise, double valeurPour1Euro, Euro sujet) {
        super(30);
        this.valeurPour1Euro = valeurPour1Euro;
        this.sujet = sujet;
        sujet.addObserver(this);
        setFont(new Font("Georgia", Font.BOLD, 25));
        addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                TextFieldObservateur source = (TextFieldObservateur) e.getSource();
                try {
                    double v = Double.parseDouble(source.getText());
                    source.setValeur(v);
                } catch (NumberFormatException ne) {
                }
            }
        });
 
    }


    private void setValeur(double valeur) {
        sujet.setValeur(valeur/valeurPour1Euro);
    }

    @Override
    public void update(Observable o, Object arg) {
        valeur = sujet.getValeur()*valeurPour1Euro;
        setText(""+valeur);
    }
    
}
