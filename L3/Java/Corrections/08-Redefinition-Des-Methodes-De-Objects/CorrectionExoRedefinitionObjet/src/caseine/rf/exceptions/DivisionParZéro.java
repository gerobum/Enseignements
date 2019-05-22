package caseine.rf.exceptions;

import caseine.cf.exceptions.*;
import exceptions.*;

/**
 *
 * @author Yvan
 */
public class DivisionParZéro extends Exception {

  public DivisionParZéro() {
    super("Attention, division par une fraction nulle");
  }
}
