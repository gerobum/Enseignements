package exercices.banditManchots;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Bandit extends JFrame {

    private static final Random R = new Random();
    private JLabel n1 = new JLabel("0", JLabel.CENTER);
    private JLabel n2 = new JLabel("0", JLabel.CENTER);
    private JLabel n3 = new JLabel("0", JLabel.CENTER);
    private JButton lance = new JButton(new ImageIcon(Bandit.class.getResource("poignee.png")));

    public Bandit() {
        super("Bandit manchot");
        initUI();

        lance.setPressedIcon(new ImageIcon(Bandit.class.getResource("poigneeBaissee.png")));
        lance.setDisabledIcon(new ImageIcon(Bandit.class.getResource("poigneeBaissee.png")));

        // L'erreur qui est souvent faite est celle l� :
        // On se doute qu'il faut utiliser join() pour attendre
        // la fin des trois threads pour relever le levier.
        /*lance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Les trois threads sont lanc�es quand baisse le levier
                    // C'est-�-dire quand cet �v�nement est re�u
                    final Thread t1, t2, t3;
                    t1 = roule(n1);
                    t2 = roule(n2);
                    t3 = roule(n3);
                    t1.start();
                    t2.start();
                    t3.start();
                    // Il reste � attendre que �a se termine.
                    t1.join();
                    t2.join();
                    t3.join();
                    // Mais �a ne fonctionne pas bien car les
                    // chiffres semblent ne pas tourner.
                    // C'est normal. On est dans un listener ici.
                    // Ca doit �tre une action tr�s courte. Pendant le
                    // temps de son ex�cution, Swing ne rafraichit rien.
                    // Il faut donc bien lancer les threads mais attendre 
                    // leur terminaison dans un autre thread pour sortir 
                    // imm�diatement du listener.
                    // Le 4eme thread rend inactif le levier tant que les 
                    // chiffres tournent pour le remettre actif juste apr�s.
                    
                    // C'est ce que fait le listener comment� suivant.
                    // Pour que �a fonctionne, commentez ce listener et 
                    // d�commentez le suivant.
                } catch (InterruptedException ex) {
                }

            }
        });*/

        lance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /* L'action lanc�e par "l'abaissement du levier". */
                final Thread t1, t2, t3;
                
                lance.setEnabled(false);// Le levier doit rester baiss�
                
                t1 = roule(n1); // R�cup�ration de trois threads qui feront "tourner"
                t2 = roule(n2); // les "roues"
                t3 = roule(n3);
                t1.start(); // Le lancement des trois threads
                t2.start(); // pour faire "tourner les roues".
                t3.start();

                // Et enfin, un quatri�me thread pour relever le levier au bout moment.
                // C'est-�-dire quand la derni�re roue � tourner s'arr�te.
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            // L'outil de synchronisation join() est bien utile pour faire �a.
                            t1.join();
                            t2.join();
                            t3.join();
                            
                            // Le levier redevient utilisable
                            lance.setEnabled(true);
                            
                        } catch (InterruptedException ex) {
                        }
                    }
                }.start();
            }
        });
    }

    /**
     * M�thode qui retourne un thread dont le but est de faire "tourner" le
     * JLabel pass� en param�tre.
     *
     * @param label le JLabel a faire tourner.
     * @return Le thread lanc�.
     */
    public Thread roule(final JLabel label) {
        return new Thread() {
            @Override
            public void run() {
                // Un thread qui change le chiffre du JLabel pass� en param�tre
                // pour simuler une roue avec des chiffres qui tournerait.

                // Un nombre est choisi au hasard dans [20, 50]
                int nb = R.nextInt(30) + 21;

                // Le chiffre du label est chang� 100nb fois toutes les millisecondes
                // Ca dure entre 2 et 5 secondes.
                // Ca tombe l'impresion que la roue tourne tr�s vite.
                for (int i = 0; i < nb * 100; i++) {
                    label.setText((label.getText().charAt(0) + 1) % 10 + "");
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                    }
                }

                // Le chiffre du label est chang� 10nb fois toutes les dix millisecondes
                // Ca dure entre 2 et 5 secondes
                // Pour donner l'impression que la roue ralentit.
                for (int i = 0; i < nb * 10; i++) {
                    label.setText((label.getText().charAt(0) + 1) % 10 + "");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                    }
                }

                // Le chiffre du label est chang� nb fois toutes les cent millisecondes
                // Ca dure entre 2 et 5 secondes 
                for (int i = 0; i < nb; i++) {
                    label.setText((label.getText().charAt(0) + 1) % 10 + "");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                    }
                }
                // Une fois ceci termin� (La dur�e totale est entre 6 et 15 secondes)
                // le thread se termine aussi. Il "meurt". Il ne pourra plus �tre 
                // possible de le relancer.
            }
        };
    }

    private void initUI() {

        JPanel centre = new JPanel(new GridLayout(3, 0, 50, 50));
        centre.setBackground(new Color(200, 40, 0));
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
        centre.add(new JLabel(" "));
        centre.add(new JLabel(" "));
        centre.add(new JLabel(" "));
        getContentPane().add(centre, "Center");
        getContentPane().add(lance, "East");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(350, 250));
        setVisible(true);
        pack();
    }

    public static void main(String[] args) {
        new Bandit();

    }
}
