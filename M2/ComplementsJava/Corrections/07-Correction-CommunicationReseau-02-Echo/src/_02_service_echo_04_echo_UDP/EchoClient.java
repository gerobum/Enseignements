package _02_service_echo_04_echo_UDP;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * Idem que exo4.EchoClient mais avec le protocole UDP
 *
 * @author maillot
 */
public class EchoClient {

    // Méthode qui envoie le message en paramètre sur le port 1414 de la machine locale.
    private static String sendAndReceive(DatagramSocket ds, String message) throws UnknownHostException, IOException {
        byte[] tampon = message.getBytes();
        DatagramPacket dp = new DatagramPacket(tampon, tampon.length, Inet4Address.getLocalHost(), 1414);
        ds.send(dp);
        ds.setSoTimeout(2000);
        ds.receive(dp);
        String ligne = new String(tampon);
        return ligne.trim();
    }

    // Le client envoie des messages au serveur tant qu'il le souhaite.
    public static void main(String[] args) throws SocketException, IOException {
        try {
            DatagramSocket ds = new DatagramSocket();
            String phrase;

            Scanner sin = new Scanner(System.in);
            do {
                System.out.print("> ");
                phrase = sin.nextLine();
                System.out.println(sendAndReceive(ds, phrase));
            } while (!"fin".equalsIgnoreCase(phrase) && !"Sésame ! Ferme toi.".equals(phrase));
        } catch (SocketTimeoutException ste) {
            System.err.println("Le serveur est peut être fermé, ça fait 2000 ms que j'attends sa réponse.");
        }
    }
}
