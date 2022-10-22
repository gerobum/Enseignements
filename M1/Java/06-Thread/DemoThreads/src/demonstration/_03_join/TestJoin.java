package demonstration._03_join;

/**
 * Un thread qui attend que passent les secondes dont le nombre est donné à la
 * construction.
 *
 * Il égrène les secondes restantes. C'est-à-dire que toutes les secondes, il
 * affiche le nombre de secondes restantes.
 */
class Egrenage extends Thread {

    private final int duree;

    public Egrenage(int duree) {
        this.duree = duree;
    }

    @Override
    public void run() {
        int dureeRestante = duree;
        while (dureeRestante > 0) {
            System.out.println("Il reste " + dureeRestante + " secondes");
            dureeRestante--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
        }
    }

}

/**
 * Illustre la méthode de synchronisation join(). Le programme principal attend
 * la fin du thread egrenage avant de continuer.
 *
 */
public class TestJoin {

    public static void main(String[] args) throws InterruptedException {

        // Ce thread va égréner les secondes restantes
        Thread egrenage = new Egrenage(10);
        
        System.out.println("Début de l'égrénage");
        
        egrenage.start();
        
        // Attendre la fin de l'égrénage
        egrenage.join();
        
        System.out.println("Fin de l'égrénage");
    }

}
