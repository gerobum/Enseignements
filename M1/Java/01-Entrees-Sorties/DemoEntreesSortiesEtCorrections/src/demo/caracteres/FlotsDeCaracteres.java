package demo.caracteres;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FlotsDeCaracteres {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        try (FileWriter out = new FileWriter("dest");
                FileReader in = new FileReader("orig")) {
            int c;
            String s = "";
            while ((c = in.read()) != -1) {
                s += (char) c;
            }
            out.write(s);
        }
    }
}
