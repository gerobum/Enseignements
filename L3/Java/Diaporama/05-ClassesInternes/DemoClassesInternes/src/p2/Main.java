package p2;

public class Main {
  public static void main(String[] args) {
    System.out.println(OuterClass.getA()); // 15
    System.out.println(OuterClass.getB()); // 30
    System.out.println(OuterClass.NestedClass.getA()); // 15
    System.out.println(OuterClass.NestedClass.getB()); // 30
    OuterClass.augmente();
    System.out.println(OuterClass.getA()); // 16
    System.out.println(OuterClass.getB()); // 31
    System.out.println(OuterClass.NestedClass.getA()); // 16
    System.out.println(OuterClass.NestedClass.getB()); // 31
    OuterClass.NestedClass.augmente();
    System.out.println(OuterClass.getA()); // 17
    System.out.println(OuterClass.getB()); // 34
    System.out.println(OuterClass.NestedClass.getA()); // 17
    System.out.println(OuterClass.NestedClass.getB()); // 34
  }
}
