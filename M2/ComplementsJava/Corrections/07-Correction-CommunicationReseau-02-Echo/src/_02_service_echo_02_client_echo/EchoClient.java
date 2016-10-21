package _02_service_echo_02_client_echo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Envoie des requêtes au serveur echo sur le port 1414.<br>
 *
 * @author maillot
 */
public class EchoClient extends Thread {
    // private Socket client;
    
    @Override
    public void run() {
        // Remarque : le placement de la Socket client dans un try-avec-ressource
        // empêche celle-ci d'être un attribut de la classe.
        try(Scanner cin = new Scanner(System.in);
            Socket client = new Socket("localhost", 1414);
            Scanner sin = new Scanner(client.getInputStream());
            PrintWriter sout = new PrintWriter(client.getOutputStream());
           ) {            
            String phrase;
            // Une fois connecté avec le serveur, le client reste en communication
            // avec lui tant qu'il le décide.
            // Il peut même stopper le serveur s'il connait la phrase secrète 
            do {
                System.out.print("> ");
                phrase = cin.nextLine();
                sout.println(phrase);
                sout.flush();
                System.out.println(sin.nextLine());
            } while (!phrase.equalsIgnoreCase("FIN") && !"Sésame ! Ferme toi.".equals(phrase));
        } catch (IOException ex) {
            System.err.println(" ---------------------------> Le serveur semble fermé !!!!!!!");
        }
    }

    public static void main(String[] args) throws IOException {
        new EchoClient().start();
    }
}
