/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package jeu;

/**
 *
 * @author yvan
 */
public class Test {

    private static int somme(Jeu2048 jeu) {
        int s = 0;
        for (int[] ligne : jeu.getGrille()) {
            for (int c : ligne) {
                s += c;
            }
        }
        return s;
    }

    public static void testConstructeur() {
        System.out.println("--- Test constructeur ---");
        int nb4 = 0;
        int nb2 = 0;
        int n = 0;
        for (int i = 0; i < 10000; ++i) {
            Jeu2048 jeu = new Jeu2048();
            int s = somme(jeu);
            switch (s) {
                case 4:
                    nb2 += 2;
                    break;
                case 6:
                    ++nb2;
                    ++nb4;
                    break;
                case 8:
                    nb4 += 2;
                    break;
            }
            n += 2;
        }
        System.out.println("Pourcentage de 2 " + (nb2 * 100.0) / n);
        System.out.println("Pourcentage de 4 " + (nb4 * 100.0) / n);
    }

    public static void testRandomAjout() {
        System.out.print("--- Test random ajout ---");
        for (int i = 0; i < 10000; ++i) {
            int[][] tab = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
            Random2048 r = new Random2048(tab);
            int n = 0;
            while (r.ajouter()) {
                ++n;
            }
            if (n != 16) {
                System.err.println("ERREUR Random2048.ajout : " + n);
                System.err.println("---> NOK");
                return;
            }
        }
        System.out.println(" OK");

    }

    public static void testRandomRotationHoraire() {
        System.out.println("--- Test random rotationHoraire ---");

        int[][] grille = new int[4][4];
        int i = 0;
        for (int l = 0; l < 4; ++l) {
            for (int c = 0; c < 4; ++c) {
                grille[l][c] = ++i;
            }
        }
        Jeu2048 jeu = new Jeu2048();
        jeu.setGrille(grille);
        Manip2048 manip = new Manip2048(grille);
        jeu.afficher();
        System.out.println("------------------------");
        manip.rotationHoraire();
        jeu.afficher();
        System.out.println("------------------------");
        manip.rotationHoraire();
        jeu.afficher();
        System.out.println("------------------------");
        manip.rotationHoraire();
        jeu.afficher();
        System.out.println("------------------------");
        manip.rotationHoraire();
        jeu.afficher();
        System.out.println("------------------------");
    }

    public static void testRandomRotationAntiHoraire() {
        System.out.println("--- Test random rotationAntiHoraire ---");

        int[][] grille = new int[4][4];
        int i = 0;
        for (int l = 0; l < 4; ++l) {
            for (int c = 0; c < 4; ++c) {
                grille[l][c] = ++i;
            }
        }
        Jeu2048 jeu = new Jeu2048();
        jeu.setGrille(grille);
        Manip2048 manip = new Manip2048(grille);
        jeu.afficher();
        System.out.println("------------------------");
        manip.rotationAntiHoraire();
        jeu.afficher();
        System.out.println("------------------------");
        manip.rotationAntiHoraire();
        jeu.afficher();
        System.out.println("------------------------");
        manip.rotationAntiHoraire();
        jeu.afficher();
        System.out.println("------------------------");
        manip.rotationAntiHoraire();
        jeu.afficher();
        System.out.println("------------------------");
    }

    /*public static void testDroite() {
        System.out.println("--- Test droite ---");
        Jeu2048 jeu = new Jeu2048();
        System.out.println("----new----");
        jeu.afficher();
        jeu.droite();
            System.out.println("----droite----");
        jeu.afficher();
        while (jeu.ajouter()) {
            System.out.println("-----ajout------");
            jeu.afficher();
            jeu.droite();
            System.out.println("----droite----");
            jeu.afficher();
        }
    }

    public static void testGauche() {
        System.out.println("--- Test gauche ---");
        Jeu2048 jeu = new Jeu2048();
        System.out.println("----new----");
        jeu.afficher();
        jeu.gauche();
            System.out.println("----gauche----");
        jeu.afficher();
        while (jeu.ajouter()) {
            System.out.println("-----ajout------");
            jeu.afficher();
            jeu.gauche();
            System.out.println("----gauche----");
            jeu.afficher();
        }
    }

    public static void testHaut() {
        System.out.println("--- Test haut ---");
        Jeu2048 jeu = new Jeu2048();
        System.out.println("----new----");
        jeu.afficher();
        jeu.haut();
            System.out.println("----haut----");
        jeu.afficher();
        while (jeu.ajouter()) {
            System.out.println("-----ajout------");
            jeu.afficher();
            jeu.haut();
            System.out.println("----haut----");
            jeu.afficher();
        }
    }

    public static void testBas() {
        System.out.println("--- Test bas ---");
        Jeu2048 jeu = new Jeu2048();
        System.out.println("----new----");
        jeu.afficher();
        jeu.bas();
            System.out.println("----bas----");
        jeu.afficher();
        while (jeu.ajouter()) {
            System.out.println("-----ajout------");
            jeu.afficher();
            jeu.bas();
            System.out.println("----bas----");
            jeu.afficher();
        }
    }*/
    public static void main(String[] args) {
        testConstructeur();
        testRandomAjout();
        testRandomRotationHoraire();
        testRandomRotationAntiHoraire();
        /*testGauche();
        testDroite();
        testHaut();
        testBas();*/
    }
}
