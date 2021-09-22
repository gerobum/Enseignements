package edu.uha.miage.watch.with.timer;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import javax.swing.*;
import java.util.ResourceBundle;
import java.util.TimerTask;
import java.util.Timer;
import edu.uha.miage.languages.LanguagePicker;
import edu.uha.miage.languages.Localizable;

import static edu.uha.miage.watch.with.timer.StopWatch.State.*;

/**
 *
 * @author maillot
 */
public class StopWatch extends JFrame implements Localizable/*Runnable*/ {

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
        centre.setText(String.format("%02d:%02d:%02d", heure, minute, seconde)); 
    }

    public StopWatch() {
        setTitle("Watch");
        start = new JButton("Start");
        stop = new JButton("Stop");
        reset = new JButton("Reset");

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
        changerDeLangue = new JCheckBox("Change language");
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
    }

    /* public void start() {
     runner.start();
     }*/
    public static void main(String[] args) {
        StopWatch sw = new StopWatch();
    }
}
