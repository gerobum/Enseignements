
package exceptions;

/**
 *
 * @author yvan
 */
public class MauvaisDegréDePolynome extends Exception {

    public MauvaisDegréDePolynome(int i, int d) {
        super(String.format("%d n'est pas un degré acceptable pour ce polynome, il devrait être dans [0, %d]", i, d));
    }
    
}
