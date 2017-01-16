package instantiation_dynamique.main;

import instantiation_dynamique.interfaces.IA;
import instantiation_dynamique.interfaces.IB;
import java.util.Scanner;

/**
 *
 * @author maillot
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(Main.class.getResourceAsStream("config.txt"));
        
        Class<?> ca = Class.forName(in.next());
        Class<?> cb = Class.forName(in.next());
        IA a = (IA) ca.newInstance();
        IB b = (IB) cb.newInstance();
        ca.getMethod("setB", IB.class).invoke(a, b);
        
        System.out.println(a.calcul());
    }
    
}
