
package main;

import p1.OuterClass;


public class Main {
  public static void main(String[] args) {
    OuterClass.NestedClass nested = new OuterClass.NestedClass();
    OuterClass outer = new OuterClass();
    System.out.println(outer.getOcs());
    nested.intrusion(outer);
    System.out.println(outer.getOcs());
    nested.notPrivate(outer);
  }
}
