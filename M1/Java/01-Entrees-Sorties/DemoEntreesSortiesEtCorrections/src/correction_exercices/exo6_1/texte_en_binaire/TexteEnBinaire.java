
package correction_exercices.exo6_1.texte_en_binaire;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author yvan
 */
public class TexteEnBinaire {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        System.out.println(1/0.0);
        try(Scanner in = new Scanner(new File("doubleentexte"));
            DataOutputStream out = new DataOutputStream(new FileOutputStream(new File("doubleenbinaire"))))
            {
            while(in.hasNextDouble()) {
                out.writeDouble(in.nextDouble());
            }
        }
    }
}
