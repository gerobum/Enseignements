package numbers;

import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JLabel;

/**
 *
 * @author p0500591
 */
public class JLabelNumber extends JLabel {
    private static NumberFormat nf = NumberFormat.getIntegerInstance();
    private int n;
    public JLabelNumber(int n) {
        super(nf.format(n)+"("+n+")");
        this.n = n;
    }
    
        @Override
    public void setLocale(Locale locale) {  
        super.setLocale(locale);
        nf = NumberFormat.getIntegerInstance(locale);
        setText(nf.format(n)+"("+n+")"+"{"+locale+"}");
    }
}
