package mediateur;

import java.awt.Font;
import javax.swing.*;

/**
 *
 * @author yvan
 */
public class Mediateur {

    private final JLabel fonteExemple;
    private final JComboBox comboBoxFamille;
    private final JRadioButton normal, bold, demibold, roman, italique, oblique;
    private final JSpinner spinnerSize;
    private final JCheckBox condense;

    public Mediateur(JLabel fonteExemple, JComboBox comboBoxFamille, JRadioButton normal, JRadioButton bold, JRadioButton demibold, JRadioButton roman, JRadioButton italique, JRadioButton oblique, JSpinner spinnerSize, JCheckBox condense) {
        this.fonteExemple = fonteExemple;
        this.comboBoxFamille = comboBoxFamille;
        this.normal = normal;
        this.bold = bold;
        this.demibold = demibold;
        this.roman = roman;
        this.italique = italique;
        this.oblique = oblique;
        this.spinnerSize = spinnerSize;
        this.condense = condense;
    }

    public void valueChanged(JComponent composant) {
        if (composant == comboBoxFamille) {
            fromComboxFamille();
        }


    }

    private int getSize() {
        String s = (String) spinnerSize.getValue();
        return Integer.parseInt(s.replaceAll("pt", "").replaceAll(" ", ""));
    }

    private void fromComboxFamille() {
        String s;
        s = (String) comboBoxFamille.getSelectedItem();

        fonteExemple.setFont(new Font(s, Font.PLAIN, getSize()));

    }
}
