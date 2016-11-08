//??????????????????????????????????????????????????????????????????????????????
package panneaux;

import analyseur.FrameAnalyseurDeClasses;
import dialogue.DialogueCreationValeurQuelconque;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import rendu.RenduDeCellulesAttributs;
import rendu.RenduDeCellulesConstructeurs;
import rendu.RenduDeCellulesMethodes;
import utilitaire.UtilitaireDeConstruction;

public class PanneauAnalyseurDeClasses extends JPanel {

  private JTextField jtfNomClasse;
  private JPanel jpOuest;
  private JComboBox<Constructor<?>> jcbConstructeursPublics;
  private JComboBox<Method> jcbMethodesPubliques;
  private JComboBox<Method> jcbMethodesNonPubliques;
  private JComboBox<Field> jcbAttributsPublics;
  private Object valeur;
  private Class<?> type;
  private FrameAnalyseurDeClasses parent;
  private JLabel jlInstance;
  private JLabel jlMethode;
  private ActionListener selectionConstructeur;
  private ActionListener selectionMethode;
  private ActionListener selectionAttribut;
  private JPanel conteneurInstance = new JPanel();
  private JPanel conteneurResultat = new JPanel();

  public PanneauAnalyseurDeClasses(FrameAnalyseurDeClasses parent) {
    this.parent = parent;
    setLayout(new BorderLayout());
    jtfNomClasse = new JTextField();
    miseEnPlaceIHM();
    miseEnPlaceEcouteur();
  }

  private void miseEnPlaceIHM() {
    add(jtfNomClasse, "South");
    jpOuest = new JPanel();
    JPanel dansOuest = new JPanel(new GridLayout(0, 1));
    jcbConstructeursPublics = new JComboBox<>();
    jcbConstructeursPublics.setRenderer(new RenduDeCellulesConstructeurs());
    jcbMethodesPubliques = new JComboBox<>();
    jcbMethodesPubliques.setRenderer(new RenduDeCellulesMethodes());
    
    jcbAttributsPublics = new JComboBox<>();
    jcbAttributsPublics.setRenderer(new RenduDeCellulesAttributs());
    
    jcbMethodesNonPubliques = new JComboBox<>();
    jcbMethodesNonPubliques.setRenderer(new RenduDeCellulesMethodes());
    dansOuest.add(new JLabel("Constructeurs publiques"));
    dansOuest.add(jcbConstructeursPublics);
    dansOuest.add(new JLabel("Attributs publiques"));
    dansOuest.add(jcbAttributsPublics);
    dansOuest.add(new JLabel("Méthodes publiques"));
    dansOuest.add(jcbMethodesPubliques);
    dansOuest.add(new JLabel("Méthodes non publiques"));
    dansOuest.add(jcbMethodesNonPubliques);

    jpOuest.add(dansOuest);
    add(jpOuest, "West");

    JPanel centreHaut = new JPanel(new BorderLayout());
    JPanel centreBas = new JPanel(new BorderLayout());
    centreHaut.setBorder(BorderFactory.createEtchedBorder());
    centreBas.setBorder(BorderFactory.createEtchedBorder());
    centreHaut.add(new JLabel("L'objet instancié", JLabel.CENTER), "North");

    jlInstance = new JLabel("", JLabel.CENTER);
    jlMethode = new JLabel("", JLabel.CENTER);
    conteneurInstance.add(jlInstance);
    centreHaut.add(conteneurInstance, "Center");
    centreBas.add(new JLabel("Le résultat de la méthode", JLabel.CENTER), "North");
    conteneurResultat.add(jlMethode);
    centreBas.add(conteneurResultat, "Center");

    JPanel centre = new JPanel(new GridLayout(0, 1));
    centre.add(centreHaut);
    centre.add(centreBas);
    centre.setPreferredSize(new Dimension(400, 200));
    add(centre, "Center");
    centre.setBorder(BorderFactory.createEtchedBorder());
  }

  private void miseEnPlaceEcouteur() {
    jtfNomClasse.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Cette est lancée quand le nom d'une classe a été choisie.
    
          type = UtilitaireDeConstruction.primitive.get(jtfNomClasse.getText());
          if (type == null) { 
            //??????????????????????????????????????????????????????????????????
            // type devient égal au "type classe" dont le nom est donné dans le
            // champ de texte jtfNomClasse     
            // En cas d'erreur, le champ change de couleur
            //??????????????????????????????????????????????????????????????????
            remplirListes();
          } else {
            DialogueCreationValeurQuelconque dcvf = new DialogueCreationValeurQuelconque(type);
            Object o = dcvf.getValeur();
            changementInstance(o);
          }
          parent.pack();

      }
    });

    jtfNomClasse.addCaretListener(new CaretListener() {
      @Override
      public void caretUpdate(CaretEvent e) {
        miseAZero();
      }
    });

    selectionConstructeur = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Constructor<?> k = (Constructor<?>) jcbConstructeursPublics.getSelectedItem();
        valeur = UtilitaireDeConstruction.constructionInstance(k);
        changementInstance(valeur);
      }
    };

    selectionMethode = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JComboBox<?> combo = (JComboBox<?>) e.getSource();
        Method m = (Method) combo.getSelectedItem();
        m.setAccessible(true);
        jlMethode.setText(UtilitaireDeConstruction.invocation(valeur, m) + "");
      }
    };

    selectionAttribut = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JComboBox<?> combo = (JComboBox<?>) e.getSource();
        Field f = (Field) combo.getSelectedItem();
        f.setAccessible(true);
        try {
          jlMethode.setText(f.get(valeur) + "");
        } catch (IllegalArgumentException | IllegalAccessException ex) {
          Logger.getLogger(PanneauAnalyseurDeClasses.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    };

  }

  private void remplirListes() {
    // Les constructeurs
    jcbConstructeursPublics.removeActionListener(selectionConstructeur);
    //??????????????????????????????????????????????????????????????????????????
    // Remplir la liste jcbConstructeursPublics des constructeurs publics
    //??????????????????????????????????????????????????????????????????????????
    jcbConstructeursPublics.addActionListener(selectionConstructeur);
    // Les attributs publics
    jcbAttributsPublics.removeActionListener(selectionAttribut);
    //??????????????????????????????????????????????????????????????????????????
    // Remplir la liste jcbAttributsPublics des attributs publics
    //??????????????????????????????????????????????????????????????????????????
    jcbAttributsPublics.addActionListener(selectionAttribut);
    // Les méthodes
    jcbMethodesPubliques.removeActionListener(selectionMethode);
    //??????????????????????????????????????????????????????????????????????????
    // Remplir la liste jcbMethodesPubliques des méthodes publiques
    //??????????????????????????????????????????????????????????????????????????
    jcbMethodesPubliques.addActionListener(selectionMethode);
    // Les méthodes non publiques
    jcbMethodesNonPubliques.removeActionListener(selectionMethode);
    //??????????????????????????????????????????????????????????????????????????
    // Remplir la liste jcbMethodesNonPubliques des méthodes déclarées dans la
    // classe même et qui ne sont pas publiques.
    //??????????????????????????????????????????????????????????????????????????
    jcbMethodesNonPubliques.addActionListener(selectionMethode);
  }

  private void miseAZero() {
    jcbConstructeursPublics.removeActionListener(selectionConstructeur);
    jcbMethodesPubliques.removeActionListener(selectionMethode);
    jcbMethodesNonPubliques.removeActionListener(selectionMethode);
    jcbAttributsPublics.removeActionListener(selectionAttribut);

    jtfNomClasse.setBackground(Color.white);
    jtfNomClasse.setForeground(Color.black);
    type = null;
    conteneurInstance.removeAll();
    jlInstance.setText("");
    jlMethode.setText("");
    conteneurInstance.add(jlInstance);
    jlInstance.revalidate();

    jcbConstructeursPublics.removeAllItems();
    jcbMethodesPubliques.removeAllItems();
    jcbMethodesNonPubliques.removeAllItems();
    jcbAttributsPublics.removeAllItems();
  }

  private void changementInstance(Object o) {
    if (o instanceof Component) {
      conteneurInstance.removeAll();
      conteneurInstance.add((Component) o);
      ((Component) o).revalidate();
    } else {
      conteneurInstance.removeAll();
      conteneurInstance.add(jlInstance);
      jlInstance.setText(o + "");
    }
  }
}
