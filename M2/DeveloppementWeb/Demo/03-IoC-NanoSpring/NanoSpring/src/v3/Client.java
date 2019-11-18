package v3;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class Client {

    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        
        Scanner in = new Scanner(A.class.getResourceAsStream("config.txt"));
        
        String aNameClass = in.next();
        String bNameClass = in.next();
        System.out.println("IA => " + aNameClass);
        System.out.println("IB => " + bNameClass);
        
        A a = (A) Class.forName(aNameClass).newInstance();
        IB b = (IB) Class.forName(bNameClass).newInstance();
        Method set = a.getClass().getMethod("setB", IB.class);
        set.invoke(a, b);
        a.go();
    }
}
