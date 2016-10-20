package pc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import types.Paquet;

/**
 *
 * @author yvan
 */
public class Simulation {
  public static void main(String[] args) {
    // Une file bloquante de capacité 10
    BlockingQueue<Paquet> file = new LinkedBlockingQueue<>(10);
    int x, y;

    x = 100; y = 500;
    final Consommateur c1 = new Consommateur(file, x, y, -1);
    // Un consommateur qui consomme dans la file une infinité de paquets,
    // un toutes les x à y millisecondes.
    x = 10; y = 50;
    final Consommateur c2 = new Consommateur(file, x, y, -1);
    // idem    
    x = 2000; y = 2000;
    final Producteur p1 = new Producteur(file, x, y, 3);
    // Un producteur qui produit 3 paquets, un toutes les 2 secondes
    x = 10; y = 50;
    final Producteur p2 = new Producteur(file, x, y, -1);
    // Un producteur qui produit dans la file une infinité de paquets,
    // un toutes les x à y millisecondes.

    c1.start();
    p1.start();
    p2.start();
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(6000);
          // Au bout d'un certain temps c2 vient à la rescousse.
          c2.start();
          System.out.println("C2 vient à la rescousse");
        } catch (InterruptedException ex) {
        }
      }
    }).start();
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          // Fin de production et de consommation
          // au bout d'un certain temps.
          Thread.sleep(8000);
          p1.interrupt();
          p2.interrupt();
          c1.interrupt();
          c2.interrupt();
          System.out.println("Demande d'arrêt");
        } catch (InterruptedException ex) {
        }
      }
    }).start();
  }
}
