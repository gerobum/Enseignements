package panneaux;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
/**
 * Panneau (JPanel) qui hérite de PanneauConstructeur (cf. doc) 
 * Permet de définir une valeur de type simple ou String. Cette valeur est à  
 * placer dans l'attribut valeur de PanneauConstructeur.
 *
 * @author yvan
 */
public class PanneauAffectationValeur extends PanneauConstructeur {

  private JLabel jlType;
  private JTextField saisie;

  public PanneauAffectationValeur(JDialog d, Class<?> c) {
    super(d, c);
    jlType = new JLabel(c.getName());
    saisie = new JTextField(20);
    setLayout(new FlowLayout());
    add(jlType);
    add(saisie);
    if ("java.lang.String".equals(type.getName())) {
      dialog.setTitle("Entrer une chaine de caractères");
    } else {
      dialog.setTitle("Entrer un " + type.getName());
    }
    saisie.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
        switch (type.getName()) {
          case "int":
            valeur = Integer.parseInt(saisie.getText());
            break;
          case "byte":
            valeur = Byte.parseByte(saisie.getText());
            break;
          case "short":
            valeur = Short.parseShort(saisie.getText());
            break;
          case "long":
            valeur = Long.parseLong(saisie.getText());
            break;
          case "float":
            valeur = Float.parseFloat(saisie.getText());
            break;
          case "double":
            valeur = Double.parseDouble(saisie.getText());
            break;
          case "char":
            valeur = saisie.getText().charAt(0);
            break;
          case "java.lang.String":
            valeur = saisie.getText();
            break;
        }
        dialog.setVisible(false);
        } catch(NumberFormatException nfe) {
          saisie.setText("");
        }
      }
    });
  }
}
