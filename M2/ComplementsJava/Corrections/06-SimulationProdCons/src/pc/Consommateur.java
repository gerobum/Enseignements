package pc;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import types.Paquet;
import static java.lang.Math.*;


/**
 * 1. Réalisez une classe Consommateur qui est un thread consommant n paquets (n < 0 ⇔
n = ∞) dans une file de type BlockingQueue<Paquet> selon une cadence aléatoire comprise
18
entre mini ms et maxi ms. Un numéro d’ordre de création du consommateur est donné pour
composer son nom. Le premier créé s’appelle C1, le deuxième C2 et ainsi de suite.
2. Un consommateur doit dans son thread consommer des paquets en quantité définie par le
paramètre n (ou infinie si n<0 ). Chaque paquet consommé dans la file doit voir ses dates de
demande de consommation et de consommation mises à jour.
3. Sa méthode toString() doit retourner "Cx" où x est le numéro du consommateur.
4. Son thread doit pouvoir se terminer (proprement) en cas d’interruption ( interrupt() ).
 * 
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class Consommateur extends Thread {

  private static final Random RANDOM = new Random();
  private final BlockingQueue<Paquet> file;
  private final int mini;
  private final int maxi;
  private final int n;
  private static int numeroOrdre = 0;
  private final int numero;
  
  /**
   * Ce consommateur consomme n paquets (n<0 <=> n infini) dans la file.
   * L'écart de temps entre deux consommations est un nombre de millisecondes
   * compris entre mini et maxi.
   * Si maxi <= mini ce temps d'attente est exactement mini millisecondes.
   * @param file dans laquelle les paquets sont pris
   * @param mini temps minimum d'attente entre deux consommations
   * @param maxi temps maximum d'attente entre deux consommations
   * @param n nombre de paquets consommés ou infini si n<0
   */
  public Consommateur(BlockingQueue<Paquet> file, int mini, int maxi, int n) {
    this.file = file;
    this.mini = min(mini, maxi);
    this.maxi = max(mini, maxi);
    this.n = n;
    numero = ++numeroOrdre;
  }

  @Override
  public void run() {
    System.out.println("Démarrage de " + this);
    try {
      if (n < 0) {
        // Si n < 0 alors la consommation est infinie
        while (true) {
          attendreEtConsommer();
        }
      } else {
        for (int i = 0; i < n; i++) {
          attendreEtConsommer();
        }
      }
    } catch (InterruptedException ex) {
    }
    System.out.println("Terminaison de " + this);
  }

  private void attendreEtConsommer() throws InterruptedException {
    // Calcul de l'attente
    sleep(RANDOM.nextInt(maxi-mini+1) + mini);
    // La demande de paquet va se faire. 
    // Le paquet que l'on va consommer n'est pas encore connu.
    // Mais sa date l'est. Donc "l'astuce" est de mémoriser cette date
    // juste avant la demande.
    Date dateDemande = new Date();
    // La demande est faite mais il peut y avoir un temps d'attente.
    Paquet p = file.take();
    // Ici, un paquet a été récupéré.
    // Il a été retiré maintenant.
    p.setDateConsommation(new Date());
    // Son consommateur est affecté
    p.setConsommateur(this);
    // Sa date de demande est celle faite avant le "take()"
    p.setDateDemandeConsommation(dateDemande);
    System.out.println(p);
  }

  @Override
  public String toString() {
    return "C" + numero;
  }
}
