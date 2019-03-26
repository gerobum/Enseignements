
import equation.Equation;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yvan
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Entrez a b et c");
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        Equation equation = new Equation(a, b, c);
        if (equation.nbRacines  == 0) {
            System.out.println("Pas de solution");
        } else if (equation.nbRacines
                == 1) {
            System.out.println("Une seule racine : " + equation.x1);
        } else {
            System.out.println("Deux racines : " + equation.x1 + " et "
                    + equation.x2);
        }

    }

}
