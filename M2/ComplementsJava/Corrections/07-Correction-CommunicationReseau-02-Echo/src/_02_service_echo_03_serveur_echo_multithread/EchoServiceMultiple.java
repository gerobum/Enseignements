package _02_service_echo_03_serveur_echo_multithread;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

/*
 * Serveur ECHO qui admet les multiples connexions simultannées.
 * @author maillot
 */
public class EchoServiceMultiple {

    // stop == true signifie l'arrêt du serveur.
    private static boolean stop = false;

    /**
     * Le serveur est multi-services. Une communication avec un client
     * est un service.
     */
    private static class Service extends Thread {

        private final Socket client;
        private Scanner sin;
        private PrintStream sout;

        /**
         * Un service est lancée quand le serveur accepte la connexion avec un
         * client. 
         * @param client : la socket de communication avec le client
         */
        Service(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                String mot;
                // Le service est traité dans un thread. Il se termine quand le 
                // client le décide.               
                sin = new Scanner(client.getInputStream());
                sout = new PrintStream(client.getOutputStream());
                do {
                    mot = sin.nextLine();
                    sout.println(mot.toUpperCase());
                } while (!stop && !mot.equalsIgnoreCase("FIN") && !mot.equals("Sésame ! Ferme toi."));
                // Comme le drapeau stop ne concerne pas seulement le service
                // mais le serveur (donc tous les services), une fois rendu vrai
                // il doit le rester.
                // Si le client sort par la phrase magique (Sésame ! Ferme toi.)
                // alors le drapeau stop doit devenir ou rester vrai.
                // sinon il doit rester en l'état.
                stop = stop || mot.equals("Sésame ! Ferme toi.");
                
            } catch (IOException ex) {
            }
        }
    }

    public static void main(String[] args) throws IOException {
        /* Pour la différence entre EchoServiceMultiple et EchoService :
         * 1) Lancer un serveur EchoService et deux EchoClient
         *    Vous verrez que seul l'un des deux fonctionne. Le premier qui
         *    a "la main" doit terminer son service (en tapant FIN) pour que
         *    le seconde puisse fonctionner.
         * 2) Mettre fin à EchoService, lancer EchoServiceMultiple et autant de
         *    de clients que voulu. Tous fonctionneront indépendemment les uns
         *    des autres.
         */
        
        // 
        try (ServerSocket server = new ServerSocket(0)) {
            System.out.println("Service echo démarré sur le port " + server.getLocalPort());
            while (!stop) {
                // Le timeout sert à quitter régulièrement l'attente pour vérifier
                // l'état du drapeau stop.
                server.setSoTimeout(1000);
                try {
                    Socket client = server.accept();
                    new Service(client).start();
                } catch (SocketTimeoutException s) {
                }
            }
        }

        System.out.println("Service echo terminé");
    }
}
