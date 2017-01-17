/*
Écrire une classe Afficheur qui contient une méthode public static void printfSimplifie
qui attend en paramètre une chaîne de caractères (message) et une liste variable d’entiers (li). La
chaîne de caractères est censée contenir des % (autant de % que d’entiers dans li). Cette méthode
affiche le message en remplaçant tous les % par sa valeur correspondante dans li.
Ainsi l’appel suivant printfSimplifie("Le mois numéro % compte % jours", 1, 31) ;
affichera Le mois numéro 1 compte 31 jours
printfSimplifie("% x % = %", a, b, a*b) ; dans une boucle pourrait afficher une table de
multiplications.
Indice : voir la classe Scanner

 */
package exo4;

import java.util.Scanner;

/**
 *
 * @author yvan
 */
public class Afficheur {

    public static void printfSimplifie(String message, int... li) {
        Scanner s = new Scanner(message);
        s.useDelimiter("%");
        int i = 0;
        if (message.charAt(0) == '%') {
            System.out.print(li[i++]);
        }
        System.out.print(s.next());
        while (s.hasNext()) {
            System.out.print(li[i++]);
            System.out.print(s.next());
        }
        if (message.charAt(message.length()-1) == '%') {
            System.out.print(li[i++]);
        }
    }
}
