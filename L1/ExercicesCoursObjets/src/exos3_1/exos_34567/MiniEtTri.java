package exos3_1.exos_34567;

import java.util.Random;

public class MiniEtTri {

    // #### 3.1-1)
    static int auHasardEntre(int d, int f, Random r) {
        return d + r.nextInt((f - d) + 1);
    }

    // #### 3.1-1)
    static void remplissageAuHasard(int[] tableau) {
        Random r = new Random();
        for (int i = 0; i < tableau.length; ++i) {
            tableau[i] = r.nextInt(100);
        }
    }

    // #### 3.1-1)
    static void affichage(int[] tableau) {
        for (int v : tableau) {
            System.out.print(v + " ");
        }
        System.out.println();
    }

    static void affiche(int[] t, int p) {
        System.out.print("{");
        for (int i = p; i < t.length; ++i) {
            System.out.print(t[i] + " ");
        }
        System.out.print("}");
    }

    // #### 3.1-2)
    static int mini(int[] t, int p) {
        int vmini = t[p];
        for (int i = p; i < t.length; ++i) {
            if (t[i] < vmini) {
                vmini = t[i];
            }
        }
        return vmini;
    }
    
    static boolean estMinimal(int v, int[] t, int p) {
        for(int i = p; i < t.length; ++i) {
            if (t[i] < v) {
                return false;
            }
        }
        return true;
    }
    
    static boolean estMinimaletEstDedans(int v, int[] t, int p) {
        boolean estDedans = false;
        for(int i = p; i < t.length; ++i) {
            if (t[i] < v) {
                return false;
            }
            if (t[i] == v) {
                estDedans = true;
            }
        }
        return estDedans;
    }
    
    // #### 3.1-2)
    static void testMini() {
        int[] t = new int[10];
        remplissageAuHasard(t);
        for(int i = 0; i < t.length; ++i) {
            int mini = mini(t, i);
            System.out.print(mini +" <= ");
            affiche(t, i);
            if (estMinimaletEstDedans(mini, t, i)) {
                System.out.println(" (Vérifié)");
            } else {
                System.out.println(" [Problème à partir de " + i + "]");
                System.exit(0);
            }
        }
    }
    // #### 3.1-2)
static void testiMini() {
    int[] t = new int[10];
    remplissageAuHasard(t);
    for(int i = 0; i < t.length; ++i) {
        int imini = imini(t, i);
        System.out.print("En " + imini + " : " + t[imini]  +" <= ");
        affiche(t, i);
        if (estMinimaletEstDedans(t[imini], t, i)) {
            System.out.println(" (Vérifié)");
        } else {
            System.out.println(" [Problème à partir de " + i + "]");
            System.exit(0);
        }
    }
}

    // #### 3.1-2)
static int imini(int[] t, int p) {
    int imini = p;
    for (int i = p; i < t.length; ++i) {
        if (t[i] < t[imini]) {
            imini = i;
        }
    }
    return imini;
}

    public static void main(String[] argv) {
        /*int t[] = {5, 1, 4, 2, 3};
        for (int i = 0; i < t.length; ++i) {
            System.out.print(mini(t, i) + " est minimal dans ");
            System.out.print(t[imini(t, i)] + "d'indice " + imini(t, i) + " est minimal dans ");
            affiche(t, i);
        }*/
        testMini();
        testiMini();
    }


    /*public static void main(String[] args) {
        // #### 3.1-1)
        // Un objet de type Random pour tirer des nombres au hasard.
        Random random = new Random();
        int[] tableau = new int[auHasardEntre(10, 100, random)];
        remplissageAuHasard(tableau, random);
        affichage(tableau);
    }*/
}
