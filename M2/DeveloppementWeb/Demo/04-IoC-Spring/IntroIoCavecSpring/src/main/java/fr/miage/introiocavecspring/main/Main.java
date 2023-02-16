package fr.miage.introiocavecspring.main;

import java.io.FileNotFoundException;
import fr.miage.introiocavecspring.interfaces.I;
import fr.miage.introiocavecspring.interfaces.impl.A;
import fr.miage.introiocavecspring.interfaces.impl.B;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ComponentScan("fr")
public class Main {


    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        System.out.println("-----------------------------------------------");
        /*// 1              
        I a = new A();
        a.setI(new B());
        a.go();
         */


 /* // 2 
        Scanner in = new Scanner(new File("src/main/resources/config.txt")) ;
        I a = (I) Class.forName(in.next()).getConstructor().newInstance();        
        I b = (I) Class.forName(in.next()).getConstructor().newInstance();
        a.setI(b);
        a.go();
         */
 /* // 3 
        
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        I a = (I) context.getBean("a");
        a.go();
         */
 /* // 4 
        ApplicationContext context = new AnnotationConfigApplicationContext("fr");
       
        I a = (I) context.getBean("a");
        a.go();
         */

        System.out.println("-----------------------------------------------");
    }
}
