/*
 Pour illustrer wait et notify
 */
package demonstration._04_wait;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author yvan
 */
public class RollNWait extends JFrame {

    private static final Random RANDOM = new Random();
    private final JLabel L = new JLabel("0", JLabel.CENTER);

    private final JCheckBox CB = new JCheckBox();

    public RollNWait() {
        super("Rock'n Roll");
        initUI();
/*
        // Commenter le code entre (run sans bloquage) et
        // décommenter le code entre (run avec bloquage) pour bloquer la "roue"
        // en cochant la case.
        // ### (run sans bloquage)
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    L.setText(RANDOM.nextInt(10) + "");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        }.start();
        // ### (run sans bloquage)
        */
        
        // ### (run avec bloquage)
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    L.setText(RANDOM.nextInt(10) + "");
                    try {
                        while (CB.isSelected()) {
                            synchronized (RollNWait.this) {
                                RollNWait.this.wait();
                            }
                        }
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        }.start();
        // ### (run avec bloquage)
        
    }

    private void initUI() {
        JPanel centre = new JPanel(new GridLayout(3, 0, 50, 50));
        centre.setBackground(new Color(200, 50, 0));
        Font fonte = L.getFont().deriveFont(30.0f);
        L.setFont(fonte);
        L.setOpaque(true);
        L.setBackground(Color.white);

        centre.add(new JLabel(" "));
        centre.add(L);

        centre.add(CB);
        getContentPane().add(centre, "Center");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(100, 300));
        setVisible(true);
        pack();
        
        
        
        // Pour bloquer la "roue" quand la case à cocher est cochée, décommenter
        // ce morceau de code :

        CB.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (!CB.isSelected()) {
                    synchronized (RollNWait.this) {
                        RollNWait.this.notify();
                    }
                }
            }
        }
        );
        

        
    }

    public static void main(String[] args) {
        new RollNWait();
    }

}
