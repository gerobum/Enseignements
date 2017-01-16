package exemplesDuCours;

public class d04_Triplet<T> extends d03_Couple<T> {
    public final T trois;
    public d04_Triplet(T p_un,
                   T p_deux, T p_trois) {
        super(p_un, p_deux);
        trois = p_trois;
    }
}

