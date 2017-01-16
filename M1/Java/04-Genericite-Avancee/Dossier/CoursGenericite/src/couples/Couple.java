package couples;

public class Couple<T> {
    public final T un, deux;
    public Couple(T p_un, T p_deux) {
        un = p_un; deux = p_deux;
    }
    public <U> void m1(T t, U u) {
      System.out.println(t);
      System.out.println(u);
    }
}
