/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2;

/**
 *
 * @author yvan
 */
public class OuterClass {
  private static int a = 15;
  
  public static class NestedClass {
    private static int b = 2*a;
    
    public static void augmente() {
      OuterClass.a++;
      b = 2*a;
    }

    public static int getB() {
      return b;
    }
    public static int getA() {
      return a;
    }
  }
  
  public static void augmente() {
    a++;
    NestedClass.b++;
  }

  public static int getA() {
    return a;
  }
  
  public static int getB() {
    return NestedClass.b;
  }
  
}
