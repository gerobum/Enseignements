package v2;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class Client {

    public static void main(String[] args) {
        A a = new A();
        a.setB(new B2());
        a.go();
    }
}
