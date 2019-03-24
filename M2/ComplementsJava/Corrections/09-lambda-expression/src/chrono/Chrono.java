
package chrono;

import java.time.Duration;


public class Chrono {
    private static long dernierJalon = System.nanoTime();
    public static void affichageDureeDepuisDernierJalon(String msg) {
        long nouveauJalon = System.nanoTime();
       
        System.out.println(msg + " " + (Duration.ofNanos(nouveauJalon-dernierJalon)));
        dernierJalon = nouveauJalon;
    }
    public static void premierJalon() {
        dernierJalon = System.nanoTime();
    }
}
