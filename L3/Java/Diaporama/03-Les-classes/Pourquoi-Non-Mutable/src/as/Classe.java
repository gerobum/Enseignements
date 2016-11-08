/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package as;

/**
 *
 * @author yvan
 */
public class Classe {
  private int x = 5;
  
  
  {
    System.out.println("dans le bloc : " + x);
    x = 500;
    System.out.println("dans le bloc : " + x);
  }
  {
    System.out.println("dans le bloc : " + x);
    x = 50;
    System.out.println("dans le bloc : " + x);
  }

  public Classe(int x) {
    System.out.println(this.x);
    this.x = x;
    System.out.println(this.x);
  }

  public static void main(String[] args) {
    new Classe(5000);
    java.awt.Color indigo = new java.awt.Color(75, 0, 130);
    System.out.println("bonjour".length()); // 7
System.out.println("bonjour".toUpperCase()); // BONJOUR
System.out.println("bonjour".charAt(2)); // n
  }
  
}
