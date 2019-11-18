package v2;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class A {

    private IB b;

    public A() {
    }

    public void setB(IB b) {
        this.b = b;
    }
    
    public void go() {
        b.go();
    }
}
