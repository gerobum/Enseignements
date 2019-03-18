package exercices.boutons.inline;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;

/**
 * Ce bouton clignotant n'a pas été demandé dans les exercices.
 * 
 * Il clignote à toute vitesse (sans aucune attente) et s'arrête
 * sur un clic.
 * 
 * L'objectif est de montrer un exemple d'interruption sans 
 * exception.
 * 
 * @author yvan
 */
public class BoutonClignotant extends JButton {

    private final static Random R = new Random();
    private Thread thread;

    public BoutonClignotant(String texte) {
        super(texte);
        initUI();
    }

    private void initUI() {
        setBackground(new Color(R.nextInt()));
        thread = new Thread() {
            @Override
            public void run() {
                boolean encore = true;
                while (encore) {
                    setBackground(new Color(R.nextInt()));
                    // Puisque rien n'est suscetible de lancer une interruption
                    if (Thread.interrupted())
                        encore = false;
                }
            }
        };
        thread.start();
        // Une interruption est envoyée au premier clic
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thread.interrupt();
                removeActionListener(this);
            }
        });

        setFont(getFont().deriveFont(50f));
    }
}
