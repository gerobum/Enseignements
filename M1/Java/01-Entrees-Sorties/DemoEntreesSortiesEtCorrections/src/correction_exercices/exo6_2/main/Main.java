package correction_exercices.exo6_2.main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import correction_exercices.exo6_2.entree.FrameSaisie;
import correction_exercices.exo6_2.sortie.FrameAffichage;
import java.io.FileNotFoundException;

/**
 *
 * @author yvan
 */
public class Main {

    public static void main(String[] args) throws IOException {
        // Création d'un tube de communication en lecture
        PipedInputStream pin = new PipedInputStream();
        // Création d'un tube de communication en écriture associé au tube pin
        PipedOutputStream pout = new PipedOutputStream(pin);
        // Enrobage des tubes
        DataOutputStream dout = new DataOutputStream(pout);
        DataInputStream din = new DataInputStream(pin);
        // Pour envoyer des données d'une fenêtre à l'autre 

        new FrameAffichage(din);

        new FrameSaisie(dout);

        new FrameAffichage(new DataInputStream(new FileInputStream("doubleenbinaire")));

        // Pour afficher le contenu d'un fichier
    }
}
