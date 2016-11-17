
package exceptions;

/**
 *
 * @author yvan
 */
public class InversionFractionNulle extends Exception {

    public InversionFractionNulle() {
        super("Impossible d'inverser une fraction nulle");
    }
    
}
