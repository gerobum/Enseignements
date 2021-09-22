/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package double_vision.dialogue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**
 *
 * @author maillot
 */
public class CreerUnePersonne extends JDialog {
    
    private static final String modeleNom = "[A-Z][a-z����������]+";

    private JTextField tnom, tage;
    private JButton boutonOk, boutonCancel;
    private boolean ok;
    private String nom;
    private int age;
    private Color couleurFond, couleurTexte;

    public int getAge() {
        return age;
    }

    public String getNom() {
        return nom;
    }

    public boolean isOk() {
        return ok;
    }

    public CreerUnePersonne() {
        setModal(true);
        tnom = new JTextField(30);
        tage = new JTextField(3);
        boutonOk = new JButton("Ok");
        boutonCancel = new JButton("Cancel");
        setLayout(new BorderLayout());
        JPanel pnom = new JPanel();
        JPanel page = new JPanel();
        pnom.add(new JLabel("Nom : "));
        pnom.add(tnom);

        page.add(new JLabel("Age : "));
        page.add(tage);

        couleurTexte = tnom.getForeground();
        couleurFond = tnom.getBackground();

        boutonCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                ok = false;
                tnom.setText("");
                tage.setText("");
                setVisible(false);
            }
        });


        boutonOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (tnom.getText().matches(modeleNom)) {

                    try {
                        age = Integer.parseInt(tage.getText().trim());
                        tage.setForeground(Color.black);
                        tnom.setForeground(Color.black);
                        nom = tnom.getText();
                        tage.setText("");
                        tnom.setText("");
                        setVisible(false);
                        ok = true;
                        tnom.setForeground(couleurTexte);
                        tnom.setBackground(couleurFond);
                        tage.setForeground(couleurTexte);
                        tage.setBackground(couleurFond);
                    } catch (Throwable e) {
                        tage.setBackground(Color.yellow);
                    }
                } else {
                    tnom.setBackground(Color.yellow);
                }
            }
        });

        tnom.addCaretListener(new CaretListener() {

            @Override
            public void caretUpdate(CaretEvent ce) {
                if (tnom.getText().matches(modeleNom)) {
                    tnom.setForeground(couleurTexte);
                    tnom.setBackground(couleurFond);
                } else {
                    tnom.setBackground(Color.yellow);
                }
            }
        });

        tage.addCaretListener(new CaretListener() {

            @Override
            public void caretUpdate(CaretEvent ce) {
                try {
                    Integer.parseInt(tage.getText());
                    tage.setForeground(couleurTexte);
                    tage.setBackground(couleurFond);
                } catch(NumberFormatException ex) {
                    tage.setBackground(Color.yellow);
                }
            }
        });

        JPanel centre = new JPanel(new GridLayout(2, 1));
        JPanel sud = new JPanel();
        centre.add(pnom);
        centre.add(page);
        sud.add(boutonOk);
        sud.add(boutonCancel);
        add(centre, "Center");
        add(sud, "South");
        pack();
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }
}
