
package demo;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.LinkedBlockingDeque;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 *
 * @author maillot
 */
public class FrameConsumer extends JFrame {
    private final JPanel CENTRE;
    private final JPanel SUD;
    private final JButton AJOUTER = new JButton("Ajouter");
    private final LinkedBlockingDeque<Double> QUEUE;

    public FrameConsumer(LinkedBlockingDeque<Double> q) {
        CENTRE = new JPanel(new GridLayout(0, 1));
        SUD = new JPanel();
        QUEUE = new LinkedBlockingDeque<>();
        init();
    }
    
    private void init() {

        
        getContentPane().add(CENTRE, "Center");
        SUD.add(AJOUTER);
        AJOUTER.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Consumer consumer = new Consumer(QUEUE, "Consommateur", 0, null);
                CENTRE.add(new PanelConsumer(consumer));
                FrameConsumer.this.pack();
            }
        });
        getContentPane().add(SUD, "South");
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        
    }
    
    
    public static void main(String[] args) {
        LinkedBlockingDeque<Double> queue = new LinkedBlockingDeque<>(2);
        Producer p = new Producer(queue, "Producteur", 100, -1, false);
        
        new FrameConsumer(queue);
        p.start();
    }

}
