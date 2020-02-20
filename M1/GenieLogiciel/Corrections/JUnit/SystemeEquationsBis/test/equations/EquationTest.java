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

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class EquationTest {
    
    public EquationTest() {
    }

    @Test
    public void testEquals() {
    }

    @Test
    public void testToString() {
        Equation e;
        
        double r = 2;
        double[] a = {5};
        e = new Equation(r, a[0]);
        assertEquals(String.format("new Equation(%f, %f).toString()", r, a[0]), "5.0x1 = 2.0", e.toString());
        r = 0;
        a = new double[]{2,3,4};
        e = new Equation(r, 1, a);
        assertEquals(String.format("new Equation(%f, %f, %f, %f, %f).toString()", r, 1.0, a[0], a[1], a[2]), "1.0x1 + 2.0x2 + 3.0x3 + 4.0x4 = 0.0", e.toString());
    }
    
}
