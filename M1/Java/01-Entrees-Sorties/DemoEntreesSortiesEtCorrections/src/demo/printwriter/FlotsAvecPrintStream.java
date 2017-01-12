package demo.printwriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Locale;

public class FlotsAvecPrintStream {

  public static void main(String[] args) throws FileNotFoundException, IOException {
      try (PrintStream out = new PrintStream(new FileOutputStream("dest"))) {
          out.println(Math.PI);
          out.format("\u03C0 = %.2f\n", Math.PI);
          out.format(Locale.US, "\u03C0 = %.2f\n", Math.PI);
          out.printf("Le plus grand entier long : %d\n", Long.MAX_VALUE);
          out.format(Locale.US, "Le plus grand entier long : %d\n", Long.MAX_VALUE);
      }
  }
}
