
package exceptions;

/**
 *
 * @author yvan
 */
public class DénominateurNul extends RuntimeException {

    public DénominateurNul() {
        super("Le dénominateur ne peut pas être nul");
    }
    
}
