package edu.uha.miage.languages;

import java.awt.GridLayout;
import java.util.Locale;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**
 *
 * @author Yvan Maillot (yvan.maillot@uha.fr)
 */
public class LanguagePicker extends JDialog {

    private JTextField langue, region, variant;
    private final JLabel JL_LANGUE, JL_REGION, JL_VARIANTE;
    private final Localizable localizable;


    public LanguagePicker(Localizable localizable) {
        this.localizable = localizable;
        JL_LANGUE = new JLabel("Language");
        JL_REGION = new JLabel("Region");
        JL_VARIANTE = new JLabel("Variant");
        init();
    }
    
    private void init() {
        
        getContentPane().setLayout(new GridLayout(2, 3));
        langue = new JTextField(8);
        region = new JTextField(8);
        variant = new JTextField(8);
        getContentPane().add(JL_LANGUE);
        getContentPane().add(JL_REGION);
        getContentPane().add(JL_VARIANTE);
        getContentPane().add(langue);
        getContentPane().add(region);
        getContentPane().add(variant);
        setModal(false);
        CaretListener caret = new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                Locale locale = new Locale(langue.getText(), region.getText(), variant.getText());
                LanguagePicker.this.setLocale(locale);
                LanguagePicker.this.localizable.setLocale(locale);
            }
        };

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        langue.addCaretListener(caret);
        region.addCaretListener(caret);
        variant.addCaretListener(caret);
        pack();
    }
}
