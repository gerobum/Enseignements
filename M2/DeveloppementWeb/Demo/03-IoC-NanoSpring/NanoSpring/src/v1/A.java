package v1;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class A {

    private B b;

    public A() {
        b = new B();
    }

    public void go() {
        b.go();
    }
}
