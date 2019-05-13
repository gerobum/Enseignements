package main;

import agenda.Horaire;
import java.util.Scanner;

public class Main {
/*
3 Test (~6pts)
Dans un paquetage main, écrire une classe autonome Main.
Le programme principal de cette classe (public static void main(
String[] argv) consiste à tester votre classe Horaire de la façon sui-
vante :
    
*/
    public static void main(String[] args) {
        simpleAffichage();
        verificationMontee();
        verificationDescente();
    }
    
/*
3.1 Simple affichage (~2pts)
    
Commencer ce programme principal par :
1. Saisir au clavier deux entiers, heure et minute.
2. Créer un horaire à partir de ces deux valeurs.
3. L’afficher.
4. Recommencer tant qu’il est différent de 00h00.   
*/
    private static void simpleAffichage() {
        Scanner in = new Scanner(System.in);

        Horaire minuit = new Horaire();

        int h, m;
        System.out.print("h > ");
        h = in.nextInt();
        System.out.print("m > ");
        m = in.nextInt();
        Horaire H = new Horaire(h, m);
        H.afficher();
        while (!H.estEnMemeTemps(minuit)) {

            System.out.print("h > ");
            h = in.nextInt();
            System.out.print("m > ");
            m = in.nextInt();
            H = new Horaire(h, m);
            H.afficher();
        }
    }
 
/*
 3.2 Vérification (~4pts)
    
À la suite de la boucle précédente :
    
1. Créer h0, l’horaire 00h00.
2. Écrire une boucle pour i qui itère de 0 à 3000 et dans laquelle :
(a) Créer un horaire h1 à i minutes.
(b) Comparer h1 et h0 (ils doivent être égaux)
(c) S’ils ne sont pas égaux, sortir de la boucle et avertir de l’erreur.
(d) Sinon, augmenter h0 d’une minute.
   
*/
    public static void verificationMontee() {
        Horaire h0 = new Horaire();
        boolean erreur = false;
        int i = 0;
        while (i < 3000 && !erreur) {
            Horaire h1 = new Horaire(i);

            erreur = !h0.estEnMemeTemps(h1);

            h0.augmenterDUneMinute();
            i++;
        }
        if (!erreur) {
            System.out.println("Le test de montée s'est bien passé");
        } else {
            System.err.println("Le test de montée s'est mal passé à partir de i = " + i);
        }
    }
 
/*
3. Faire une boucle similaire qui descend de 0 à -3000.   
*/
    public static void verificationDescente() {
        Horaire h0 = new Horaire();
        boolean erreur = false;
        int i = 0;
        while (i > -3000 && !erreur) {
            Horaire h1 = new Horaire(i);

            erreur = !h0.estEnMemeTemps(h1);

            h0.diminuerDUneMinute();
            i--;
        }
        if (!erreur) {
            System.out.println("Le test de descente s'est bien passé");
        } else {
            System.err.println("Le test de descente s'est mal passé à partir de i = " + i);
        }
    }
}
