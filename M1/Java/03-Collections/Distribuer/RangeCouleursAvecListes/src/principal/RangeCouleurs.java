package principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListDataListener;
import swing.JSliderLabel;

public class RangeCouleurs extends JFrame implements ItemListener {

    private final JComboBox<String> listeDesCouleurs;
    private final JPanel sud, centre, nord;
    private final JSliderLabel rouge;
    private final JSliderLabel vert;
    private final JSliderLabel bleu;
    private final JButton ajouter, supprimer;

    private Map<String, Color> nomCouleur;

    public RangeCouleurs() {
        super("Fenêtre pour ranger les couleurs");

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("couleurs"))) {
            nomCouleur = (TreeMap<String, Color>) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            nomCouleur = new TreeMap<>();
        }

        nomCouleur.put("Blanc", Color.WHITE);
        nomCouleur.put("blanc", Color.WHITE);
        nomCouleur.put("Rouge", Color.RED);
        nomCouleur.put("rouge", Color.RED);

        listeDesCouleurs = new JComboBox<>();
        listeDesCouleurs.addItem("Blanc");
        listeDesCouleurs.addItem("blanc");
        listeDesCouleurs.setEditable(true);

        sud = new JPanel();
        sud.setLayout(new GridLayout(0, 1));

        nord = new JPanel();
        nord.setLayout(new FlowLayout());

        centre = new JPanel();
        centre.setPreferredSize(new Dimension(200, 200));
        centre.setBackground(Color.white);

        rouge = new JSliderLabel(0, 255);
        vert = new JSliderLabel(0, 255);
        bleu = new JSliderLabel(0, 255);

        rouge.setPreferredSize(new Dimension(50, 20));
        vert.setPreferredSize(new Dimension(50, 20));
        bleu.setPreferredSize(new Dimension(50, 20));

        sud.add(rouge);
        sud.add(vert);
        sud.add(bleu);

        getContentPane().setLayout(new BorderLayout());

        ajouter = new JButton("+");
        supprimer = new JButton("-");

        ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listeDesCouleurs.setSelectedIndex(listeDesCouleurs.getSelectedIndex());
            }
        });

        nord.add(listeDesCouleurs);
        nord.add(ajouter);
        nord.add(supprimer);

        getContentPane().add(nord, "North");
        getContentPane().add(centre, "Center");
        getContentPane().add(sud, "South");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();

        /*ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Color c = new Color(Integer.parseInt(rouge.getText()), Integer.parseInt(vert.getText()), Integer.parseInt(bleu.getText()));
                    centre.setBackground(c);
                    Set<String> set = couleurNoms.get(c);
                    nomsDeLaCouleur.removeItemListener(RangeCouleurs.this);
                    nomsDeLaCouleur.removeAllItems();
                    if (set != null) {
                        for (String s : set) {
                            nomsDeLaCouleur.addItem(s);
                        }
                    }
                    nomsDeLaCouleur.addItemListener(RangeCouleurs.this);
                } catch (Exception ex) {
                }
            }
        };
        

        rouge.addActionListener(action);
        vert.addActionListener(action);
        bleu.addActionListener(action);
         */
        ChangeListener change = new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                //System.out.println(e);
                try {
                    Color c = new Color(rouge.getValue(), vert.getValue(), bleu.getValue());
                    centre.setBackground(c);

                    listeDesCouleurs.setSelectedItem(nomDe(c));

                    /*listeDesCouleurs.removeItemListener(RangeCouleurs.this);
                    Set<String> set = new HashSet<>();
                    nomCouleur.entrySet().stream().filter(p -> p.getValue().equals(c)).map(p -> p.getKey()).filter(p -> absentDeLaListe(p)).forEach(p->set.add(p));
                    if (set != null) {
                        for (String s : set) {
                            listeDesCouleurs.addItem(s);
                        }
                    }
                    
                    listeDesCouleurs.addItemListener(RangeCouleurs.this);*/
                } catch (Exception ex) {
                }
            }
        };


        rouge.addChangeListener(change);
        vert.addChangeListener(change);
        bleu.addChangeListener(change);
        rouge.setValue(0);
        vert.setValue(0);
        bleu.setValue(0);

        listeDesCouleurs.addItemListener(this);

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent we) {
                try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("enregistrement_couleurs"))) {
                    out.writeObject(nomCouleur);
                } catch (IOException ex) {
                }
            }
        });

    }

    public static void main(String[] args) {
        new RangeCouleurs();
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        System.out.println(ie);
        listeDesCouleurs.removeItemListener(this);
        if (ie.getStateChange() == (ItemEvent.ITEM_STATE_CHANGED & ItemEvent.SELECTED)) {
            if (ie.getItem() != null) {
                String nom = ((String) (ie.getItem())).trim();
                if (!"".equals(nom)) {
                    // Si l'item fait parti de la liste inutile d'aller plus loin
                    // car cela signifie qu'il n'a rien d'autres à faire que de 
                    // changer l'item

                    Color c = nomCouleur.get(nom);
                    if (c != null) {
                        centre.setBackground(c);
                        rouge.setValue(c.getRed());
                        vert.setValue(c.getGreen());
                        bleu.setValue(c.getBlue());

                    } else {
                        Color couleur = centre.getBackground();
                        nomCouleur.put(nom, couleur);
                        if (absentDeLaListe(nom)) {
                            listeDesCouleurs.addItem(nom);
                        }
                    }
                }
            }
        }
        listeDesCouleurs.addItemListener(this);
    }
    
    
            private boolean absentDeLaListe(String p) {
                for (int i = 0; i < listeDesCouleurs.getItemCount(); i++) {
                    if (listeDesCouleurs.getItemAt(i).equals(p)) {
                        return false;
                    }
                }
                return true;
            }

            private String nomDe(Color c) {
                Optional<String> o = nomCouleur.entrySet().stream().filter(p -> p.getValue().equals(c)).map(p -> p.getKey()).findFirst();
                if (o.isPresent()) {
                    return o.get();
                } else {
                    return null;
                }
            }

    private ComboBoxModel<String> getComboxModel() {
        return new ComboBoxModel<String>() {

            private final ArrayList<String> tableau = new ArrayList<>();

            private Object selected = null;

            @Override
            public void setSelectedItem(Object anItem) {
                selected = anItem;
            }

            @Override
            public Object getSelectedItem() {
                return selected;
            }

            @Override
            public int getSize() {
                return nomCouleur.size();
            }

            @Override
            public String getElementAt(int index) {
                return "";
            }

            @Override
            public void addListDataListener(ListDataListener l) {

            }

            @Override
            public void removeListDataListener(ListDataListener l) {

            }
        };

    }
}
