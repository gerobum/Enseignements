package exemplesDuCours;

import java.lang.reflect.Array;

public class d13_tableauAvecTypeParam {

    public static <T> T[] créerTableau(int n) {
            return new T[n];
    }

    public static <T> T[] créerTableau(int n, T t)
    {
     return (T[]) Array.newInstance(t.getClass(), n);
    }


}
