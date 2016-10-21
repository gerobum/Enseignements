/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package _01_service_horaire_03_envoi_dobjets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maillot
 */
public class DayTimeClientSimple {

    private static final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM);

    public static void main(String[] args) throws InterruptedException, ClassNotFoundException  {
        Object o = null;
        boolean encore = true;
        while (encore) {
            try {
                Socket socket = new Socket("localhost", 1313);
                ObjectInputStream dis = new ObjectInputStream(socket.getInputStream());
                // C'est un objet de type Date qui est lu
                o = dis.readObject();
                Date d = (Date) o;
                System.out.println(df.format(d));
            } catch (ClassCastException ex) {
                // Problème de casting : c'est probablement parce que le serveur
                // a envoyé autre chose qu'une date (c'est son dernier message)                
                System.out.println(o);
                encore = false;
            } catch (IOException ex) {
                encore = false;
            }
            Thread.sleep(1000);
        }
        System.out.println("Service terminé");
    }
}
