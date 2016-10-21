package _01_service_horaire_03_envoi_dobjets;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Le même service que la classe DayTimeService du paquetage exo1 à une variante
 * près : un objet de type Date est envoyé plutôt qu'une chaîne. Bien entendu,
 * un navigateur ne pourra pas interpréter cet objet. Il faut maintenant un
 * client pour le faire.
 *
 * @author maillot
 */
public class DayTimeService {

    public static void main(String[] args) throws InterruptedException {
        try {
            System.out.println("Service démarré");
            ServerSocket serverSocket = new ServerSocket(1313);

            for (int i = 0; i < 10; i++) {
                System.out.println("Attente d'une requête");

                try (Socket socket = serverSocket.accept();
                        ObjectOutputStream pout = new ObjectOutputStream(socket.getOutputStream());) {
                    pout.writeObject(new Date());
                    socket.shutdownOutput();
                }
            }
            // Un objet de type String est envoyé à la fin. Le client devra gérer ça.
            try (Socket socket = serverSocket.accept();
                    ObjectOutputStream pout = new ObjectOutputStream(socket.getOutputStream());) {
                pout.writeObject("Fin du service");
                socket.shutdownOutput();
            }

        } catch (IOException ex) {
            Logger.getLogger(DayTimeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
