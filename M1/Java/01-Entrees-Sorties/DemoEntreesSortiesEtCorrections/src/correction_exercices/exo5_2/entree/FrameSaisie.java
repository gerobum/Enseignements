package correction_exercices.exo5_2.entree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FrameSaisie extends JFrame {

    private JTextField valeur;
    private final DataOutputStream dout;

    public FrameSaisie(DataOutputStream dout) {
        this.dout = dout;
        generateUI();
        putListeners();

    }

    private void generateUI() {
        valeur = new JTextField(20);
        valeur.setFont(valeur.getFont().deriveFont(30f));
        JLabel texte = new JLabel("Entrez un double", JLabel.CENTER);
        texte.setFont(texte.getFont().deriveFont(30f));

        getContentPane().add(texte, "North");
        getContentPane().add(valeur, "South");
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void putListeners() {

        valeur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    FrameSaisie.this.dout.writeDouble(Double.parseDouble(valeur.getText()));
                } catch (NumberFormatException | IOException ex) {
                } finally {
                    valeur.setText("");
                }
            }
        });
    }
}
