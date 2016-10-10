
package demo;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/*
 *
 * @author maillot
 */
public class PanelConsumer extends JPanel {
    private JProgressBar progressBar;
    private static final Random RANDOM = new Random();
    public PanelConsumer(Consumer consumer) {
        
        init(consumer);
    }
    
    private void init(Consumer consumer) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.ipadx = 5;
        gbc.ipady = 5;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(new JLabel(consumer.getConsumerName(), JLabel.RIGHT), gbc);
        
        progressBar = new JProgressBar();
        progressBar.setForeground(new Color(RANDOM.nextInt()));
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 5.0;
        add(progressBar, gbc);
        consumer.setAfficheur(new Afficheur() {

            @Override
            public void afficher(String name, double v) {
                progressBar.setValue((int) (v * 100));
            }
        });
        consumer.start();
    }
}
