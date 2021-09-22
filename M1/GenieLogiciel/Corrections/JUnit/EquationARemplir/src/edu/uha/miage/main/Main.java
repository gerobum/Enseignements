package edu.uha.miage.main;

import edu.uha.miage.equation.AbstractEquation;
import edu.uha.miage.equation.Equation;
import java.util.Scanner;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez a b et c");
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        AbstractEquation equation = new Equation(a, b, c);
        switch (equation.getRootsCount()) {
            case 0:
                System.out.println("Pas de solution");
                break;
            case 1:
                System.out.println("Une seule racine : " + equation.getX1());
                break;
            default:
                System.out.println("Deux racines : " + equation.getX1() + " et "
                        + equation.getX2());
                break;
        }
    }

}
