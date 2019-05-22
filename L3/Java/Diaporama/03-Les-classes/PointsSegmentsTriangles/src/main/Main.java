/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package main;

import checker.Checker;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import reflect.ReflectUtilities;
import tags.CheckModifier;
import tags.ToCheck;

class B {

}

interface C {

}

interface D {

}

interface E {

}

@ToCheck("A")
class A extends B implements D, Serializable, C, Cloneable, E, Comparable<A> {

    @Override
    public int compareTo(A o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

/**
 *
 * @author yvan
 */
public class Main {

    public static void main(String[] args) {
        /*System.out.println("// ------------ Point -------------");
        System.out.println(new Checker(cf.Point.class));
        System.out.println("// ------------ Segment -------------");
        System.out.println(new Checker(cf.Segment.class));
        System.out.println("// ------------ Triangle -------------");
        System.out.println(new Checker(cf.Triangle.class));*/

 /*Arrays.stream(cf.Segment.class.getDeclaredMethods())
                .filter(m -> m.getAnnotation(ToCompare.class) != null)
                .forEach(System.out::println);
        
        Arrays.stream(cf.Point.class.getDeclaredMethods())
                .filter(m -> m.getAnnotation(ToCompare.class) != null)
                .forEach(System.out::println);*/
        
        System.out.println(new Checker(A.class));
    }
}
