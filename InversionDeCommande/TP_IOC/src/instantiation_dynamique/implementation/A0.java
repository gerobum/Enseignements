package instantiation_dynamique.implementation;

import instantiation_dynamique.interfaces.IA;
import instantiation_dynamique.interfaces.IB;

/**
 *
 * @author maillot
 */
public class A0 implements IA{
    IB b;
    
    @Override
    public String calcul() {
        return "A0 --> " + b.getValue();
    }

    public void setB(IB b) {
        this.b = b;
    }
    
}
