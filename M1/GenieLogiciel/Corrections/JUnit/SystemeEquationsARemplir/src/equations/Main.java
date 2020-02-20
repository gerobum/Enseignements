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

import exceptions.PivotNulException;
import exceptions.WrongNumberOfValuesException;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class Main {
    public static void main(String[] args) throws WrongNumberOfValuesException, PivotNulException {
              
            /*Systeme s = new Systeme(5, 
                    1,  -1, 2, 7, 1,  5,
                    3,  2,  1, 2, 5,  10,
                    2, -3, -2, 6, 7, -8,
                    3, -3, -2, 7, 11, -7,
                    1, 4, -8, 19, 11, 4
            );*/
            
  
            Systeme s = new Systeme(3, 
                    1,  -1, 2,  5,
                    3,  2,  1,  10,
                    2, -3, -2, -10
            );
            
            System.out.println(s);
            s.triangulation();
            System.out.println(s);
      
    }
}
