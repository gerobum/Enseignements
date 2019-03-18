package demonstration.interruption;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maillot
 */
public class Bla {

    public static Random random = new Random();

    private Bla(String mot) {

        TresBavard b = new TresBavard(mot);
        new Taiseur(b).start();
        b.start();

    }

    private Bla(int duree, String mot) {
        Bavard b = new Bavard(duree, mot);
        new Taiseur(b).start();
        b.start();

    }

    class TresBavard extends Thread {

        public final String mot;

        public TresBavard(String mot) {
            this.mot = mot;
        }

        @Override
        public void run() {
            while (true) {
                System.out.println(mot);
                if (Thread.interrupted()) {
                    System.out.println("Fin de " + mot);
                    return;
                }
            }
        }
    }

    class Bavard extends TresBavard {

        private final int duree;

        public Bavard(int duree, String mot) {
            super(mot);
            this.duree = duree;
        }

        @Override
        public void run() {
            while (true) {
                System.out.println(mot);
                try {
                    Thread.sleep(duree);
                } catch (InterruptedException ex) {
                    System.out.println("Fin de " + mot);
                    return;
                }

            }

        }
    }

    class Taiseur extends Thread {

        private TresBavard bavard;

        public Taiseur(TresBavard bavard) {
            this.bavard = bavard;
        }

        @Override
        public void run() {
            try {
                int duree = (random.nextInt(10) + 1) * 1000;
                System.out.println("Attente " + duree);
                Thread.sleep(duree);
                System.out.println("Mort de " + bavard.mot);
                bavard.interrupt();
            } catch (InterruptedException ex) {
                Logger.getLogger(Bla.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        new Bla("bla");
        new Bla(500, "blo");
    }
}
