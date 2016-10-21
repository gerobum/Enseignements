/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.tcp;

import demo.udp.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

/**
 *
 * @author yvan
 */
public class Recoit {
  public static void main(String[] args) throws SocketException, IOException {
    try (Socket s = new Socket("localhost", 1414)) {
      System.out.println("Connecté");
      Scanner scan = new Scanner(s.getInputStream());
      while(scan.hasNext()) {
        System.out.println(scan.nextInt());
      }
      System.out.println("Fini");
    }
  }
}
