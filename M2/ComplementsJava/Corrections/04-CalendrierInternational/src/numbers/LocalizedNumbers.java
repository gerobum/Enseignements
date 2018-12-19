
package numbers;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Locale;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 *
 * @author p0500591
 */
public class LocalizedNumbers extends JPanel {

    private JPanel numbers = new JPanel();
    private JLabelNumber label1 = new JLabelNumber(1);
    private JLabelNumber label2 = new JLabelNumber(2);
    private JLabelNumber label3 = new JLabelNumber(3);
    private JComboBox<Locale> pays = new JComboBox<>(DateFormat.getAvailableLocales());

    public LocalizedNumbers() {
        setLayout(new BorderLayout());
        numbers.add(label1);
        numbers.add(label2);
        numbers.add(label3);
        this.add(numbers, "Center");
        this.add(pays, "South");

        pays.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label1.setLocale((Locale) pays.getSelectedItem());
                label2.setLocale((Locale) pays.getSelectedItem());
                label3.setLocale((Locale) pays.getSelectedItem());
            }
        });
    }
}