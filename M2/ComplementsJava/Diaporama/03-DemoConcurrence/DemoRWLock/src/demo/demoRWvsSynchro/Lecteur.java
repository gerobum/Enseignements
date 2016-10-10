package demo.demoRWvsSynchro;

import java.awt.Color;
import java.util.Random;
import javax.swing.JLabel;

/**
 *
 * @author maillot
 */
public class Lecteur extends Thread {

    private final JLabel label;
    private final int nb;
    private final PG pg;
    private Color eteint, allume;
    private static final Random RANDOM = new Random();

    public Lecteur(JLabel label, PG pg, int nb, Color eteint, Color allume) {
        this.label = label;
        this.nb = nb;
        this.pg = pg;
        this.eteint = eteint;
        this.allume = allume;
    }

    @Override
    public void run() {
        for (int i = 0; i < nb; i++) {
            try {
                label.setBackground(eteint);
                pg.get();
                label.setBackground(allume);
                Thread.sleep(RANDOM.nextInt(1000));
            } catch (InterruptedException ex) {
            }
        }
    }
}
