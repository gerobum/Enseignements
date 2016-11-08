
package implementation;

import interfaces.I1;

/**
 *
 * @author yvan
 */
public class C1 implements I1 {

    @Override
    public void m1() {
        System.out.println("Je fais un truc");
    }

    @Override
    public int m2(int i, double d) {
        return (int) (i * d);
    }
    
}
