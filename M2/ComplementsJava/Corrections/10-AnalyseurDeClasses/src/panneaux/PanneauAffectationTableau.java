//??????????????????????????????????????????????????????????????????????????????
package panneaux;

import dialogue.DialogueCreationValeurQuelconque;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Panneau (JPanel) qui hérite de PanneauConstructeur (cf. doc) 
 * Permet de créer un tableau de valeurs quelconques. Ce tableau est à placer 
 * dans l'attribut valeur de PanneauConstructeur
 *
 * @author yvan
 */
public class PanneauAffectationTableau extends PanneauConstructeur {

  private JLabel jlQuantite;
  private JTextField saisie;
  private DialogueCreationValeurQuelconque dialogue;

  /**
   * Le panneau contient un champ de texte pour entrer le nombre voulu
   * d'éléments du tableau.
   *
   * @param d la boite de dialogue associée (pour pouvoir la fermer)
   * @param c le type des éléments du tableau.
   */
  public PanneauAffectationTableau(JDialog d, Class<?> c) {
    super(d, c);
    jlQuantite = new JLabel("Nombre d'éléments");
    saisie = new JTextField(20);
    setLayout(new FlowLayout());
    add(jlQuantite);
    add(saisie);
    dialog.setTitle("Entrer le nombre de valeurs de votre tableau");
    saisie.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          /*????????????????????????????????????????????????????????????????????
           * Une fois le nombre de valeurs donné, un tableau du type demandé
           * est créé (cf. Array.newInstance()). Une valeur est affectée à 
           * chacune de ses cases (cf. Array.set()) à l'aide
           * de la boite de dialogue DialogueCreationValeurQuelconque.
           */
          int n = Integer.parseInt(saisie.getText());
          valeur = Array.newInstance(type, n);
          for (int i = 0; i < n; i++) {
            dialogue = new DialogueCreationValeurQuelconque(type);
            Array.set(valeur, i, dialogue.getValeur());
          }
          //????????????????????????????????????????????????????????????????????
          dialog.setVisible(false);
        } catch (NumberFormatException nfe) {
          saisie.setText("");
        }
      }
    });
  }
}
