package _02_service_echo_01_serveur_echo;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/* 
Exercice: 

Par composition avec la classe java.net.ServerSocket, développer une classe 
EchoService qui implémentera le service « echo » sur le port de votre choix. 
Si un client se connecte à ce service et lui envoie une chaîne de caractères, 
le serveur devra la lui renvoyer en majuscules.

Le serveur reste connecté à un client tant que celui-ci n’envoie pas le mot 
"Fin". Il ne peut traiter qu’un client à la fois (serveur itératif).

Prévoir la possibilité au client d’arrêter le serveur à l’aide d’une phrase 
secrète. Par exemple à la réception de la chaîne "Sésame ! Ferme toi.", 
le serveur la renvoie et met définitivement fin à son service.
 */
/**
 * Le serveur "echo" se connecte à un port libre dont il affiche la valeur.
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
public class EchoService {

    public static void main(String[] args) throws IOException {

        String phrase;
        try (ServerSocket server = new ServerSocket(0)) {
            // Le serveur tourne tant que la phrase magique n'est pas prononcée.
            System.out.println("Service ECHO démarré sur le port " + server.getLocalPort());
            do {

                try (Socket client = server.accept();
                        Scanner sin = new Scanner(client.getInputStream());
                        PrintStream sout = new PrintStream(client.getOutputStream());) {
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
        }
    }
}
