package _02_service_echo_02_client_echo;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

/*
 * Envoie des requêtes au serveur echo sur le port saisi au clavier.
 * Par composition avec la classe java.net.Socket, développer une classe 
 * EchoClient implémentant un client du service « echo ». Ce client connecté au 
 * serveur « echo » doit permettre à un utilisateur de saisir des lignes de 
 * texte depuis la console et d’afficher celles reçues du serveur.

 * @author maillot
 */
public class EchoClient {

    public static void main(String[] args) {
        try (Scanner cin = new Scanner(System.in)) {
            // Lecture au clavier du port de connexion du serveur
            System.out.print("Entrez le port du serveur ECHO : ");
            int port = cin.nextInt();
            cin.nextLine();
            // Tentative de connexion avec le serveur
            try (Socket client = new Socket("localhost", port);                    
                    Scanner sin = new Scanner(client.getInputStream());
                    // PrintWriter "n'est pas autoflush" par défaut
                    PrintWriter sout = new PrintWriter(client.getOutputStream());
                    /* On peut demander l'autoflush ainsi
                    PrintWriter sout = new PrintWriter(client.getOutputStream(), true);
                    // Ou alors PrintStream est autoflush par défaut
                    // PrintStream sout = new PrintStream(client.getOutputStream(), true);
                    */) {
                // La connexion est acceptée
                String phrase;
                // Une fois connecté avec le serveur, le client reste en communication
                // avec lui tant qu'il le décide.
                // Il peut même stopper le serveur s'il connait la phrase secrète 
                do {
                    System.out.print("> ");
                    phrase = cin.nextLine();
                    sout.println(phrase);
                    sout.flush(); // En l'absence d'autoflush
                    System.out.println(sin.nextLine());
                } while (!phrase.equalsIgnoreCase("FIN") && !"Sésame ! Ferme toi.".equals(phrase));
            } catch (IOException | NoSuchElementException ex) {
                // La tentative de connexion a échoué, le serveur doit être fermé.
                System.err.println(" ---------------------------> Le serveur semble fermé !!!!!!!");
            }
        }
    }
}
