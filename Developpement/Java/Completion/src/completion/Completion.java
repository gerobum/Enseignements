package completion;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.lang.reflect.Method;
import java.util.stream.Stream;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author maillot
 */
public class Completion extends JFrame {

    private JLabel jlNomClasse, jlNomMethode, jlMethodeSel;
    private JTextField jtfNomClasse, jtfNomMethode, jtfMethodeSel;
    private JList<Method> jtaListeMethodes;
    private DefaultListModel<Method> dlm;
    private String nomMethode = "";

    private Class<?> classe;

    public Completion() {
        super("Outil de découverte de classes Java");

        initAttributs();

        initEcouteurs();

        initProprietes();

    }

    private void initAttributs() {
        JPanel nord = new JPanel(new GridLayout(0, 1));

        jlNomClasse = new JLabel("Nom d'une classe", JLabel.LEFT);
        jlNomMethode = new JLabel("Nom d'une méthode", JLabel.LEFT);
        jlMethodeSel = new JLabel("Méthode sélectionnée", JLabel.LEFT);
        jtfNomClasse = new JTextField(80);
        jtfNomMethode = new JTextField(80);
        jtfMethodeSel = new JTextField(80);
        jtfMethodeSel.setEditable(false);
        jtaListeMethodes = new JList<>();
        dlm = new DefaultListModel<>();
        jtaListeMethodes.setModel(dlm);
        jtaListeMethodes.setPreferredSize(new Dimension(300, 300));

        JPanel jpNomClasse = new JPanel();
        JPanel jpNomMethode = new JPanel();
        JPanel jpMethodeSel = new JPanel();

        jpNomClasse.add(jlNomClasse);
        jpNomClasse.add(jtfNomClasse);

        jpNomMethode.add(jlNomMethode);
        jpNomMethode.add(jtfNomMethode);

        jpMethodeSel.add(jlMethodeSel);
        jpMethodeSel.add(jtfMethodeSel);

        nord.add(jpNomClasse);
        nord.add(jpNomMethode);
        getContentPane().add(nord, "North");
        getContentPane().add(new JScrollPane(jtaListeMethodes), "Center");
        getContentPane().add(jpMethodeSel, "South");
    }

    private void initProprietes() {

        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initEcouteurs() {
        jtfNomClasse.addActionListener((ActionEvent e) -> {
            try {
                Class<?> classe = Class.forName(jtfNomClasse.getText());
                if (classe != Completion.this.classe) {
                    Completion.this.classe = classe;
                    //jtfNomClasse.setText(classe.getName());
                    jtfNomMethode.setText("");
                    dlm.clear();
                    for (Method m : classe.getMethods()) {
                        dlm.addElement(m);
                    }
                }
            } catch (ClassNotFoundException ex) {

            }
        });

        jtfNomMethode.addCaretListener((CaretEvent e) -> {
            if (!nomMethode.equals(jtfNomMethode.getText())) {
                nomMethode = jtfNomMethode.getText();
                dlm.clear();
                Stream.of(classe.getMethods())
                        .filter(m -> m.getName().matches(".*" + nomMethode + ".*"))
                        .forEach(m -> {
                            dlm.addElement(m);
                        });
            }
        });

        jtaListeMethodes.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                jtfMethodeSel.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });

        jtaListeMethodes.addListSelectionListener((ListSelectionEvent e) -> {
            jtfMethodeSel.setText(dlm.getElementAt(e.getFirstIndex()).toString());
        });
    }

}
