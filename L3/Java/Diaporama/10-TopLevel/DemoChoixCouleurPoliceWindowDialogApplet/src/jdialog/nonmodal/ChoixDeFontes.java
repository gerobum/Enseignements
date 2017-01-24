package jdialog.nonmodal;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ChoixDeFontes extends JDialog {

    private JPanel sud;
    private JPanel est;
    private final JLabel message;

    private JComboBox listePolices;
    private JCheckBox gras;
    private JCheckBox italique;
    private JTextField taille;

    public ChoixDeFontes(JLabel message) {
        this.message = message;

        miseEnPlaceDesComposants();
        miseEnPlaceDesEcouteurs();
        miseEnPlaceUI();
    }

    private void miseEnPlaceDesComposants() {
        sud = new JPanel();
        est = new JPanel();
        listePolices = new JComboBox(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());

        listePolices.setSelectedItem(message.getFont().getFamily());
        sud.add(listePolices);

        gras = new JCheckBox("Gras", message.getFont().isBold());
        italique = new JCheckBox("Italique", message.getFont().isItalic());
        taille = new JTextField(message.getFont().getSize()+"");

        est.setLayout(new GridLayout(0, 1));
        est.add(gras);
        est.add(italique);
        est.add(new JLabel("Taille", JLabel.CENTER));
        est.add(taille);

        getContentPane().setBackground(Color.WHITE);

        getContentPane().add(est, "Center");
        getContentPane().add(sud, "South");
    }

    private void miseEnPlaceDesEcouteurs() {
        ActionListener action = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String nomFonte = (String) listePolices.getSelectedItem();
                int taille = 20;
                try {
                    taille = Integer.parseInt(ChoixDeFontes.this.taille.getText());
                } catch (NumberFormatException n) {
                    ChoixDeFontes.this.taille.setText("20");
                }
                if (taille < 1) {
                    taille = 1;
                    ChoixDeFontes.this.taille.setText("1");
                } else if (taille > 200) {
                    taille = 200;
                    ChoixDeFontes.this.taille.setText("200");
                }

                int style = Font.PLAIN;
                if (ChoixDeFontes.this.gras.isSelected()) {
                    style = Font.BOLD;
                }
                if (ChoixDeFontes.this.italique.isSelected()) {
                    style = style | Font.ITALIC;
                }

                message.setFont(new Font(nomFonte, style, taille));
            }
        };

        listePolices.addActionListener(action);
        gras.addActionListener(action);
        italique.addActionListener(action);

        taille.addActionListener(action);
    }

    private void miseEnPlaceUI() {
        pack();
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

}
