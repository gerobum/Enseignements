/*
 * Pour illustrer les blocs synchronisés
 */
package demonstration._02_synchronisation;

import java.util.Scanner;

/**
 * Dans DoubleLecture, deux lecteurs concurrents lisent des messages au clavier
 * et écrivent ce qu'ils ont lu dans la console. Comme ils se partagent la même
 * console, ils peuvent se mélanger les pinceaux s'ils sont mal synchronisés.
 *
 * @author maillot
 */
public class DoubleLecture {

    private final Lecteur p, q;

    public DoubleLecture() {
        p = new Lecteur("P");
        q = new Lecteur("Q");
    }

    public void start() {
        p.start();
        q.start();
    }

    public static void main(String[] args) {
        new DoubleLecture().start();
    }

    /**
     * Un lecteur lit au clavier et affiche ce qu'il a lu.
     */
    private class Lecteur extends Thread {

        private final Scanner clavier = new Scanner(System.in);
        private final String nom;

        public Lecteur(String nom) {
            this.nom = nom;
        }
        
 
        /* 1 */
        @Override
        public void run() {
            String un_message;
            do {

                System.out.println(nom + " demande à lire");
                System.out.print("> ");
                un_message = clavier.next();

                System.out.println(un_message + " a été lu par " + nom);
            } while (!"fin".equalsIgnoreCase(un_message));
        } /**/
 /* 2 
        @Override
        public void run() {
            String un_message;
            do {
                synchronized (DoubleLecture.this) {
                    System.out.println(nom + " demande à lire");
                    System.out.print("> ");
                    un_message = clavier.next();
                }
                System.out.println(un_message + " a été lu par " + nom);
            } while (!"fin".equalsIgnoreCase(un_message));
        }/* */
 /* 3 (toujours le même qui demande) 
        @Override
        public void run() {
            String un_message;
            do {
                synchronized (DoubleLecture.this) {
                    System.out.println(nom + " demande à lire");
                    System.out.print("> ");
                    un_message = clavier.next();
                    System.out.println(un_message + " a été lu par " + nom);
                }
            } while (!"fin".equalsIgnoreCase(un_message));
        }/* */
 /* 4 (oui mais non) 
        @Override
        public void run() {
            String un_message;
            do {
                synchronized (DoubleLecture.this) {
                    System.out.println(nom + " demande à lire");
                    System.out.print("> ");
                    un_message = clavier.next();
                    System.out.println(un_message + " a été lu par " + nom);
                }
                try {
                    sleep(0);
                } catch (InterruptedException ex) {}
            } while (!"fin".equalsIgnoreCase(un_message));
        }/* */
    }
}
