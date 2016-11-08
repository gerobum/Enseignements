package main;

import cartes.JeuDeBelote;
import interfaces.Compartimenteur;

/**
 *
 * @author yvan
 */
public class Main {
    public static void main(String[] args) {
        JeuDeBelote jeu = new JeuDeBelote();
        jeu.melanger();
        System.out.println("Jeu mélangé");
        System.out.println(jeu);
        jeu.trier();
        System.out.println("Jeu complètement trié");
        System.out.println(jeu);
        Compartimenteur compartimenteur = new Compartimenteur() {
            @Override
            public int getNumberOfCompartiments(Object o) {
                return 4;
            }

            @Override
            public int getCompartiment(Object o) {
                return ((JeuDeBelote.Carte)o).I_COULEUR;
            }
        };
        jeu.melanger();
        System.out.println("Jeu mélangé");
        System.out.println(jeu);
        jeu.trier(compartimenteur);
        System.out.println("Jeu trié partiellement, par couleurs");
        System.out.println(jeu);
        compartimenteur = new Compartimenteur() {
            @Override
            public int getNumberOfCompartiments(Object o) {
                return 8;
            }

            @Override
            public int getCompartiment(Object o) {
                return ((JeuDeBelote.Carte)o).I_FIGURE;
            }
        };
        jeu.melanger();
        System.out.println("Jeu mélangé");
        System.out.println(jeu);
        jeu.trier(compartimenteur);
        System.out.println("Jeu trié partiellement, par figures");
        System.out.println(jeu);
    }    
}
