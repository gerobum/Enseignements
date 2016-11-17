
package interfaces;

/**
 *
 * @author yvan
 */
public interface I2 {
    int c1 = 2;
    void m1();
    int m2(int i, double d);
    default String md() {
        return "défaut";
    }  
    public default String md2() {
        return "défaut I2";
    } 
    static String ms() {
        return "static";
    } 
    class C {}
    interface I {}
}
