/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediateur;

import java.util.GregorianCalendar;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author yvan
 */
public class MediateurFomulaire {

    private JFrame frame;
    private JButton boutonOK;
    private JButton boutonAnnuler;
    private ButtonGroup groupeGenre;
    private JRadioButton radioButtonMasculin;
    private JRadioButton radioButtonFeminin;
    private JLabel labelNomDeJeuneFille;
    private JComboBox listCivilite;
    private JTextField textFieldAge;
    private JTextField textFieldDateDeNaissance;
    private JTextField textFieldNom;
    private JTextField textFieldNomDeJeuneFille;
    private JTextField textFieldPrenom;
    private GregorianCalendar gc;

    public MediateurFomulaire(
            JFrame frame,
            JButton boutonAnnuler,
            JButton boutonOK,
            ButtonGroup groupeGenre,
            JRadioButton radioButtonMasculin,
            JRadioButton radioButtonFeminin,
            JLabel labelNomDeJeuneFille,
            JComboBox listCivilite,
            JTextField textFieldAge,
            JTextField textFieldDateDeNaissance,
            JTextField textFieldNom,
            JTextField textFieldNomDeJeuneFille,
            JTextField textFieldPrenom) {
        this.frame = frame;
        this.boutonOK = boutonOK;
        this.boutonAnnuler = boutonAnnuler;
        this.groupeGenre = groupeGenre;
        this.radioButtonMasculin = radioButtonMasculin;
        this.radioButtonFeminin = radioButtonFeminin;

        this.labelNomDeJeuneFille = labelNomDeJeuneFille;

        this.listCivilite = listCivilite;
        this.textFieldAge = textFieldAge;
        this.textFieldDateDeNaissance = textFieldDateDeNaissance;
        this.textFieldNom = textFieldNom;
        this.textFieldNomDeJeuneFille = textFieldNomDeJeuneFille;
        this.textFieldPrenom = textFieldPrenom;
    }

    public void changementComposant(JComponent composant) {
        if (composant == boutonAnnuler) {
            groupeGenre.clearSelection();
            listCivilite.removeAllItems();
            listCivilite.addItem("Mme");
            listCivilite.addItem("Mlle");
            listCivilite.addItem("M.");
            listCivilite.addItem("-");
                listCivilite.setSelectedItem("-");
            textFieldAge.setText("");
            textFieldDateDeNaissance.setText("");
            textFieldNom.setText("");
            textFieldNomDeJeuneFille.setText("");
            textFieldPrenom.setText(null);
            radioButtonFeminin.setEnabled(true);
            radioButtonMasculin.setEnabled(true);
        } else if (composant == boutonOK) {
            frame.dispose();
        } else if (composant == radioButtonMasculin || composant == radioButtonFeminin) {
            if (radioButtonMasculin.isSelected()) {
                listCivilite.removeAllItems();
                listCivilite.addItem("M.");
                listCivilite.addItem("-");
                listCivilite.setSelectedItem("M.");
            } else {
                listCivilite.removeAllItems();
                listCivilite.addItem("Mme");
                listCivilite.addItem("Mlle");
                listCivilite.addItem("-");
                listCivilite.setSelectedItem("-");
                radioButtonFeminin.setSelected(true);
            }
            radioButtonFeminin.setEnabled(false);
            radioButtonMasculin.setEnabled(false);

        } else if (composant == listCivilite) {
            if ("-".equals(listCivilite.getSelectedItem())) {
                groupeGenre.clearSelection();
                listCivilite.removeAllItems();
                listCivilite.addItem("-");
                listCivilite.addItem("Mme");
                listCivilite.addItem("Mlle");
                listCivilite.addItem("M.");
            } else if ("M.".equals(listCivilite.getSelectedItem())) {
                radioButtonMasculin.setSelected(true);
            } else if ("Mme".equals(listCivilite.getSelectedItem())) {
                radioButtonMasculin.setSelected(false);
            } else if ("Mlle".equals(listCivilite.getSelectedItem())) {
                radioButtonMasculin.setSelected(false);
            }

        } else if (composant == textFieldNomDeJeuneFille) {
            if (!("".equals(textFieldNomDeJeuneFille.getText().trim()))) {
                radioButtonMasculin.setSelected(false);
                listCivilite.removeAllItems();
                listCivilite.addItem("Mme");
                listCivilite.addItem("-");
            }
        } else if (composant == textFieldDateDeNaissance) {
            //try {
                //Scanner sc = new Scanner(textFieldDateDeNaissance.getText());
                //sc.useDelimiter("/");
                //int jour = sc.nextInt();
                //int mois = sc.nextInt();
                //int annee = sc.nextInt();
                //gc = new GregorianCalendar(annee, mois, annee);
                //textFieldAge.setText(new GregorianCalendar().get(GregorianCalendar.YEAR) - gc.get(GregorianCalendar.YEAR) + "");
            //} catch (Exception ex) {
                //textFieldDateDeNaissance.setText("");
                
            //}
        }
        labelNomDeJeuneFille.setVisible(!radioButtonMasculin.isSelected() && !("Mlle".equals(listCivilite.getSelectedItem())));
        textFieldNomDeJeuneFille.setVisible(!radioButtonMasculin.isSelected() && !("Mlle".equals(listCivilite.getSelectedItem())));

        boutonOK.setEnabled(
                !"-".equals(listCivilite.getSelectedItem())
                && !"".equals(textFieldNom.getText().trim())
                && !"".equals(textFieldPrenom.getText().trim())
                && (!"Mme".equals(listCivilite.getSelectedItem()) || !"".equals(textFieldNomDeJeuneFille.getText().trim()))
                && !"".equals(textFieldAge.getText().trim())
                && !"".equals(textFieldDateDeNaissance.getText().trim())
        );

    }
}
