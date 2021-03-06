package langues;

import java.awt.GridLayout;
import java.util.Locale;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import pendule.StopWatch;

/**
 *
 * @author yvan
 */
public class ChoixDeLangue extends JDialog {

    private JTextField langue, region, autre;
    private final JLabel JL_LANGUE, JL_REGION, JL_VARIANTE;
    private final StopWatch stopWatch;

    @Override
    public void setLocale(Locale l) {
        super.setLocale(l);
        try {
            JL_LANGUE.setText(java.util.ResourceBundle.getBundle("langues/dico", l).getString("LANGUE"));
            JL_REGION.setText(java.util.ResourceBundle.getBundle("langues/dico", l).getString("REGION"));
            JL_VARIANTE.setText(java.util.ResourceBundle.getBundle("langues/dico", l).getString("AUTRE"));
        } catch (NullPointerException ne) {

        }
    }

    public ChoixDeLangue(StopWatch stopWatch) {
        this.stopWatch = stopWatch;
        JL_LANGUE = new JLabel(java.util.ResourceBundle.getBundle("langues/dico").getString("LANGUE"));
        JL_REGION = new JLabel(java.util.ResourceBundle.getBundle("langues/dico").getString("REGION"));
        JL_VARIANTE = new JLabel(java.util.ResourceBundle.getBundle("langues/dico").getString("AUTRE"));
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
                ChoixDeLangue.this.setLocale(locale);
                ChoixDeLangue.this.stopWatch.setLocale(locale);
            }
        };

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        langue.addCaretListener(caret);
        region.addCaretListener(caret);
        autre.addCaretListener(caret);
        pack();
    }
}
