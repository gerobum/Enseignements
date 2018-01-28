/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exo06;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author yvan
 */
public class MasterMind {

    public static void main(String[] args) {
        char[] aDeviner, proposition;
        boolean trouvé = false;
        boolean auBout = false;
        for (int i = 0; i < 20; ++i) {
            aDeviner = aDeviner(4, 4);
        }
        aDeviner = aDeviner(4, 4);
        while (!trouvé && !auBout) {
            proposition = saisirProposition(4, 4);
            compare(proposition, aDeviner);
        }
    }

    private static char[] aDeviner(int nc, int n) {
        Random random = new Random();
        char[] tc = new char[n];
        for (int i = 0; i < tc.length; ++i) {
            tc[i] = unCar(random.nextInt(nc));
        }
        return tc;
    }

    private static char unCar(int i) {
        return (char) ('A' + i);
    }

    private static char[] saisirProposition(int nc, int n) {
        System.out.println("Entrez 4 caractères entre A et " + unCar(n - 1));
        Scanner in = new Scanner(System.in);
        String proposition = in.next();
        while (!estValide(proposition, nc, n)) {
            System.out.println("Entrez " + nc + " caractères entre A et " + unCar(n - 1));
            proposition = in.next();
        }
        return proposition.toCharArray();
    }

    private static boolean estValide(String proposition, int nc, int n) {
        if (proposition.length() != nc) {
            return false;
        } else {
            for (int i = 0; i < proposition.length(); ++i) {
                if (proposition.charAt(i) < 'A' || proposition.charAt(i) >= ('A' + n)) {
                    return false;
                }
            }
            return true;
        }
    }

    private static String compare(char[] proposition, char[] aDeviner) {
        int nbp = 0;
        int nmp = 0;
        for (int i = 0; i < proposition.length; ++i) {
            if (proposition[i] == aDeviner[i]) {
                nbp++;
                aDeviner[i] = '-';
                proposition[i] = '+';
            }
        }
        String sp = new String(proposition);
        for (char c : proposition) {
            int i = sp.indexOf(c);
            if (i >= 0) {
                nmp++;
                aDeviner[i] = '-';
            }
        }
        return String.format("NBP:%d - NMP:%d", nbp, nmp);
    }

}
