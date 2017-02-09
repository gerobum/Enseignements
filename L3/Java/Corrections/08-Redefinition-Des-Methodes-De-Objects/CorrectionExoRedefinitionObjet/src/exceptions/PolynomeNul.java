
package exceptions;

/**
 *
 * @author yvan
 */
public class PolynomeNul extends RuntimeException {
    public PolynomeNul() {
        super("Impossible d'évaluer un polynôme nul");
    }
}
