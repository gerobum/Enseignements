package _02_service_echo_01_serveur_echo;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Le serveur "echo" attend sur le port 1414 un message d'un client. Et il lui
 * répond ce même message.<br> Tant qu'un client est connecté au serveur, il
 * peut envoyer des phrases pour recevoir leur echo. Il met fin à la connection
 * en envoyant le mot FIN.<br>
 * Remarquez que si un deuxième client demande un echo alors que le premier n'a
 * pas terminé, il doit attendre que celui-ci termine.<br>
 * Un client peut même mettre fin au service s'il connaît la phrase magique :
 * Sésame ! Ferme toi.
 *
 * @author maillot
 */
public class EchoService extends Thread {
    
    private ServerSocket server;
    private final int port;

    public EchoService(int port) {
        this.port = port;
        // Surtout ne pas mettre server.accept() ici...
    }
    
    @Override
    public void run() {
        try {
            System.out.println("Démarrage du service");
            String phrase;
            server = new ServerSocket(port);
            // Le serveur tourne tant que la phrase magique n'est pas prononcée.
            do {
                
                try (Socket client = server.accept();
                     Scanner sin = new Scanner(client.getInputStream());
                     PrintStream sout = new PrintStream(client.getOutputStream());
                    ) {
                    System.out.println("Requête acceptée");                                                           
                    // Un seul client à la fois est servi car les autres clients
                    // doivent attendre que cette boucle se termine pour se connecter.
                    
                    do {
                        phrase = sin.nextLine();
                        sout.println(phrase.toUpperCase());
                    } while (!phrase.equalsIgnoreCase("FIN") && !"Sésame ! Ferme toi.".equals(phrase));
                }
            } while (!"Sésame ! Ferme toi.".equals(phrase));
            System.out.println("Fin du service");
        } catch (IOException ex) {
        }
    }
}
