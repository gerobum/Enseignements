package rf.pti_pgm.main;

import caseinedev.IntrospectionUtilities;
import cf.pti_pgm.geometrie.Point;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author yvan
 */
public class TestPoint {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, FileNotFoundException {
  
        //System.out.println(new double[] {}.getClass());
        IntrospectionUtilities.checkIfMutable(Point.class);
    }
}
