package edu.uha.miage.introspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 *
 * @author yvan
 */
@SpringBootApplication
/*  extends SpringBootServletInitializer est nécessaire pour faire fonctionner
JSP sur un vrai serveur tomcat. Je veux dire par là (pas embarqué).
*/
public class Application extends SpringBootServletInitializer { 
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
