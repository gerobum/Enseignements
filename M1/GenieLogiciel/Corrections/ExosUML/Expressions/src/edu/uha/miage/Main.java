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
package edu.uha.miage;

import edu.uha.miage.expressions.*;

import java.util.function.Consumer;
import java.util.function.Function;


/**
 *
 * @author yvan
 */
public class Main {

    public static void main(String[] args) {


        Variable x = new Variable(1);
        Variable y = new Variable(3);
        Constante _2 = new Constante(2);
        Constante _3 = new Constante(3);
        Expression e = new Division(
                new Addition(
                        new Multiplication(
                                _2,
                                x
                        ),
                        _3
                ),
                new Multiplication(
                        new Negation(x),
                        new Substraction(y, _2)
                )
        );

        System.out.println(e + " = " + e.evaluate());

        x.setValue(2);
        System.out.println(e + " = " + e.evaluate());
     
    }
}
