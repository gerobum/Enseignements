package page_1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean encore = true;
        Scanner clavier = new Scanner(System.in);
        Jour jour = Jour.LUNDI;
        do {
            System.out.print("Un jour ? ");
            String j = clavier.next().toUpperCase();
            try {
                jour = Jour.valueOf(j);
                encore = false;
            } catch (IllegalArgumentException iae) {
                System.err.println("Lundi ou mardi ou ...");
            }
        } while (encore);
        switch (jour) {//Le nom pleinement qualifié n’est pas requis dans une structure de contrôle switch
            case LUNDI:
                System.out.println("Ca va comme un lundi");
                break;
            case MARDI:
            case MERCREDI:
            case JEUDI:
                System.out.println("Boulot, boulot");
                break;
            case VENDREDI:
                System.out.println("C’est bientôt fini");
                break;
            case SAMEDI:
            case DIMANCHE:
                System.out.println("C’est cool");
                break;
        }
    }
}
