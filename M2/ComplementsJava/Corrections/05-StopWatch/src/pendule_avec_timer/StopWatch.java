package pendule_avec_timer;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import javax.swing.*;
import java.util.ResourceBundle;
import java.util.TimerTask;
import java.util.Timer;

import static pendule_avec_timer.StopWatch.State.*;

/**
 *
 * @author maillot
 */
public class StopWatch extends JFrame /*implements Runnable*/ {

    public enum State {

        READY, // pret à démarrer
        ACTIVE, // en train de décompter
        BLOQUED     // bloqué
    }

    private final JLabel centre;
    private int heure, minute, seconde;
    private final JButton start, stop, reset;
    private State state;
    //private Thread runner;
    private JCheckBox changerDeLangue;
    private ChoixDeLangue choixDeLangue;
    private Timer timer;
    private TimerTask ttask;

    private void OneSecondMore() {
        seconde++;
        if (seconde == 60) {
            seconde = 0;
            minute++;
            if (minute == 60) {
                minute = 0;
                heure++;
                if (heure == 100) {
                    heure = 0;
                }
            }
        }
    }

    private void print() {
        centre.setText(String.format("%02d:%02d:%02d", heure, minute, seconde)); //NOI18N
    }

    public StopWatch() {
        super(java.util.ResourceBundle.getBundle("langues/dico").getString("WATCH"));
        start = new JButton(ResourceBundle.getBundle("langues/dico").getString("START"));
        stop = new JButton(ResourceBundle.getBundle("langues/dico").getString("STOP"));
        reset = new JButton(ResourceBundle.getBundle("langues/dico").getString("RESET"));

        centre = new JLabel("00:00:00", JLabel.CENTER);

        ttask = new TimerTask() {

            @Override
            public void run() {
                OneSecondMore();
                print();
            }
        };

        init();
    }

    private void init() {
        state = READY;

        heure = 0;
        minute = 0;
        seconde = 0;
        centre.setFont(centre.getFont().deriveFont(40F));

        centre.setBorder(BorderFactory.createEmptyBorder(10, 70, 10, 70));
        JPanel pan = new JPanel();
        pan.setLayout(new GridLayout(1, 3));
        pan.add(start);
        pan.add(stop);
        pan.add(reset);

        getContentPane().add(centre, "Center");
        getContentPane().add(pan, "South");
        getContentPane().setBackground(Color.orange);
        choixDeLangue = new ChoixDeLangue(this);
        changerDeLangue = new JCheckBox(java.util.ResourceBundle.getBundle("langues/dico").getString("CHANGER DE LANGUE"));
        getContentPane().add(changerDeLangue, "North");

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        changerDeLangue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choixDeLangue.setVisible(changerDeLangue.isSelected());
            }
        });

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (state != ACTIVE) {
                    state = ACTIVE;
                    timer = new Timer();

                    ttask = new TimerTask() {

                        @Override
                        public void run() {
                            OneSecondMore();
                            print();
                        }
                    };
                    timer.schedule(ttask, 1000, 1000);
                }
            }
        });

        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (state == ACTIVE) {
                    state = BLOQUED;
                    timer.cancel();
                }
            }
        });

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (state != ACTIVE) {
                    state = READY;
                    heure = minute = seconde = 0;
                    print();
                }

            }
        });
        //runner = new Thread(this);
    }

    @Override
    public void setLocale(Locale locale) {
        super.setLocale(locale);
        try {
            start.setText(ResourceBundle.getBundle("langues/dico", locale).getString("START"));
            reset.setText(ResourceBundle.getBundle("langues/dico", locale).getString("RESET"));
            stop.setText(ResourceBundle.getBundle("langues/dico", locale).getString("STOP"));
            StopWatch.this.setTitle(ResourceBundle.getBundle("langues/dico", locale).getString("WATCH"));
            changerDeLangue.setText(ResourceBundle.getBundle("langues/dico", locale).getString("CHANGER DE LANGUE"));
            choixDeLangue.setLocale(locale);
        } catch (NullPointerException ne) {

        }
    }

    /* public void start() {
     runner.start();
     }*/
    public static void main(String[] args) {
        StopWatch sw = new StopWatch();
    }
}
