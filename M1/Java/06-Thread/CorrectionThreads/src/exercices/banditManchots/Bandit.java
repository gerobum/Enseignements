package exercices.banditManchots;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Bandit extends JFrame {

  private static Random random = new Random();
  private JLabel n1 = new JLabel("0", JLabel.CENTER);
  private JLabel n2 = new JLabel("0", JLabel.CENTER);
  private JLabel n3 = new JLabel("0", JLabel.CENTER);
  private JButton lance = new JButton(new ImageIcon(Bandit.class.getResource("poignee.png")));

  public Bandit() {
    super("Bandit manchot");
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

    lance.setPressedIcon(new ImageIcon(Bandit.class.getResource("poigneeBaissee.png")));
    lance.setDisabledIcon(new ImageIcon(Bandit.class.getResource("poigneeBaissee.png")));
    lance.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        /*
         * L'action lancée par "l'abaissement du levier".
         * 
         */
        final Thread t1, t2, t3;
        lance.setEnabled(false);// Le levier doit rester baissé
        t1 = roule(n1); // Récupération de trois threads qui feront "tourner"
        t2 = roule(n2); // les "roues"
        t3 = roule(n3);
        t1.start(); // Le lancement des trois threads
        t2.start(); // pour faire "tourner les roues".
        t3.start();

        // Et enfin, un quatrième thread pour relever le levier au bout moment.
        // C'est-à-dire quand la dernière roue à tourner s'arrête.
        new Thread(new Runnable() {
          @Override
          public void run() {
            try {
              // L'outil de synchronisation join() est bien utile pour faire ça.
              t1.join();
              t2.join();
              t3.join();
              // Le levier 
              lance.setEnabled(true);
            } catch (InterruptedException ex) {
            }
          }
        }).start();
      }
    });
  }

  /**
   * Méthode qui retourne un thread dont le but est de faire "tourner" le JLabel
   * passé en paramètre.
   *
   * @param label le JLabel a faire tourner.
   * @return Le thread lancé.
   */
  public Thread roule(final JLabel label) {
    return new Thread(new Runnable() {
      @Override
      public void run() {
        // Un thread qui change le chiffre du JLabel passé en paramètre
        // pour simuler une roue avec des chiffres qui tournerait.
        
        // Un nombre est choisi au hasard dans [20, 40]
        int nb = random.nextInt(20) + 21;

        // Le chiffre du label est changé 100nb fois toutes les millisecondes
        // Ca dure entre 2 et 4 secondes.
        // Ca tombe l'impresion que la roue tourne très vite.
        for (int i = 0; i < nb * 100; i++) {
          label.setText((label.getText().charAt(0) + 1) % 10 + "");
          try {
            Thread.sleep(1);
          } catch (InterruptedException ex) {
          }
        }

        // Le chiffre du label est changé 10nb fois toutes les dix millisecondes
        // Ca dure entre 2 et 4 secondes
        // Pour donner l'impression que la roue ralentit.
        for (int i = 0; i < nb * 10; i++) {
          label.setText((label.getText().charAt(0) + 1) % 10 + "");
          try {
            Thread.sleep(10);
          } catch (InterruptedException ex) {
          }
        }

        // Le chiffre du label est changé nb fois toutes les cent millisecondes
        // Ca dure entre 2 et 4 secondes 
        for (int i = 0; i < nb; i++) {
          label.setText((label.getText().charAt(0) + 1) % 10 + "");
          try {
            Thread.sleep(100);
          } catch (InterruptedException ex) {
          }
        }
        // Une fois ceci terminé (La durée totale est entre 6 et 12 secondes)
        // le thread se termine aussi. Il "meurt". Il ne pourra plus être 
        // possible de le relancer.
      }
    });
  }

  public static void main(String[] args) {
    new Bandit();

  }
}
