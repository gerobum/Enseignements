package demo.scanner;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws FileNotFoundException {
    Scanner sc = new Scanner("Une liste de mots");
    while (sc.hasNext()) {
      System.out.println(sc.next());
    }
    sc = new Scanner(new File("dest"));
    while (sc.hasNext()) {
      System.out.println(sc.next());
    }
    sc = new Scanner(new BufferedInputStream(new FileInputStream("dest")));
    while (sc.hasNext()) {
      System.out.println(sc.nextLine());
    }

    String texte = "On peut alors assez facilement découper un texte en mots, "
            + "en tenant compte de la ponctuation. Il faudra considérer aussi "
            + "les points virgules (;) ; même s'ils sont rares : c'est ce qu'on va "
            + "faire ici ! \nVoici un bel exemple, qu'en pensez-vous ? pas mal, non ?";
    sc = new Scanner(texte);
    sc.useDelimiter("[\\s;,.?!':\\\"()]+");

    while (sc.hasNext()) {
      System.out.println("#" + sc.next() + "#");

    }
  }
}
