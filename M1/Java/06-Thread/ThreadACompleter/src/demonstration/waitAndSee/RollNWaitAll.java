/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demonstration.waitAndSee;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author yvan
 */
public class RollNWaitAll extends JFrame {

    private static final Random RANDOM = new Random();
    private final JLabel n1 = new JLabel("0", JLabel.CENTER);
    private final JLabel n2 = new JLabel("0", JLabel.CENTER);
    private final JLabel n3 = new JLabel("0", JLabel.CENTER);
    private final JCheckBox cb1 = new JCheckBox();
    private final JCheckBox cb2 = new JCheckBox();
    private final JCheckBox cb3 = new JCheckBox();

    public RollNWaitAll() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        super("Bandit manchot");
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
        
        initialisation(centre);
        
        // La méthode roule retourne un Thread qui agit sur ni et cbi
        roule(n1, cb1).start();
        roule(n2, cb2).start();
        roule(n3, cb3).start();

        // L'action réveille tous les Thread en attente sur ce moniteur
        // Essayez de remplacer notifyAll par notify, vous constaterez qu'un
        // clic sur 3 déclenchera le défilement des chiffres.
        // La raison est qu'il y a trois thread en attente sur le même verrou
        // et la notification est envoyé à l'un des trois (peut être pas le bon).
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized(RollNWaitAll.this) {RollNWaitAll.this.notifyAll();}
            }
        };

        cb1.addActionListener(action);
        cb2.addActionListener(action);
        cb3.addActionListener(action);

    }
    
    private void initialisation(JPanel centre) {
        
        getContentPane().add(centre, "Center");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 300));
        setVisible(true);
        pack();
    }

    public final Thread roule(final JLabel label, final JCheckBox cb) {
        return new Thread(new Runnable() {

            @Override
            public void run() {
                while(true) {
                    label.setText(RANDOM.nextInt(10)+"");
                    try {
                        // L'attente se fait dans un "while", car le Thread peut être réveillé à tort.
                        while(!cb.isSelected()) {
                            synchronized(RollNWaitAll.this){ RollNWaitAll.this.wait(); }
                        }
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(RollNWaitAll.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    
    public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        new RollNWaitAll();
    }
}
