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
package edu.uha.miage.main;

import edu.uha.miage.equation.Equation;
import java.util.Scanner;

/**
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez a b et c");
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        Equation equation = new Equation(a, b, c);
        switch(equation.getRootsCount()) {
            case 0:
                System.out.println("Pas de solution");
                break;
            case 1:
                System.out.println("Une seule racine : " + equation.getX1());
                break;
            default:
                System.out.println("Deux racines : " + equation.getX1() + " et " + equation.getX2());
                break;
        }
    }
}
