package fr.miage.introiocavecspring.main;

import java.io.FileNotFoundException;
import fr.miage.introiocavecspring.interfaces.I;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ComponentScan("fr")
public class Main {
    

    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        /*A a = new A();
        a.setB(new B());
        a.setB(new I() {
            @Override
            public void go() {
                System.out.println("V2");
            }
        });
        a.go();*/

 /*Scanner in = new Scanner(new File("src/main/resources/config.txt")) ;
        I a = (I) Class.forName(in.next()).newInstance();        
        I b = (I) Class.forName(in.next()).newInstance();
        a.setI(b);
        a.go();*/
        // ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        // System.out.println("------------------------------------------------------------------------");

        ApplicationContext context = new AnnotationConfigApplicationContext("fr");
        //context.getBeanDefinitionNames()
        I a = (I) context.getBean("a");

        a.go();
    }
}
