/*
 * Pour illustrer les blocs synchronis�s
 */
package demonstration._02_synchronisation;

import java.util.Scanner;

/**
 * Dans DoubleLecture, deux lecteurs concurrents lisent des messages au clavier
 * et �crivent ce qu'ils ont lu dans la console. Comme ils se partagent la m�me
 * console, ils peuvent se m�langer les pinceaux s'ils sont mal synchronis�s.
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

                System.out.println(nom + " demande � lire");
                System.out.print("> ");
                un_message = clavier.next();

                System.out.println(un_message + " a �t� lu par " + nom);
            } while (!"fin".equalsIgnoreCase(un_message));
        } /**/
 /* 2 
        @Override
        public void run() {
            String un_message;
            do {
                synchronized (DoubleLecture.this) {
                    System.out.println(nom + " demande � lire");
                    System.out.print("> ");
                    un_message = clavier.next();
                }
                System.out.println(un_message + " a �t� lu par " + nom);
            } while (!"fin".equalsIgnoreCase(un_message));
        }/* */
 /* 3 (toujours le m�me qui demande) 
        @Override
        public void run() {
            String un_message;
            do {
                synchronized (DoubleLecture.this) {
                    System.out.println(nom + " demande � lire");
                    System.out.print("> ");
                    un_message = clavier.next();
                    System.out.println(un_message + " a �t� lu par " + nom);
                }
            } while (!"fin".equalsIgnoreCase(un_message));
        }/* */
 /* 4 (oui mais non) 
        @Override
        public void run() {
            String un_message;
            do {
                synchronized (DoubleLecture.this) {
                    System.out.println(nom + " demande � lire");
                    System.out.print("> ");
                    un_message = clavier.next();
                    System.out.println(un_message + " a �t� lu par " + nom);
                }
                try {
                    sleep(0);
                } catch (InterruptedException ex) {}
            } while (!"fin".equalsIgnoreCase(un_message));
        }/* */
    }
}
