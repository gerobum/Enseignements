package _01_service_horaire_01_serveur_qui_donne_le_jour_et_lheure;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/*
 * Cet exercice n'était pas demandé. Mais j'ai rajouté un client qui (simplement)
 * 
 * @author maillot
 */
public class DayTimeClientSimple {

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {

        for (int i = 0; i <= 10; i++) {
            try (Socket socket = new Socket("localhost", 1313)) {
                Scanner dis = new Scanner(socket.getInputStream());
                System.out.println(dis.nextLine());
                Thread.sleep(100);
            }
        }
    }
}
