/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package jeu;

import java.util.Random;
/**
 * Classe de haut niveau pour manipuler le jeu 2048.
 * 
 * Permet d'afficher l'interface et d'interagir avec l'utilisateur
 * 
 * Des classes dédiées de plus bas niveaux sont présentes dans ce fichier.
 * 
 * @author yvan
 */
public class Jeu2048 {

    private int[][] jeu;
    private final Random2048 R;
    private final Manip2048 M;
    private final Info2048 I;

    /**
     * Créer un jeu vide avant de rajouter deux nombres.
     */
    public Jeu2048() {
        jeu = new int[4][4];
        R = new Random2048(jeu);
        M = new Manip2048(jeu);
        I = new Info2048(jeu);
        R.ajouter();
        R.ajouter();
    }

    /**
     * Pour afficher sur la sortie standard
     */
    public void afficher() {
        for (int[] ligne : jeu) {
            for (int c : ligne) {
                System.out.print(String.format(" %4d", c));
            }
            System.out.println();
        }
    }

    /**
     * Pour accéder à la grille
     * @return la grille
     */
    public int[][] getGrille() {
        return jeu;
    }

    /**
     * Pour modifier la grille
     * @param jeu 
     */
    public void setGrille(int[][] jeu) {
        this.jeu = jeu;
    }

    /**
     * Déplacement à gauche
     * @return le score
     */
    public int gauche() {
        int score = M.gauche();
        if (M.aChange()) {
            ajouter();
        }
        return score;
    }

    /**
     * Déplacement à droite
     * @return le score
     */
    public int droite() {
        int score = M.droite();
        if (M.aChange()) {
            ajouter();
        }
        return score;
    }

    /**
     * Déplacement vers le haut
     * @return le score
     */
    public int haut() {
        int score = M.haut();
        if (M.aChange()) {
            ajouter();
        }
        return score;
    }

    /**
     * Déplacement vers le bas
     * @return le score
     */
    public int bas() {
        int score = M.bas();
        if (M.aChange()) {
            ajouter();
        }
        return score;
    }

    /**
     * Pour ajouter un nombre au hasard dans la grille
     * @return 
     */
    private boolean ajouter() {
        return R.ajouter();
    }

    /**
     * Pour tester si c'est gagné
     * @return vrai si gagné faux sinon
     */
    public boolean gagne() {
        return I.gagne();
    }

    /**
     * Pour tester si c'est perdu
     * @return vrai si perdu faux sinon
     */
    public boolean perdu() {
        return I.perdu();
    }

}


/**
 * Classe utilisée pour extraire des informations de la grille.
 * 
 * @author maillot
 */
class Info2048 {

    private final int[][] grille;

    public Info2048(int[][] grille) {
        this.grille = grille;
    }

    /**
     * Compte le nombre de zéros (dont de places libres)
     * @return le nombre de places libres
     */
    private int nbZeros() {
        int n = 0;
        for (int[] ligne : grille) {
            for (int c : ligne) {
                if (c == 0) {
                    ++n;
                }
            }
        }
        return n;
    }

    /**
     * Remplit un tableau de toutes les positions libres
     * @return le tableau de toutes les positions libres
     */
    public Position[] getLibres() {
        int n = nbZeros();
        Position[] libres = new Position[n];
        int i = 0;
        for (int l = 0; l < grille.length; ++l) {
            for (int c = 0; c < grille[l].length; ++c) {
                if (grille[l][c] == 0) {
                    libres[i++] = new Position(l, c);
                }
            }
        }
        return libres;
    }

    public boolean gagne() {
        for (int[] ligne : grille) {
            for (int c : ligne) {
                if (c >= 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean perdu() {
        if (gagne() || nbZeros() > 0) {
            return false;
        }
        for (int l = 0; l < 4; ++l) {
            for (int c = 0; c < 4; ++c) {
                if (memeVoisin(l, c)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean memeVoisin(int l, int c) {

        try {
            if (grille[l][c] == grille[l][c+1]) {
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        try {
            if (grille[l][c] == grille[l][c-1]) {
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        try {
            if (grille[l][c] == grille[l+1][c]) {
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        try {
            if (grille[l][c] == grille[l-1][c]) {
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        return false;
    }
}

class Manip2048 {

    private final int[][] grille;
    private final Info2048 I;
    private boolean changement = false;

    public boolean aChange() {
        return changement;
    }

    public Manip2048(int[][] grille) {
        this.grille = grille;
        this.I = new Info2048(grille);
    }
    
    public void demiTour() {
        for (int l = 0; l < 4; ++l) {
            swap(l, 0, 3);
            swap(l, 1, 2);
        }
    }

    public void rotationHoraire() {
        int v = grille[0][0];
        grille[0][0] = grille[3][0];
        grille[3][0] = grille[3][3];
        grille[3][3] = grille[0][3];
        grille[0][3] = v;
        v = grille[1][0];
        grille[1][0] = grille[3][1];
        grille[3][1] = grille[2][3];
        grille[2][3] = grille[0][2];
        grille[0][2] = v;
        v = grille[2][0];
        grille[2][0] = grille[3][2];
        grille[3][2] = grille[1][3];
        grille[1][3] = grille[0][1];
        grille[0][1] = v;
        v = grille[1][1];
        grille[1][1] = grille[2][1];
        grille[2][1] = grille[2][2];
        grille[2][2] = grille[1][2];
        grille[1][2] = v;
    }

    public void rotationAntiHoraire() {
        int v = grille[0][0];
        grille[0][0] = grille[0][3];
        grille[0][3] = grille[3][3];
        grille[3][3] = grille[3][0];
        grille[3][0] = v;
        v = grille[0][1];
        grille[0][1] = grille[1][3];
        grille[1][3] = grille[3][2];
        grille[3][2] = grille[2][0];
        grille[2][0] = v;
        v = grille[0][2];
        grille[0][2] = grille[2][3];
        grille[2][3] = grille[3][1];
        grille[3][1] = grille[1][0];
        grille[1][0] = v;
        v = grille[1][1];
        grille[1][1] = grille[1][2];
        grille[1][2] = grille[2][2];
        grille[2][2] = grille[2][1];
        grille[2][1] = v;
    }

    private void swap(int l, int a, int b) {
        int v = grille[l][a];
        grille[l][a] = grille[l][b];
        grille[l][b] = v;
    }

    private void ligneGauche(int l) {
        int g = 0;
        while (g < 4) {
            int d = g;
            while (d < 4 && grille[l][d] == 0) {
                ++d;
            }
            if (d < 4) {
                changement = changement || grille[l][g] != grille[l][d];
                swap(l, g, d);
            }
            ++g;
        }

    }

    private int compresse(int l) {
        int c = 0;
        int score = 0;
        while (c < 3) {
            if (grille[l][c] == grille[l][c + 1]) {
                grille[l][c] *= 2;
                score += grille[l][c];
                grille[l][c + 1] = 0;
                c += 2;
            } else {
                ++c;
            }
        }
        return score;
    }

    public int gauche() {
        changement = false;
        int score = 0;
        for (int l = 0; l < 4; ++l) {
            ligneGauche(l);
            score += compresse(l);
            ligneGauche(l);
        }
        return score;
    }

    public int droite() {
        demiTour();
        int score = gauche();
        demiTour();
        return score;
    }

    public int haut() {
        rotationAntiHoraire();
        int score = gauche();
        rotationHoraire();
        return score;
    }

    public int bas() {
        rotationHoraire();
        int score = gauche();
        rotationAntiHoraire();
        return score;
    }
}

/**
 * Classe utile à la génération des valeurs aléatoires dans la grille
 * @author maillot
 */
class Random2048 {

    private final int[][] grille;
    private static final Random R = new Random();
    private final Info2048 I;

    public Random2048(int[][] grille) {
        this.grille = grille;
        this.I = new Info2048(grille);
    }

    private int next() {
        return R.nextInt(15) == 0 ? 4 : 2;
    }

    public boolean ajouter() {
        Position[] libres = I.getLibres();
        if (libres.length > 0) {
            Position p = libres[R.nextInt(libres.length)];
            if (grille == null) {
                System.err.println("null");
            } else {
               
                grille[p.l][p.c] = next();
            }
            return true;
        }
        return false;
    }
}