package couples;

public class Triplet<T> extends Couple<T> {
  public final T trois;
  public Triplet(T p_un, T p_deux, T p_trois) {
    super(p_un, p_deux);
    trois = p_trois;
  }
}
