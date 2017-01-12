package demo.octets;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FlotsOctets {
  public static void main(String[] args) throws FileNotFoundException, IOException {
    FileInputStream in = new FileInputStream("orig");
    FileOutputStream out = new FileOutputStream("dest");
    int c;
    while ((c = in.read()) != -1) {
      out.write(c);
    } /* read retourne un entier et non un octet pour distinguer la lecture des erreurs et de la fin de fichier. */
  }
}
