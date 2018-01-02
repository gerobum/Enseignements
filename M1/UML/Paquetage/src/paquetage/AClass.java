
package paquetage;

/**
 *
 * @author yvan
 */
public class AClass extends APackageElement {

    public AClass(String name, APackage parent) {
        super(name, parent);
        parent.add(this);
    }

    @Override
    protected void print(String margin) {
        System.out.println(margin+name);
    } 

    @Override
    public void print() {
        print("\t");
    }
    
    
}
