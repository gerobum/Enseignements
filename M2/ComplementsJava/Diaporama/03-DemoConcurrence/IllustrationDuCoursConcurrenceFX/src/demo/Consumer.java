
package demo;

import java.util.concurrent.BlockingDeque;
import javafx.concurrent.Task;

/**
 *
 * @author maillot
 */
public class Consumer extends Task<Void> {

    private final BlockingDeque<Double> QUEUE;
    private final String NAME;
    private final int DELTA;
    private Afficheur afficheur;

    public void setAfficheur(Afficheur afficheur) {
        this.afficheur = afficheur;
    }

    /**
     * Un consommateur qui consomme des valeurs d'une file bloquante.
     * @param queue la file dans laquelle consommer des valeurs.
     * @param name un nom pour le consommateur
     * @param delta l'intervalle de temps entre deux consommations.
     * @param afficheur Une interface pour définir la façon d'afficher les résutats.
     */
    public Consumer(BlockingDeque<Double> queue, String name, int delta, Afficheur afficheur) {
        this.QUEUE = queue;
        this.NAME = name;
        this.DELTA = delta;
        this.afficheur = afficheur;
    }
    
    public Consumer(BlockingDeque<Double> queue, String name, int delta) {
        this(queue, name, delta, null);
    }
    
    public String getConsumerName() {
        return NAME;
    }


    /**
     * Pour mettre fin au consommateur en cas d'attente trop longue.
     * @param duration le temps d'attente.
     * @return 
     */
    private Thread toFinishIfWaitingTooMuch(final int duration) {
        return new Thread(new Runnable() {

            @Override
            public void run() {
                /*try {
                    Thread.sleep(duration);
                    Consumer.this.
                } catch (InterruptedException ex) {
                }*/
            }
        });
    }



    @Override
    protected Void call() throws Exception {
        while (true) {
            try {
                Thread toFinish = toFinishIfWaitingTooMuch(10000);
                toFinish.start();
                Thread.sleep(DELTA);
                updateProgress(QUEUE.take(), 1);
                toFinish.interrupt();

            } catch (InterruptedException ex) {
                System.out.println("Fin du consommateur " + NAME);
                return null;
            }
        }
    }
}
