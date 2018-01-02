package paquetage;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author yvan
 */
public class APackage extends APackageElement {

    private final Set<APackageElement> element;

    public APackage(String name) {
        this(name, null);
    }

    public APackage(String name, APackage pe) {
        super(name, pe);
        element = new TreeSet<>((a, b) -> a.name.compareTo(b.name));
        if (pe != null) {
            pe.add(this);
        }
    }

    @Override
    protected void print(String margin) {
        System.out.println(margin + name);
        for (APackageElement mn : element) {
            mn.print(margin+"\t");
        }
    }

    @Override
    public void print() {
        print("\t");
    }

    public void add(APackageElement pe) {
        element.add(pe);
    }

}
