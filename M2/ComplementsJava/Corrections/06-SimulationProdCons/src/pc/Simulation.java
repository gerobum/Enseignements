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
    // Un consommateur qui consomme dans la file une infinité de paquets
    // toutes les x à y millisecondes.
    x = 1000;
    y = 5000;
    final Consommateur c = new Consommateur(file, x, y, -1);
    // Un producteur qui produit 10 paquets toutes les 2 secondes
    // et les met dans la file 
    x = 2000;
    final Producteur p1 = new Producteur(file, x, x, 10);
    // Un producteur qui produit des paquets toutes les x à y secondes
    // et les met dans la file 
    x = 100;
    y = 500;
    final Producteur p2 = new Producteur(file, x, y, -1);
    c.start();
    p1.start();
    p2.start();
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          // Fin de production et de consommation
          // au bout d'un certain temps.
          Thread.sleep(60000);
          p1.fin();
          p2.fin();
          c.fin();
          System.out.println("Demande d'arrêt");
        } catch (InterruptedException ex) {
        }
      }
    }).start();
  }
}
