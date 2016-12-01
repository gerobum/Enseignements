package instantiation_dynamique.implementation;

import instantiation_dynamique.interfaces.IA;
import instantiation_dynamique.interfaces.IB;

/**
 *
 * @author maillot
 */
public class A1 implements IA{
    IB b;
    
    @Override
    public String calcul() {
        return "A1 --> " + b.getValue();
    }

    public void setB(IB b) {
        this.b = b;
    }
    
}
