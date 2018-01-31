package part1.exo02;

/**
 *
 * @author yvan
 */
public class BonneAnnee {
    public static void main(String[] args) {
        System.out.print("Bonne année");
        for(int i = 0; i < args.length; ++i) {
            System.out.print(" " + args[i]);
        }
    }
}
