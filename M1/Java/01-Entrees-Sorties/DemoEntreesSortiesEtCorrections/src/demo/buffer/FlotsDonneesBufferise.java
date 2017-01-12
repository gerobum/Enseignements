package demo.buffer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FlotsDonneesBufferise {

  public static void main(String[] args) throws FileNotFoundException, IOException {
    try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("data")))) {
      out.writeDouble(Math.PI);
      out.writeDouble(Double.NaN);
      out.writeDouble(-1.0);
    }
    try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("data")))) {
      System.out.println(in.markSupported());
      try {
        while (true) {
          System.out.printf("%x\n", in.readLong());
        }
      } catch (EOFException e) {
      }
    }
  }
}
