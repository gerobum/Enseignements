package principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RangeCouleurs extends JFrame {

    private final JTextField nomDeLaCouleur;
    private final JPanel sud, centre;
    private final JTextField rouge;
    private final JTextField vert;
    private final JTextField bleu;
    private final Map<Color, String> couleurNom;
    private final Map<String, Color> nomCouleur;

    /**
     * Création d'un Objet de type RangeCouleurs
     */
    public RangeCouleurs() {
        super("Fenêtre pour ranger les couleurs");

        couleurNom = new HashMap<>();
        couleurNom.put(Color.RED, "Rouge");
        couleurNom.put(Color.BLACK, "Noir");
        couleurNom.put(Color.BLUE, "Bleu");
        couleurNom.put(Color.CYAN, "Bleu Ciel");
        couleurNom.put(Color.DARK_GRAY, "Gris foncé");
        couleurNom.put(Color.GRAY, "Gris");
        couleurNom.put(Color.GREEN, "Vert");
        couleurNom.put(Color.LIGHT_GRAY, "Gris clair");
        couleurNom.put(Color.MAGENTA, "Magenta");
        couleurNom.put(Color.ORANGE, "Orange");
        couleurNom.put(Color.PINK, "Rose");
        couleurNom.put(Color.WHITE, "Blanc");

        nomCouleur = new HashMap<>();
        nomCouleur.put("Rouge", Color.RED);
        nomCouleur.put("Noir", Color.BLACK);
        nomCouleur.put("Bleu", Color.BLUE);
        nomCouleur.put("Bleu Ciel", Color.CYAN);
        nomCouleur.put("Gris foncé", Color.DARK_GRAY);
        nomCouleur.put("Gris", Color.GRAY);
        nomCouleur.put("Vert", Color.GREEN);
        nomCouleur.put("Gris clair", Color.LIGHT_GRAY);
        nomCouleur.put("Magenta", Color.MAGENTA);
        nomCouleur.put("Orange", Color.ORANGE);
        nomCouleur.put("Rose", Color.PINK);
        nomCouleur.put("Blanc", Color.WHITE);

        nomDeLaCouleur = new JTextField("Blanc");

        sud = new JPanel();
        centre = new JPanel();
        centre.setPreferredSize(new Dimension(200, 200));
        centre.setBackground(Color.white);

        rouge = new JTextField("000");
        vert = new JTextField("000");
        bleu = new JTextField("000");
        rouge.setPreferredSize(new Dimension(50, 20));
        vert.setPreferredSize(new Dimension(50, 20));
        bleu.setPreferredSize(new Dimension(50, 20));

        sud.add(rouge);
        sud.add(vert);
        sud.add(bleu);

        generateUI();
        putListeners();

    }

    public static void main(String[] args) {
        new RangeCouleurs();
    }

    private void generateUI() {

        getContentPane().setLayout(new BorderLayout());

        getContentPane().add(nomDeLaCouleur, "North");
        getContentPane().add(centre, "Center");
        getContentPane().add(sud, "South");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    private void putListeners() {

        nomDeLaCouleur.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String couleurEcrite = nomDeLaCouleur.getText().trim();
                if (!couleurEcrite.isEmpty()) {
                    Color couleur = nomCouleur.get(couleurEcrite);
                    if (couleur != null) {
                        centre.setBackground(couleur);
                        rouge.setText("" + couleur.getRed());
                        bleu.setText("" + couleur.getBlue());
                        vert.setText("" + couleur.getGreen());
                    } else {
                        try {
                            couleur = new Color(Integer.parseInt(rouge.getText()),
                                    Integer.parseInt(vert.getText()),
                                    Integer.parseInt(bleu.getText()));
                            couleurNom.put(couleur, couleurEcrite);
                            nomCouleur.put(couleurEcrite, couleur);
                        } catch (Exception ex) {
                        }
                    }
                }
            }
        });

        ActionListener action = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Color couleur = new Color(Integer.parseInt(rouge.getText()),
                        Integer.parseInt(vert.getText()),
                        Integer.parseInt(bleu.getText()));
                centre.setBackground(couleur);
                String nom = couleurNom.get(couleur);
                if (nom == null) {
                    nomDeLaCouleur.setText("");
                } else {
                    nomDeLaCouleur.setText(nom);
                }
            }
        };

        rouge.addActionListener(action);
        vert.addActionListener(action);
        bleu.addActionListener(action);
    }
}
