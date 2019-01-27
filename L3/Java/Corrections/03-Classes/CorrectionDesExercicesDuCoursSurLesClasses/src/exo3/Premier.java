/*
 L’objectif de cet exercice est de montrer l’intérêt des blocs d’initialisation statique.
 Écrire une classe utilitaire capable de donner des nombres premiers.

 */
package exo3;

/**
 *
 * @author yvan
 */
public class Premier {
    /*
     Cette classe possède deux attributs de classe, privés et constants qui sont des tableaux : */

    private static final boolean[] EST_PREMIER = new boolean[Short.MAX_VALUE];
    /*
     Ce tableau sera utilisé pour savoir si un nombre est premier. Le booléen à l’indice p dans
     est_premier est vrai si et seulement si p est premier. Par exemple est_premier[12] est false
     alors que est_premier[97] est true.*/
    private static final short[] PREMIER;
    /* Ce tableau contient les premiers nombres premiers inférieurs à Short.MAX_VALUE.
     */

    // Il faut remplir les tableaux premier et est_premier.
    // Ce serait possible "à la main" mais fastidieux.
    // C'est aussi possible en appliquant l'algorithme du crible d'Eratostene.
    // Mais il faut le faire dans un bloc d'initialisation statique
    static {
        for (int i = 2; i < EST_PREMIER.length; ++i) {
            EST_PREMIER[i] = true;
        }
        {   // JCe bloc ne sert qu'à isoler la variable i
            // et pouvoir la réutiliser par la suite
            int i = 2;

            while (i * i < EST_PREMIER.length) {
                for (int j = i * i; j < EST_PREMIER.length; j += i) {
                    EST_PREMIER[j] = false;
                }
                i++;
            }
        }
        int nbpremiers = 0;
        for (int i = 0; i < EST_PREMIER.length; ++i) {
            if (EST_PREMIER[i]) {
                nbpremiers++;
            }
        }
        PREMIER = new short[nbpremiers];
        int k = 0;
        for (short i = 0; i < EST_PREMIER.length; ++i) {
            if (EST_PREMIER[i]) {
                PREMIER[k++] = i;
            }
        }
    }

    public static boolean estPremier(short p) {
        if (p < 0) {
            return false;
        }
        return EST_PREMIER[p];
    }

    public static int premier(short p) {
        return PREMIER[p];
    }

    public static int nbPremiers() {
        return PREMIER.length;
    }

}
