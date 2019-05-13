/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package partie;

import frame.Frame;
import jeu.Jeu2048;

/**
 * Une partie complète de 2048 avec affichage du score
 * 
 * @author yvan
 */
public class Partie {

    public static void main(String[] args) {
        Jeu2048 jeu = new Jeu2048();
        Frame frame = new Frame();
        int score = 0;
        while (!jeu.gagne() && !jeu.perdu()) {
            frame.setScore(score);
            frame.setGrille(jeu.getGrille());

            switch (frame.saisie()) {
                case 'H':
                    score += jeu.haut();
                    break;
                case 'B':
                    score += jeu.bas();
                    break;
                case 'G':
                    score += jeu.gauche();
                    break;
                case 'D':
                    score += jeu.droite();
                    break;
            }
        }
        if (jeu.gagne()) {
            System.out.println("BRAVO");
            frame.afficheVictoire();
        } else {
            System.out.println("DOMMAGE");
            frame.afficheDefaite();
        }
        frame.setGrille(jeu.getGrille());
    }
}
