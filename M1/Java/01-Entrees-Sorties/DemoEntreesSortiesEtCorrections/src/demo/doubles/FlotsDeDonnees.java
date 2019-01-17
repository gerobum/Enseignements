package demo.doubles;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FlotsDeDonnees {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("data"))) {
            for (double x = -0.5; x <= 0.5; x += 0.1) {
                out.writeDouble(x);
            }
            out.writeDouble(Math.PI);
            out.writeDouble(Double.NaN);
            out.writeDouble(-1.0);
            out.writeDouble(0);
        }
        try (DataInputStream in = new DataInputStream(new FileInputStream("data"))) {
            System.out.println(in.markSupported());
            try {
                while (true) {
                    System.out.printf("%016X\n", in.readLong());
                }
            } catch (EOFException e) {
            }
        }
    }
}
