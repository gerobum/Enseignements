package demonstration._01_interruption;

/**
 * Pour lancer ou faire taire des bavards
 * @author yvan
 */
public class Main {
    public static void main(String[] args) {
        new Taiseur(new Bavard("bla", 500), 30000);
        new Taiseur(new Bavard("bli", 300), 15000);
        new Taiseur(new TresBavard("BLABLO"), 5000);
    }
}
