package exercices.boutons.inline;

import java.awt.Color;
import java.util.Random;
import javax.swing.JButton;

public class BoutonClignotant extends JButton {

    private final static Random R = new Random();

    public BoutonClignotant(String texte) {
        super(texte);
        initUI();
    }

    private void initUI() {
        setBackground(new Color(R.nextInt()));
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        setBackground(new Color(R.nextInt()));
                        Thread.sleep(70);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        }.start();

        setFont(getFont().deriveFont(50f));
    }
}
