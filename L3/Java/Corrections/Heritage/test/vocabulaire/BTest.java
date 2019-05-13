/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package vocabulaire;

import java.lang.reflect.Modifier;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yvan
 */
public class BTest {

    @Test
    public void p0000_checkClassB() {
        System.out.println("check class B");
        assertTrue(" (Héritage)", B.class.getSuperclass().equals(A.class));
        Class<?> x = B.class;
        assertTrue("Revoir  (Modificateurs)", Modifier.isAbstract(1) == Modifier.isAbstract(x.getModifiers()));
        assertTrue("Revoir  (Modificateurs)", Modifier.isFinal(1) == Modifier.isFinal(x.getModifiers()));
        assertTrue("Revoir  (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
    }
}
