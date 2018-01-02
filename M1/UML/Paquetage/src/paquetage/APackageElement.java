package paquetage;

/**
 *
 * @author yvan
 */
public abstract class APackageElement {
    public final String name;
    public final APackageElement parent;

    public APackageElement(String name, APackage parent) {
        this.name = name;
        this.parent = parent;
    }

    public APackageElement(String name) {
        this.name = name;
        this.parent = null;
    }
    
    protected abstract void print(String margin);
    protected abstract void print();
    
    public String fullyQualified() {
        if (parent == null) {
            return name;
        } else {
            return parent.fullyQualified() + "." + name;
        }
    }
}
