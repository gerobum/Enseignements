package _02_service_echo_03_serveur_echo_multithread;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

/*
 *
 * @author maillot
 */
public class EchoServiceMultiple {

    private static boolean stop = false;

    private static class Service extends Thread {

        private final Socket client;
        private Scanner sin;
        private PrintStream sout;

        Service(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                String mot;

                sin = new Scanner(client.getInputStream());
                sout = new PrintStream(client.getOutputStream());
                do {
                    mot = sin.nextLine();
                    sout.println(mot.toUpperCase());
                } while (!mot.equalsIgnoreCase("FIN") && !mot.equals("Sésame ! Ferme toi."));
                stop = mot.equals("Sésame ! Ferme toi.");
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
        ServerSocket server = new ServerSocket(1414);
        System.out.println("Service echo démarré");
        while (!stop) {
            // Le timeout sert à quitter régulièrement l'attente pour vérifier
            // l'état du drapeau stop.
            server.setSoTimeout(1000);
            try {
                Socket client = server.accept();
                System.out.println("Requête acceptée");
                new Service(client).start();
            } catch (SocketTimeoutException s) {

            }
        }

        System.out.println("Service echo terminé");
    }
}
