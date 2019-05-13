package exceptions;

/**
 *
 * @author Yvan
 */
public class DénominateurNul extends Exception {

  public DénominateurNul() {
    super("Attention, le dénominateur d'une fraction ne peut pas être nul");
  }
}
