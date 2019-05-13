/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package partie;

import frame.Frame;
import jeu.Jeu2048;

/**
 * 
 * @author yvan
 */
public class Partie {

    public static void main(String[] args) {
        Frame frame = new Frame();
        Jeu2048 jeu = new Jeu2048();
        while (!jeu.gagne() && !jeu.perdu()) {
            frame.setGrille(jeu.getGrille());
            switch (frame.saisie()) {
                case 'H':
                    jeu.haut();
                    System.out.println("HAUT");
                    break;
                case 'B':
                    jeu.bas();
                    System.out.println("BAS");
                    break;
                case 'G':
                    jeu.gauche();
                    System.out.println("GAUCHE");
                    break;
                case 'D':
                    jeu.droite();
                    System.out.println("DROITE");
                    break;
            }
        }
        if (jeu.gagne()) {
            System.out.println("BRAVO");
             frame.afficheVictoire();
            frame.afficheVictoire();
        } else {
            System.out.println("DOMMAGE");
            frame.afficheDefaite();
        }
        frame.setGrille(jeu.getGrille());
    }
}
