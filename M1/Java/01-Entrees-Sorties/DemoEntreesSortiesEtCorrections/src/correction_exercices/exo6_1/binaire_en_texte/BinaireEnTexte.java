package correction_exercices.exo6_1.binaire_en_texte;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

/**
 *
 * @author yvan
 */
public class BinaireEnTexte {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        try (DataInputStream in = new DataInputStream(new FileInputStream(new File("doubleenbinaire")));
                PrintWriter out = new PrintWriter((new File("doubleentextedepuisbinaire")))) {
            boolean pasfini = true;
            while (pasfini) {
                try {
                    out.printf(Locale.FRANCE, "%f\n", in.readDouble());
                } catch (EOFException e) {
                    pasfini = false;
                }
            }
        }
    }
}
