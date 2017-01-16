/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demonstration.synchronisation;

import java.util.Scanner;

/**
 *
 * @author maillot
 */
public class Attente {

    private class Lecture extends Thread {

        private Scanner clavier = new Scanner(System.in);
        private String nom;

        public Lecture(String nom) {
            this.nom = nom;
        }

        @Override
        public void run() {
            String un_message = "";
            do {
                synchronized (Attente.this) {
                    System.out.println(nom + " demande à lire");
                    System.out.print("> ");
                    un_message = clavier.next();
                }
                System.out.println(un_message + " a été lu par " + nom);
            } while (!"fin".equalsIgnoreCase(un_message));
        }
    }
    Lecture p, q;

    public Attente() {
        p = new Lecture("P");
        q = new Lecture("Q");
    }

    public void start() {
        p.start();
        q.start();
    }

    public static void main(String[] args) {
        new Attente().start();
    }
}
