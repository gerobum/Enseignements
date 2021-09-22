package langues;

import java.awt.GridLayout;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**
 *
 * @author yvan
 */
public class LanguagePicker extends JDialog {

    private JTextField langue, region, autre;
    private final JLabel JL_LANGUE, JL_REGION, JL_VARIANTE;
    private final Localizable localizable;
    private ResourceBundle bundle = ResourceBundle.getBundle("langues/dico");

    @Override
    public void setLocale(Locale l) {
        super.setLocale(l);
        try {
            JL_LANGUE.setText(bundle.getString("LANGUE"));
            JL_REGION.setText(bundle.getString("REGION"));
            JL_VARIANTE.setText(bundle.getString("AUTRE"));
        } catch (NullPointerException ne) {

        }
    }

    public LanguagePicker(Localizable localizable) {
        this.localizable = localizable;
        JL_LANGUE = new JLabel(bundle.getString("LANGUE"));
        JL_REGION = new JLabel(bundle.getString("REGION"));
        JL_VARIANTE = new JLabel(bundle.getString("AUTRE"));
        init();
    }
    
    private void init() {
        
        getContentPane().setLayout(new GridLayout(2, 3));
        langue = new JTextField(8);
        region = new JTextField(8);
        autre = new JTextField(8);
        getContentPane().add(JL_LANGUE);
        getContentPane().add(JL_REGION);
        getContentPane().add(JL_VARIANTE);
        getContentPane().add(langue);
        getContentPane().add(region);
        getContentPane().add(autre);
        setModal(false);
        CaretListener caret = new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                Locale locale = new Locale(langue.getText(), region.getText(), autre.getText());
                LanguagePicker.this.setLocale(locale);
                LanguagePicker.this.localizable.setLocale(locale);
            }
        };

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        langue.addCaretListener(caret);
        region.addCaretListener(caret);
        autre.addCaretListener(caret);
        pack();
    }
}
