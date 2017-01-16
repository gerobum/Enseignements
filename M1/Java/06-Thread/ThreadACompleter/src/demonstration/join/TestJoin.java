/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demonstration.join;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Illustre la méthode de synchronisation join(). Le programme principal attend 
 * la fin du thread t avant de continuer.
 * @author maillot
 */
public class TestJoin {
    /**
     * Créer un thread dont la durée de vie est donnée en seconces dans le paramètre.
     * Il affiche à chaque seconde le nombre de secondes restantes.
     * @param duree Nombre de secondes restantes.
     * @return le thread
     */
    public static Thread creation(final int duree) {
        return new Thread() {

            @Override
            /**
             * 
             */
            public void run() {
                try {
                    Thread t = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            int dureeRestante = duree;
                            while(dureeRestante > 0) {
                                System.out.println("Duree de vie restante " + dureeRestante + " secondes");
                                dureeRestante--;
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException ex) {}
                            }
                        }
                    });
                    
                    
                    t.start();
                    t.join(); // Attend la fin de t pour terminer

                    System.out.println("Aargh !");
                } catch (InterruptedException ex) {}
            }
            
        };
    }
    
    public static void main(String[] args) throws InterruptedException {
        Thread t = creation(10);
        t.start();
        // Attendre la fin de thread
        //t.join();
        System.out.println("R.I.P ");       
    }
    
}
