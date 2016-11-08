package pc;

import static java.lang.Thread.sleep;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import types.Paquet;

class Consommateurs {

    private Set<Consommateur> consommateurs = new HashSet<>();

    public Consommateurs(BlockingQueue<Paquet> file, int n, int duree) {
        for (int i = 0; i < n; ++i) {
            Consommateur p = new Consommateur(file, 1000, 2000, -1);
            consommateurs.add(p);
            p.start();
        }

        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(duree);
                    Consommateurs.this.stopAll();
                } catch (InterruptedException ex) {
                }
            }
        }.start();
    }

    private void stopAll() {
        for(Consommateur c : consommateurs) {
            c.interrupt();
        }
    }

}
class Producteurs {

    private Set<Producteur> producteurs = new HashSet<>();

    public Producteurs(BlockingQueue<Paquet> file, int n, int duree) {
        for (int i = 0; i < n; ++i) {
            Producteur p = new Producteur(file, 1000, 2000, -1);
            producteurs.add(p);
            p.start();
        }

        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(duree);
                    Producteurs.this.stopAll();
                } catch (InterruptedException ex) {
                }
            }
        }.start();
    }

    private void stopAll() {
        for(Producteur p : producteurs) {
            p.interrupt();
        }
    }

}


/**
 *
 * @author maillot
 */
public class AutreSimulation {

    public static void main(String[] args) {
        BlockingQueue<Paquet> file = new LinkedBlockingQueue<>(10);

        new Consommateurs(file, 50, 60000);
        new Producteurs(file, 2, 60000);
        
    }
}
