package _01_service_horaire_01_serveur_qui_donne_le_jour_et_lheure;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Le serveur horaire offre un service simple : donner l'heure sur le port 1313.
 * Accessible depuis un navigateur internet. Pour simple illustration le service
 * se termine au bout de 10 requêtes.
 *
 * @author maillot
 */
public class DayTimeService {

    // Création d'un objet pour formatter la date à notre convenance
    private static final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM);

    public static void écrire(PrintStream pout, int i) {
        pout.print(df.format(new Date()));
        pout.println(" : " + i + "eme requête");
    }

    /*
     * Souvent un serveur est lancé dans un thread. Pour
     * aller à l'essentiel, il est ici lancé dans le "main".
     */
    public static void main(String[] args) throws InterruptedException {

        try {

            System.out.println("Service démarré");
            // Création d'un serveur de socket lié au port local numéro 1313            
            ServerSocket serverSocket = new ServerSocket(1313);

            // En toute logique, le service devrait être permanent...
            // J'ai choisi d'y mettre fin au bout de 10 requêtes.
            for (int i = 0; i < 10; i++) {
                // Création d'un flux de sortie enrobé dans un PrintStream
                // pour écrire plus facilement une chaîne de caractères.
                try ( // Le serveur se met en attente d'une requête.
                        // L'appel de accept() est donc bloquant
                        Socket socket = serverSocket.accept();
                        // Passer ce cap signifie qu'un client a fait une requête,
                        // il faut lui envoyer l'heure dans un flux de sortie.
                        // Création d'un flux de sortie enrobé dans un PrintStream
                        // pour écrire plus facilement une chaîne de caractères.
                        PrintStream pout = new PrintStream(socket.getOutputStream())) {

                    écrire(pout, i + 1);
                    socket.shutdownOutput();
                }
            }

            try ( // Le service est terminé, ce sera annoncé à la prochaine requête.
                    Socket socket = serverSocket.accept();
                    PrintStream pout = new PrintStream(socket.getOutputStream());) {
                pout.println("Fin du service");
                socket.shutdownOutput();
            }
        } catch (IOException ex) {
            Logger.getLogger(DayTimeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
