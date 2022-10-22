package _02_service_echo_04_echo_UDP;

import java.io.IOException;
import java.net.*;

/*
 * Le serveur Echo en utilisant le protocole UDP.
 *
 * @author maillot
 */
public class EchoService {

    /*
     * La communication se fait par envois et réceptions de datagramms. Le
     * serveur commmence par attendre de recevoir un datagramm et en renvoie un
     * autre au destinataire.<br>
     *
     * @param ds un DatagramSocket connecté au port 1414 pour envoyer des
     * datagramms.
     *
     * @throws IOException
     */
    private static boolean receiveAndSend(DatagramSocket ds) throws IOException {
        // Réception 
        byte[] tampon = new byte[256];
        DatagramPacket dp = new DatagramPacket(tampon, tampon.length);
        // En attente de réception d'un paquet
        ds.receive(dp);
        // Paquet reçu
        
        // Qui l'a envoyé ?
        int distantPort = dp.getPort(); 
        InetAddress distantAddress = dp.getAddress();
        // Quelle information contient il ?
        String message = new String(tampon);

        // Pour enlever les éventuels caractères zéro.
        //int pos0 = message.indexOf(0);
        //message = message.substring(0, pos0 >= 0 ? pos0 : tampon.length);
            
        // Envoi
        // on construit le paquet a envoyer
        tampon = message.toUpperCase().getBytes();

        // Retour à l'envoyeur (il faut bien sûr "qu'en face" l'envoyeur attende
        // le retour avec un "receive()".
        dp = new DatagramPacket(tampon, tampon.length, distantAddress, distantPort);
        ds.send(dp);

        return !"Sésame ! Ferme toi.".equals(message);
    }

    public static void main(String[] args) throws SocketException, IOException {
        DatagramSocket ds = new DatagramSocket(1414);

        System.out.println("Démarrage du service");
        while (receiveAndSend(ds)) {}
        System.out.println("Fin du service");
    }
}
