// #### V2.2 Interception des exceptions d'intégrité de la base de données 
//
// https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc

package edu.uha.miage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "edu.uha.miage")
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
