package demo.buffer;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class FlotsBufferise {
  public static void main(String[] args) throws IOException {
      try (PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter("dest1")))) {
          out1.println(Math.PI);
      }
      try (PrintStream out2 = new PrintStream(new BufferedOutputStream(new FileOutputStream("dest2")))) {
          out2.println(Math.PI);
      }
      try (DataOutputStream out3 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("data")))) {
          out3.writeDouble(Math.PI);
      }
  }
}
