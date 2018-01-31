/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package part2.exo06;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author yvan
 */
public class MasterMind {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String aDeviner, proposition, resultat = "";
        int np, nc, nb;
        String[][] propositions;
        int n;        
        
        System.out.print("En combien de coups : ");
        np = in.nextInt();
        System.out.print("Combien de couleurs : ");
        nc = in.nextInt();
        System.out.print("Taille d'une proposition : ");
        nb = in.nextInt();
        
        propositions = initPropositions(np, nb);

        n = 0; // Numéro de la proposition
        aDeviner = aDeviner(nc, nb);
        while (n < propositions.length  && !"4BP 0MP".equals(resultat)) {
            affichage(propositions);
            proposition = saisirProposition(nc, nb);
            resultat = compare(proposition, aDeviner);
            propositions[n][0] = proposition;
            propositions[n][1] = resultat;
            ++n;
        }   
        if (n < propositions.length) {
            System.out.println("Gagné");
        } else {
            System.out.println("Perdu");
            System.out.println("La solution était " + aDeviner);
        }
    }

    private static String aDeviner(int nc, int nb) {
        Random random = new Random();
        char[] tc = new char[nb];
        for (int i = 0; i < tc.length; ++i) {
            tc[i] = unCar(random.nextInt(nc));
        }
        return new String(tc);
    }

    private static char unCar(int i) {
        return (char) ('A' + i);
    }

    private static String saisirProposition(int nc, int nb) {
        System.out.println("Entrez " + nb + " caractères entre A et " + unCar(nc - 1));
        Scanner in = new Scanner(System.in);
        String proposition = in.next();
        while (!estValide(proposition, nc, nb)) {
            System.out.println("Entrez " + nb + " caractères entre A et " + unCar(nc - 1));
            proposition = in.next();
        }
        return proposition;
    }

    private static boolean estValide(String proposition, int nc, int nb) {
        if (proposition.length() != nb) {
            return false;
        } else {
            for (int i = 0; i < proposition.length(); ++i) {
                if (proposition.charAt(i) < 'A' || proposition.charAt(i) >= ('A' + nc)) {
                    return false;
                }
            }
            return true;
        }
    }

    private static String compare(String proposition, String aDeviner) {
        int nbp = 0;
        int nmp = 0;
        char[] tabProposition = proposition.toCharArray();
        char[] tabADeviner  = aDeviner.toCharArray();
        for (int i = 0; i < tabProposition.length; ++i) {
            if (tabProposition[i] == tabADeviner[i]) {
                nbp++;
                tabADeviner[i] = '-';
                tabProposition[i] = '+';
            }
        }
        
        for (int i = 0; i < tabProposition.length; ++i) {
            int j = indexDe(tabProposition[i], tabADeviner);
            if (j >= 0) {
                tabADeviner[j] = '-';
                nmp++;
            }
        }
        return String.format("%dBP %dMP", nbp, nmp);
    }

    private static void affichage(String[][] propositions) {
        for(int l = 0; l < propositions.length; ++l) {
            System.out.println(propositions[l][0] + " - " + propositions[l][1]);
        }
    }

    private static String[][] initPropositions(int np, int nb) {
        char[] tc = new char[nb];
        for(int i = 0; i < nb; ++i) {
            tc[i] = '.';
        }
        String s = new String(tc);
        String[][] propositions = new String[np][2];
        for (int i = 0; i < np; ++i) {
            propositions[i][0] = s;
            propositions[i][1] = ".BP .MP";
        }
        return propositions;
    }

    private static int indexDe(char c, char[] aDeviner) {
        int i = aDeviner.length-1;
        while(i >= 0 && c != aDeviner[i])
            --i;
        return i;
    }

}
