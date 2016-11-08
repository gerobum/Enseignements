//??????????????????????????????????????????????????????????????????????????????
package dialogue;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import panneaux.PanneauAffectationTableau;
import panneaux.PanneauAffectationValeur;
import panneaux.PanneauConstructeur;
import panneaux.PanneauConstructeurInstance;

/**
 * Boite de dialogue pour construire une valeur quel que soit son type (valeur
 * de type simple, un tableau ou un objet).
 * 
 * @author yvan
 */
public class DialogueCreationValeurQuelconque extends JDialog {

  PanneauConstructeur pc;

  /**
   * La boite de dialogue est créée à partir du type de la valeur à créer.
   * @param c le type de la valeur à créer, si c'est
   * <ul>
   * <li> un type simple ou un String : la valeur est demandée dans un champ de
   * saisie.</li>
   * <li> un tableau : son nombre d'éléments est demandé dans un champ de saisie.
   * Pour chacun d'eux une autre boite de dialogue de même type est ouverte.</li>
   * <li>une classe : une boite à liste propose ses constructeurs. </li>
   * </ul>
   */
  public DialogueCreationValeurQuelconque(Class<?> c) {
    setLayout(new BorderLayout());
    /* 
     * L'action à mener est différente suivant le type de la valeur à créer : c
     * C'est le type de panneau qui détermine l'action.
     * - Pour un type simple ou un String : pc est un PanneauAffectationValeur.
     * - Pour un tableau : pc est un PanneauAffectationTableau
     * - Dans les autres cas : pc est un PanneauConstructeurInstance
     */
    //??????????????????????????????????????????????????????????????????????????
    if (c.isPrimitive() || "java.lang.String".equals(c.getName())) {
      pc = new PanneauAffectationValeur(this, c);
    } else if (c.isArray()) {
      pc = new PanneauAffectationTableau(this, c.getComponentType());
    } else {
      pc = new PanneauConstructeurInstance(this, c);
    }
    //??????????????????????????????????????????????????????????????????????????
    add(pc);
    /*
     * La boite de dialogue est bloquante.
     */
    setModalityType(ModalityType.APPLICATION_MODAL);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    pack();
    /*
     * Attention, la boite est bloquante à sa création.
     */
    setVisible(true);
  }
 /**
  * Pour retourner la valeur construite.
  * @return la valeur construite.
  */
  public Object getValeur() {
    return pc.getValeur();
  }
}
