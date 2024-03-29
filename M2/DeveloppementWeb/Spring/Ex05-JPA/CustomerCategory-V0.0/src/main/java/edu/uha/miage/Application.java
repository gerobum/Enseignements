// #### V0.0 Application "Customers" qui permet de lister, ajouter et modifier
// #### V0.0 des clients dans une base de données.
package edu.uha.miage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// #### V0.0 @SpringBootApplication est un raccourci pour 
// ######### @EnableAutoConfiguration
// ######### @ComponentScan
// ######### @Configuration
// ######### https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-using-springbootapplication-annotation.html
@SpringBootApplication(scanBasePackages = "edu.uha.miage")
// #### V0.0 "edu.uha.miage" précise à partir d'où commence le scan des beans.
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
