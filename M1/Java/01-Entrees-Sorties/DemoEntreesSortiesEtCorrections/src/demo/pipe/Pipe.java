package demo.pipe;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Scanner;

public class Pipe {

  public static void main(String[] args) throws IOException {
    PipedInputStream pin = new PipedInputStream();
    PipedOutputStream pout = new PipedOutputStream(pin);
    DataOutputStream dout = new DataOutputStream(pout);
    DataInputStream din = new DataInputStream(pin);
    Scanner sc = new Scanner(System.in);
    System.out.print("Un double : ");
    double d = sc.nextDouble();
    while (d != Double.NaN) {
      dout.writeDouble(d);
      System.out.format("%08X\n", din.readLong());
      System.out.print("Un double : ");
      d = sc.nextDouble();
    }
  }
}
