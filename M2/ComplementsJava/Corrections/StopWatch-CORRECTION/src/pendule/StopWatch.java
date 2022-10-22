package pendule;

import langues.LanguagePicker;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import javax.swing.*;
import java.util.ResourceBundle;
import langues.Localizable;

/**
 *
 * @author maillot
 */
public class StopWatch extends JFrame implements Runnable, Localizable {
    private ResourceBundle bundle = ResourceBundle.getBundle("langues/dico");
    private Locale locale = Locale.getDefault();

    private enum State {

        READY, // pret à démarrer
        ACTIVE {
            @Override
            void sync(StopWatch monitor) {
                try {
                    Thread.sleep(1000);
                    monitor.OneSecondMore();
                    monitor.print();
                } catch (InterruptedException ex) {
                }
            }
        }, // en train de décompter
        BLOQUED;     // bloqué

        void sync(StopWatch monitor) {
            synchronized (monitor) {
                try {
                    monitor.wait();
                } catch (InterruptedException ex) {
                }
            }
        }
    }

    private final JLabel centre;
    private int heure, minute, seconde;
    private final JButton start, stop, reset;
    private State state;
    private Thread runner;
    private JCheckBox changerDeLangue;
    private LanguagePicker choixDeLangue;

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
        centre.setText(String.format(locale, "%02d:%02d:%02d", heure, minute, seconde)); //NOI18N
    }

    @Override
    public void run() {
        while (true) {
            /* #### (1) switch (state) {
                case READY:
                case BLOQUED:
                    synchronized (this) {
                        try {
                            wait();
                        } catch (InterruptedException ex) {
                        }
                    }
                    break;

                case ACTIVE:
                    try {
                        Thread.sleep(1000);
                        OneSecondMore();
                        print();
                    } catch (InterruptedException ex) {
                    }

            } #### (1)*/
            /* #### (2) */ state.sync(this); /* #### (2) */
        }
    }

    public StopWatch() {
        setTitle(bundle.getString("WATCH"));
        start = new JButton(bundle.getString("START"));
        stop = new JButton(bundle.getString("STOP"));
        reset = new JButton(bundle.getString("RESET"));

        centre = new JLabel("00:00:00", JLabel.CENTER);

        init();
    }

    private void init() {
        state = State.READY;

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
        choixDeLangue = new LanguagePicker(this);
        changerDeLangue = new JCheckBox(bundle.getString("CHANGER DE LANGUE"));
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
                state = State.ACTIVE;
                runner.interrupt(); // possible aussi
                /*synchronized (StopWatch.this) {
                    StopWatch.this.notify();
                }*/
            }
        });

        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (state == State.ACTIVE) {
                    state = State.BLOQUED;
                }
                // Pourquoi une interruption ici ?
                runner.interrupt();
                // Sans ça, l'appui sur STOP n'est pas immédiat
                // L'attente (sleep) doit se terminer.
                // L'interruption interrompt cette attente.
            }
        });

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (state != State.ACTIVE) {
                    state = State.READY;
                    heure = minute = seconde = 0;
                    print();
                    runner.interrupt();
                }

            }
        });
        runner = new Thread(this);
    }

    @Override
    public void setLocale(Locale locale) {
        super.setLocale(locale);
        try {
            this.locale = locale;
            bundle = ResourceBundle.getBundle("langues/dico", locale);
            start.setText(bundle.getString("START"));
            reset.setText(bundle.getString("RESET"));
            stop.setText(bundle.getString("STOP"));
            StopWatch.this.setTitle(bundle.getString("WATCH"));
            changerDeLangue.setText(bundle.getString("CHANGER DE LANGUE"));
            choixDeLangue.setLocale(locale);
            print();
        } catch (NullPointerException ne) {

        }
    }

    public void start() {
        runner.start();
    }

    public static void main(String[] args) {
        StopWatch sw = new StopWatch();
        sw.start();
    }
}
