package edu.uha.miage.watch;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import edu.uha.miage.languages.LanguagePicker;
import edu.uha.miage.languages.Localizable;
import java.util.Locale;


public class StopWatch extends JFrame implements Runnable, Localizable {    
    
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

    private final JLabel center;
    private int hour, minute, seconde;
    private final JButton start, stop, reset;
    private State state;
    private Thread runner;
    private JCheckBox changeLanguage;
    private LanguagePicker picker;

    private void OneSecondMore() {
        seconde++;
        if (seconde == 60) {
            seconde = 0;
            minute++;
            if (minute == 60) {
                minute = 0;
                hour++;
                if (hour == 100) {
                    hour = 0;
                }
            }
        }
    }

    private void print() {
        center.setText(String.format("%02d:%02d:%02d", hour, minute, seconde)); 
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
            /* #### (2) */state.sync(this);/* #### (2) */
        }
    }

    public StopWatch() {
        setTitle("Watch");
        start = new JButton("Start");
        stop = new JButton("Stop");
        reset = new JButton("Reset");

        center = new JLabel("00:00:00", JLabel.CENTER);

        init();
    }

    private void init() {
        state = State.READY;

        hour = 0;
        minute = 0;
        seconde = 0;
        center.setFont(center.getFont().deriveFont(40F));

        center.setBorder(BorderFactory.createEmptyBorder(10, 70, 10, 70));
        JPanel pan = new JPanel();
        pan.setLayout(new GridLayout(1, 3));
        pan.add(start);
        pan.add(stop);
        pan.add(reset);

        getContentPane().add(center, "Center");
        getContentPane().add(pan, "South");
        getContentPane().setBackground(Color.orange);
        picker = new LanguagePicker(this);
        changeLanguage = new JCheckBox("Change language");
        getContentPane().add(changeLanguage, "North");

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        changeLanguage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                picker.setVisible(changeLanguage.isSelected());
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
                    hour = minute = seconde = 0;
                    print();
                    runner.interrupt();
                }

            }
        });
        runner = new Thread(this);
    }

    public void start() {
        runner.start();
    }

    public static void main(String[] args) {
        StopWatch sw = new StopWatch();
        Locale.getDefault();
        sw.start();
    }
}
