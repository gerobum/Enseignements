
package caseine.cf.exceptions;

/**
 *
 * @author yvan
 */
public class InversionDuneFractionNulle extends Exception {

  public InversionDuneFractionNulle() {
    super("Impossible d'inverser une fraction nulle");
  }
}
