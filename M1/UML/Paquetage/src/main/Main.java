package main;

import paquetage.AClass;
import paquetage.APackage;



/**
 *
 * @author yvan
 */
public class Main {
    public static void main(String[] args) {
        APackage a = new APackage("a");
        APackage aa = new APackage("a", a);
        APackage ab = new APackage("b", a);
        AClass aA = new AClass("A", a);
        AClass aB = new AClass("B", a);
        AClass aC = new AClass("C", a);
        APackage aba = new APackage("b", ab);
        AClass abA = new AClass("A", ab);
        AClass abB = new AClass("B", ab);
        AClass abC = new AClass("C", ab);
        
        System.out.println(abC.fullyQualified());
        a.print();
    }
            
}
