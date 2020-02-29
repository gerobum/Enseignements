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
package edu.uha.miage.swing;

import edu.uha.miage.Porteur;
import edu.uha.miage.LongOf10;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

class ParametreInattendu extends Exception {
    
}

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class Frame extends JFrame {

    private JTextField jtf;

    public Frame() {
        super("Titre");
        jtf = new JTextField(30);
        getContentPane().add(jtf);
        getContentPane().add(new JButton("Ok"), "South");
        new LongOf10(new Porteur(jtf));
        pack();
        setVisible(true);

    }

    /**
     * Calcule et retourne x élevé à la puissance n
     * 
     * Le calcul ne peut pas se faire 
     *   1. si n est strictement négatif
     *   2. pour 0 puissance 0
     * Dans ces deux cas une exception ParametreInattendu est lancée.
     * 
     * Remarque : aucune vérification n'est faite si x puissance n dépasse
     * la capacité des int
     * 
     * On suppose donc que x puissance n est inférieur à Integer.MAX_VALUE
     * 
     * @param x l'entier à élever à la puissance
     * @param n l'entier exposant de x
     * @return x puissance n
     * @throws ParametreInattendu si n < 0 ou (x = 0 et n = 0)
     */
    public static int puissance(int x, int n) throws ParametreInattendu {
        if (n < 0 || x == 0 && n == 0) {
            throw new ParametreInattendu();
        } else if (x == 0 || x == 1) {
            return x;
        } else if (n == 0) {
            return 1;
        } else if (n % 2 == 0) {
            return puissance(x * x, n / 2);
        } else {
            return x * puissance(x, n - 1);
        }
    }

    public static void main(String[] args) {
        new Frame();
    }
}
