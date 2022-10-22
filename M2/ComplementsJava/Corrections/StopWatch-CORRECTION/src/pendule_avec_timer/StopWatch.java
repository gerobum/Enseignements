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
import langues.LanguagePicker;
import langues.Localizable;

import static pendule_avec_timer.StopWatch.State.*;

/**
 *
 * @author maillot
 */
public class StopWatch extends JFrame implements Localizable /*Runnable*/ {
    
    private ResourceBundle bundle = ResourceBundle.getBundle("langues/dico");
    private Locale locale = Locale.getDefault();

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
    private LanguagePicker picker;
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
        centre.setText(String.format(locale, "%02d:%02d:%02d", heure, minute, seconde)); 
    }

    public StopWatch() {
        setTitle(bundle.getString("WATCH"));
        start = new JButton(bundle.getString("START"));
        stop = new JButton(bundle.getString("STOP"));
        reset = new JButton(bundle.getString("RESET"));

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
        picker = new LanguagePicker(this);
        changerDeLangue = new JCheckBox(bundle.getString("CHANGER DE LANGUE"));
        getContentPane().add(changerDeLangue, "North");

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        changerDeLangue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                picker.setVisible(changerDeLangue.isSelected());
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
                    timer.schedule(ttask, 1000, 500);
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
            this.locale = locale;
            bundle = ResourceBundle.getBundle("langues/dico", locale);
            start.setText(bundle.getString("START"));
            reset.setText(bundle.getString("RESET"));
            stop.setText(bundle.getString("STOP"));
            StopWatch.this.setTitle(bundle.getString("WATCH"));
            changerDeLangue.setText(bundle.getString("CHANGER DE LANGUE"));
            picker.setLocale(locale);
            print();
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
