package demo.printwriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Locale;


public class FlotsAvecPrintWriter {

  public static void main(String[] args) throws FileNotFoundException, IOException {
    PrintWriter out1 = new PrintWriter(new FileWriter("dest1"));
    out1.println(Math.PI);
    out1.format("\u03C0 = %.2f\n", Math.PI);
    out1.format(Locale.US, "\u03C0 = %.2f\n", Math.PI);
    out1.printf("Le plus grand long : %d\n", Long.MAX_VALUE);
    out1.printf("Le plus grand long en hexa : %X\n", Long.MAX_VALUE);
    out1.close();
    PrintStream out2 = new PrintStream(new FileOutputStream("dest2"));
    out2.println(Math.PI);
    out2.format("\u03C0 = %.2f\n", Math.PI);
    out2.format(Locale.US, "\u03C0 = %.2f\n", Math.PI);
    out2.printf("Le plus grand long : %d\n", Long.MAX_VALUE);
    out2.printf("Le plus grand long en hexa : %X\n", Long.MAX_VALUE);
    out2.close();
  }
}
