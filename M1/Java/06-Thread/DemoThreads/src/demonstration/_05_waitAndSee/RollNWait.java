/*
 Pour expliquer la différence en notify et notifyAll

Ici notify suffit car les threads sont bloqués sur des "moniteurs" différents.
 */
package demonstration._05_waitAndSee;

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
    private final JLabel n1 = new JLabel("0", JLabel.CENTER);
    private final JLabel n2 = new JLabel("0", JLabel.CENTER);
    private final JLabel n3 = new JLabel("0", JLabel.CENTER);
    private final JCheckBox cb1 = new JCheckBox();
    private final JCheckBox cb2 = new JCheckBox();
    private final JCheckBox cb3 = new JCheckBox();


    public RollNWait() {
        super("Rock'N Roll");
        initUI();

        // La méthode roule retourne un Thread qui agit sur ni et cbi
        roule(n1, cb1).start();
        roule(n2, cb2).start();
        roule(n3, cb3).start();


        cb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized(n1) {n1.notify();}
            }
        });
        cb2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized(n2) {n2.notify();}
            }
        });
        cb3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized(n3) {n3.notify();}
            }
        });

    }

    public static Thread roule(final JLabel label, final JCheckBox cb) {
        return new Thread(new Runnable() {

            @Override
            public void run() {
                while(true) {
                    label.setText(RANDOM.nextInt(10)+"");
                    try {
                        // L'attente se fait dans un "while", car le Thread peut être réveillé à tort.
                        while(!cb.isSelected()) {
                            synchronized(label){ label.wait(); }
                        }
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {}
                }
            }
        });
    }

    private void initUI() {
        
        JPanel centre = new JPanel(new GridLayout(3, 0, 50, 50));
        centre.setBackground(new Color(200, 50, 0));
        Font fonte = n1.getFont().deriveFont(30.0f);
        n1.setFont(fonte);
        n1.setOpaque(true);
        n1.setBackground(Color.white);
        n2.setFont(fonte);
        n2.setOpaque(true);
        n2.setBackground(Color.white);
        n3.setFont(fonte);
        n3.setOpaque(true);
        n3.setBackground(Color.white);
        centre.add(new JLabel(" "));
        centre.add(new JLabel(" "));
        centre.add(new JLabel(" "));
        centre.add(n1);
        centre.add(n2);
        centre.add(n3);
        centre.add(cb1);
        centre.add(cb2);
        centre.add(cb3);
        getContentPane().add(centre, "Center");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 300));
        setVisible(true);
        pack();
    }
    
    public static void main(String[] args) {
        new RollNWait();
    }
}
