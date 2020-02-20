/*
 * Creative commons CC BY-NC-SA 2020 Yvan Maillot <yvan.maillot@uha.fr>
 *
 *     Share - You can copy and redistribute the material in any medium or format
 * 
 *     Adapt - You can remix, transform, and build upon the material 
 * 
 * Under the following terms :
 * 
 *     Attribution - You must give appropriate credit, provide a link to the license, 
 *     and indicate if changes were made. You may do so in any reasonable manner, 
 *     but not in any way that suggests the licensor endorses you or your use. 
 * 
 *     NonCommercial — You may not use the material for commercial purposes. 
 * 
 *     ShareAlike — If you remix, transform, or build upon the material, 
 *     you must distribute your contributions under the same license as the original. 
 * 
 * Notices:    You do not have to comply with the license for elements of 
 *             the material in the public domain or where your use is permitted 
 *             by an applicable exception or limitation. 
 * 
 * No warranties are given. The license may not give you all of the permissions 
 * necessary for your intended use. For example, other rights such as publicity, 
 * privacy, or moral rights may limit how you use the material. 
 * 
 * See <https://creativecommons.org/licenses/by-nc-sa/4.0/>.
 */
package equations;

import exceptions.WrongNumberOfValuesException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class SystemeTest {
    private final double EPSILON = 1e-6;
    
    public SystemeTest() {
    }

    @Test
    public void testConstructeur() {
        try {
            Systeme s = new Systeme(3, 
                    1,  -1, 2,  5,
                    3,  2,  1,  10,
                    2, -3, -2, -10
            );
            assertEquals(1, s.getA(0, 0), EPSILON);
            assertEquals(-1, s.getA(0, 1), EPSILON);
            assertEquals(2, s.getA(0, 2), EPSILON);
            assertEquals(5, s.getR(0), EPSILON);
        } catch (WrongNumberOfValuesException ex) {
            fail("Devrait être ok");            
        }
    }

    @Test
    public void testGetA() {
    }

    @Test
    public void testGetR() {
    }

    @Test
    public void testPivot() {
        try {
            Systeme s = new Systeme(3, 
                    1,  -1, 2,  5,
                    3,  2,  1,  10,
                    2, -3, -2, -10
            );
            assertEquals(1, s.pivot(0, 0));
            assertEquals(1, s.pivot(1, 0));
            assertEquals(2, s.pivot(2, 0));
            
            assertEquals(2, s.pivot(0, 1));
            assertEquals(2, s.pivot(1, 1));
            assertEquals(2, s.pivot(2, 1));
        } catch (WrongNumberOfValuesException ex) {
            fail("Devrait être ok");            
        }
    }

    @Test
    public void testEchange() {
        try {
            Systeme s = new Systeme(3, 
                    1,  -1, 2,  5,
                    3,  2,  1,  10,
                    2, -3, -2, -10
            );
            
            Equation e0 = new Equation(5, 1, -1, 2);
            Equation e1 = new Equation(10, 3, 2, 1);
            Equation e2 = new Equation(-10, 2, -3, -2);
            
            assertEquals(e0, s.getE(0));
            assertEquals(e1, s.getE(1));
            assertEquals(e2, s.getE(2));
            
            s.echange(0, 1);
            assertEquals(e0, s.getE(1));
            assertEquals(e1, s.getE(0));
            
            s.echange(0, 1);
            assertEquals(e0, s.getE(0));
            assertEquals(e1, s.getE(1));
            
            s.echange(0, 2);
            assertEquals(e2, s.getE(0));
            assertEquals(e0, s.getE(2));
            
        } catch (WrongNumberOfValuesException ex) {
            fail("Devrait être ok");            
        }
    }
    
}
