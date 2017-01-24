
package testBidon;



public class Couple<T> {
    T un, deux;

    public Couple(T un, T deux) {
        this.un = un;
        this.deux = deux;
    }
    public T getUn() {
        return un;
    }
    public T getDeux() {
        return deux;
    }   
    
    public static <U extends Comparable<? super U>> U maxOK(Couple<U> c) {
        if (c.un.compareTo(c.deux) < 0)
            return c.deux;
        else
            return c.un;
    }   
    
    public static <U extends Comparable<U>> U maxNoOK(Couple<U> c) {
        if (c.un.compareTo(c.deux) < 0)
            return c.deux;
        else
            return c.un;
    }  
    
    public static <U extends Comparable<U>> U max(Couple<U> c) {
        if (c.un.compareTo(c.deux) < 0)
            return c.deux;
        else
            return c.un;
    }


}