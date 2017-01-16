package couples;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Main {
  
  public static <T> void m3(T[] a, List<T> b) {
    System.out.println(a.getClass());
    System.out.println(b.getClass());
  }

  public static void main(String[] args) {
    Couple<Integer> c = new Couple<>(1, 2);
    c.m1(2, Color.BLACK);
    Triplet<String> t = new Triplet<>("1", "2", "3");

    System.out.println(c.un + c.deux);
    System.out.println(t.un + t.deux + t.trois);
    List<Number> l = new ArrayList<>();
    m3(new Integer[] {}, l);
  }
}
