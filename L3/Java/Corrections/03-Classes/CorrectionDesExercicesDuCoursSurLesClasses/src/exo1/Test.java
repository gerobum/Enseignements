
package exo1;

/**
 *
 * @author yvan
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(AutoCompteur.getNbInstances());
        for(int i = 0; i < 100; i++) {
            new AutoCompteur();
        }
    }
}
