/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package jeu;


/**
 *
 * @author yvan
 */
public class Jeu2048 {

    private int[][] jeu;

    public Jeu2048() {
        jeu = new int[4][4];
    }

    public int[][] getGrille() {
        return jeu;
    }

    public void setGrille(int[][] jeu) {
        this.jeu = jeu;
    }

    public void gauche() {
    }

    public void droite() {
    }

    public void haut() {
    }

    public void bas() {
    }

    public boolean gagne() {
        return false;
    }

    public boolean perdu() {
        return false;
    }

}
