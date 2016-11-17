package p1;

public class OuterClass {

  private String ocs = "Externe";
  private static int x = 10;

  public static class NestedClass {

    public void intrusion(OuterClass outer) {
      outer.ocs = "Intrusion";
    }

    public void notPrivate(final OuterClass outer) {
      class LocalClass {

        public void f() {
          outer.amIPrivateFromNestedClass();
          System.out.println("Le membre statique : " + outer.x);
        }
      }
      LocalClass lc = new LocalClass();
      lc.f();
    }
  }

  private void amIPrivateFromNestedClass() {
    System.out.println("Should I be private from nested ?");
  }

  public String getOcs() {
    return ocs;
  }
}
