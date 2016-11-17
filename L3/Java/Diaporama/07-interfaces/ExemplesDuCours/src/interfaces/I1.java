
package interfaces;

/**
 *
 * @author yvan
 */
public interface I1 {
    public static final int c1 = 2;
    public void m1();
    public int m2(int i, double d);
    public default String md() {
        return "défaut";
    }
    public default String md1() {
        return "défaut I1";
    }
    public static String ms() {
        return "static";
    }
    public class C {}
    public interface I {}  
}
