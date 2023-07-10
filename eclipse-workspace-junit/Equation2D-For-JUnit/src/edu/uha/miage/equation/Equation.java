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
package edu.uha.miage.equation;

import edu.uha.miage.NullCoefException;

/**
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class Equation {

    private int a, b, c;

    private int rootsCount;

    private double x1;

    private double x2;

    /**
     * Calcule rootsCount, x1 et x2 de sorte que rootsCount soit le nombre de
     * racines réelles de l'équation
     *
     * (E) ax^2 + bx + c = 0 (a fois x au carré plus b fois x plus c égale à 0)
     *
     * et que x1 et x2 soient leur racine (si elles existent).
     *
     * Plus précisément, x1 et x2 sont des valeurs de type double égales aux
     * racines réelles à 1x10-7 près (1e-7).
     *
     * rootsCount doit être égal à 0, 1 ou 2 (et rien d'autre)
     *
     * Si rootsCount = 0 alors x1 et x2 ne sont pas définis (on pourra choisir
     * Double.NaN)
     *
     * Si rootsCount = 1 alors x1 = x2 et est la racine double de (E) à 1x10-7 près.
     *
     * Si rootsCount = 2 alors x1 != x2 sont les racines de (E) à 1x10-7 près.
     *
     * @param a le coef a de (E) ax2 + bx + c = 0
     * @param b le coef b de (E) ax2 + bx + c = 0
     * @param c le coef c de (E) ax2 + bx + c = 0
     */
    public Equation(int a, int b, int c) {
    // TODO
    	if (a == 0) throw new NullCoefException();
    	this.a = a;
    	this.b = b;
    	this.c = c;
    	double delta = b*b-4*a*c;
    	if (delta < 0) {
    		rootsCount = 0;
    	} else if (delta > 0) {
    		rootsCount = 2; 
    		double rdelta = Math.sqrt(delta);
    		x1 = (-b-rdelta)/(2*a);
    		x2 = (-b+rdelta)/(2*a);
    	} else {
    		rootsCount = 1;
    		x1 = x2 = -b/(2.0*a);
    	}
    }

    public int getRootsCount() {
        return rootsCount;
    }

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }

    @Override
    public String toString() {
        return a + "x^2 + " + b + "x + " + c + " = 0";
    }
}
