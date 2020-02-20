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
import static java.lang.Math.abs;
import java.util.ArrayList;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class Systeme {

    private final ArrayList<Equation> systeme = new ArrayList<>();

    /**
     * Pour créer le système / | x1 + -x2 + 2x3 = 5 | < 3x1 + 2x2 + x3 = 10 |
     * |2x1 + -3x2 + -2x3 = 10 \
     *
     * Systeme s = new Systeme(3, // Parce que 3 équations
     *
     * 1, -1, 2, 5, // ligne 1
     *
     * 3, 2, 1, 10, // ligne 2
     *
     * 2, -3, -2, -10 // ligne 3
     *
     * );
     *
     * @param nbEquations
     * @param a
     * @throws WrongNumberOfValuesException
     */
    public Systeme(int nbEquations, double... a) throws WrongNumberOfValuesException {
        if (a.length != nbEquations * (nbEquations + 1)) {
            throw new WrongNumberOfValuesException();
        }
        int k = 0;
        for (int l = 0; l < nbEquations; ++l) {
            double a0 = a[k++];
            double[] ai = new double[nbEquations - 1];
            for (int i = 0; i < ai.length; ++i) {
                ai[i] = a[k++];
            }
            double r = a[k++];
            systeme.add(new Equation(r, a0, ai));
        }
    }

    public Equation getE(int l) {
        return systeme.get(l);
    }

    public double getA(int l, int c) {
        return systeme.get(l).getA(c);
    }

    public double getR(int l) {
        return systeme.get(l).getR();
    }

    /**
     * Retourne le numéro de la ligne du pivot c'est-à-dire une dont le coef en
     * colonne c est maximal en valeur absolue
     *
     * @param l
     * @param c
     * @return
     */
    public int pivot(int l, int c) {
        int lmax = l;
        for (int il = l + 1; il < systeme.size(); ++il) {
            if (abs(getA(il, c)) > abs(getA(lmax, c))) {
                lmax = il;
            }
        }
        return lmax;
    }

    /**
     * Echange les equations l1 et l2 du système
     *
     * @param l1
     * @param l2
     */
    public void echange(int l1, int l2) {
        Equation e = systeme.get(l1);
        systeme.set(l1, systeme.get(l2));
        systeme.set(l2, e);
    }

    /**
     * Met à 1 le coef i de l'équation i en divisant par le pivot l'équation par
     * le pivot
     *
     * @param i
     */
    public void mettreA1(int i) throws PivotNulException {
        if (abs(getA(i, i)) < 1e-6) {
            throw new PivotNulException();
        }
        getE(i).divisePar(getA(i, i));
    }

    /**
     * Met à 0 le coef i de l'équation i à l'aide du pivot le pivot
     *
     * @param i
     * @param p
     */
    public void mettreA0(int i, int p) throws PivotNulException, WrongNumberOfValuesException {
        getE(i).retirer(getE(p), getA(i, p));
    }

    public void triangulation() throws WrongNumberOfValuesException, PivotNulException {
        for (int j = 0; j < systeme.size(); ++j) {
            int lp = pivot(j, j);
            echange(j, lp);
            mettreA1(j);
            for (int i = j+1; i < systeme.size(); ++i) {
                mettreA0(i, j);
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Equation e : systeme) {
            sb.append(e).append('\n');
        }

        return sb.toString();
    }

}
