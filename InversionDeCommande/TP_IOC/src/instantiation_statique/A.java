package instantiation_statique;

/**
 *
 * @author maillot
 */
public class A {

    private B b = new B();
    
    public String calcul() {
        return "A0 --> "+b.getValue();
    }
    
}
