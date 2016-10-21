package _02_service_echo_04_echo_UDP;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class EchoClientThread extends Thread {

    private String sendAndReceive(DatagramSocket ds, String message) throws UnknownHostException, IOException {
        byte[] tampon = message.getBytes();
        DatagramPacket dp = new DatagramPacket(tampon, tampon.length, Inet4Address.getLocalHost(), 1414);
        ds.send(dp);
        ds.receive(dp);
        String ligne = new String(tampon);
        return ligne.trim();
    }

    @Override
    public void run() {
        try {
            DatagramSocket ds = new DatagramSocket();
            String phrase;
            Scanner sin = new Scanner(System.in);
            do {
                System.out.print("> ");
                phrase = sin.nextLine();
                System.out.println(sendAndReceive(ds, phrase));
            } while (!"fin".equalsIgnoreCase(phrase) && !"Sésame ! Ferme toi.".equals(phrase));
        } catch (SocketException ex) {
        } catch (IOException ex) {
        }
    }

    public static void main(String[] args) throws SocketException, IOException {
        new EchoClientThread().start();
    }
}
