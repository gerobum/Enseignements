package panneaux;

import javax.swing.JDialog;
import javax.swing.JPanel;

public class PanneauConstructeur extends JPanel {

  protected Object valeur;
  protected JDialog dialog;
  protected Class<?> type;

  public PanneauConstructeur(JDialog dialog, Class<?> type) {
    this.dialog = dialog;
    this.type = type;
  }
  
  public Object getValeur() {
    return valeur;
  }
}
