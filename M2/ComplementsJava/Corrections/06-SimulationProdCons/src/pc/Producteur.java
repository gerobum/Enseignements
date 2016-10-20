package pc;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import types.Paquet;
import static java.lang.Math.*;

/**
 *
 * @author yvan
 */
public class Producteur extends Thread {

  private static final Random RANDOM = new Random();
  private final BlockingQueue<Paquet> file;
  private final int mini;
  private final int maxi;
  private final int n;
  private static int numeroOrdre = 0;
  private final int numero;

  /**
   * Ce producteur produit n paquets (n<0 <=> n infini) et les place dans une file.
   * L'écart de temps entre deux productions est un nombre de millisecondes
   * compris entre mini et maxi.
   * Si maxi <= mini ce temps d'attente est exactement mini millisecondes.
   * @param file dans laquelle les paquets sont mis
   * @param mini temps minimum d'attente entre deux productions
   * @param maxi temps maximum d'attente entre deux productions
   * @param n nombre de paquets produits ou infini si n<0
   */
  public Producteur(BlockingQueue<Paquet> file, int mini, int maxi, int n) {
    this.file = file;
    this.mini = min(mini, maxi);
    // Pour éviter d'avoir un intervalle négatif.
    this.maxi = max(mini, maxi);
    this.n = n;
    numero = ++numeroOrdre;
  }

  @Override
  public void run() {
    System.out.println("Démarrage de " + this);
    try {
      if (n < 0) {
        // Si n < 0 alors la production est infinie
        while (true) {
          attendreEtProduire();
        }
      } else {
        for (int i = 0; i < n; i++) {
          attendreEtProduire();
        }
      }
    } catch (InterruptedException ex) {
    }
    System.out.println("Terminaison de " + this);
  }

  private void attendreEtProduire() throws InterruptedException {
    // Calcul de l'attente
    sleep(RANDOM.nextInt(maxi-mini+1)+mini);
    // La création du paquet doit impérativement
    // se faire avant la tentative de mise en file
    // si l'on veut observer l'éventuel temps d'attente.
    Paquet p = new Paquet(this);
    file.put(p);
    // La date de rangement est affectée après l'attente.
    p.setDateRangement(new Date());
  }
  @Override
  public String toString() {
    return "P" + numero;
  }
}
