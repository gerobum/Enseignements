package _02_service_echo_04_echo_UDP;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Le serveur Echo en utilisant le protocole UDP.
 *
 * @author maillot
 */
public class EchoServiceThread extends Thread {

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
    private boolean receiveAndSend(DatagramSocket ds) throws IOException {
        // Réception 
        byte[] tampon = new byte[256];
        DatagramPacket dp = new DatagramPacket(tampon, tampon.length);

        ds.receive(dp);
        int distantPort = dp.getPort();
        InetAddress distantAddress = dp.getAddress();
        String message = new String(tampon);

        // Pour enlever les éventuels caractères zéro.
        int pos0 = message.indexOf(0);
        message = message.substring(0, pos0 >= 0 ? pos0 : tampon.length);
            
        // Envoi
        // on construit le paquet a envoyer
        tampon = message.toUpperCase().getBytes();

        dp = new DatagramPacket(tampon, tampon.length, distantAddress, distantPort);
        ds.send(dp);

        return !"Sésame ! Ferme toi.".equals(message);
    }

    @Override
    public void run() {
        try {
            DatagramSocket ds = new DatagramSocket(1414);            
            System.out.println("Démarrage du service");
            while (receiveAndSend(ds)) {}
            System.out.println("Fin du service");
        } catch (SocketException ex) {
        } catch (IOException ex) {
        }
    }
    
    public static void main(String[] args) {
        new EchoServiceThread().start();
    }
}
