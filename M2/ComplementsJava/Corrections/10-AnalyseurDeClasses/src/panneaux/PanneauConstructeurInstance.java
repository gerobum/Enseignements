
package panneaux;

import dialogue.DialogueCreationValeurQuelconque;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import rendu.RenduDeCellulesConstructeurs;

public class PanneauConstructeurInstance extends PanneauConstructeur {

  private JPanel jpOuest;
  private JComboBox<Constructor<?>> jcbConstructeursPublics;
  private ActionListener selectionConstructeur;
  private DialogueCreationValeurQuelconque dcvq;

  public PanneauConstructeurInstance(JDialog d, Class<?> c) {
    super(d, c);
    setLayout(new BorderLayout());
    miseEnPlaceIHM(c);
    miseEnPlaceEcouteur();
  }

  private void miseEnPlaceIHM(Class<?> c) {
    jpOuest = new JPanel();
    JPanel dansOuest = new JPanel(new GridLayout(0, 1));

    jcbConstructeursPublics = new JComboBox<>();
    jcbConstructeursPublics.setRenderer(new RenduDeCellulesConstructeurs());
    dansOuest.add(new JLabel("Constructeurs publiques"));
    dansOuest.add(jcbConstructeursPublics);

    jpOuest.add(dansOuest);
    add(jpOuest, "West");
    if (c.getConstructors().length > 0) {
      for (Constructor<?> k : c.getConstructors()) {
        jcbConstructeursPublics.addItem(k);
      }
      dialog.setTitle("Choisir un constructeur");
    } else {
      dialog.setTitle("Aucun constructeur disponible");
      JButton bouton = new JButton("Aucun constructeur disponible, cliquer pour fermer");
      bouton.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
          valeur = null;
          dialog.setVisible(false);
        }
      });
      dansOuest.add(bouton);
    }
  }

  private void miseEnPlaceEcouteur() {
    selectionConstructeur = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Constructor<?> k = (Constructor<?>) jcbConstructeursPublics.getSelectedItem();
        valeur = utilitaire.UtilitaireDeConstruction.constructionInstance(k);
        dialog.setVisible(false);
      }
    };
    jcbConstructeursPublics.addActionListener(selectionConstructeur);

  }
}
