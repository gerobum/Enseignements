// #### V4.2 Configurer par programme deux utilisateurs, leur mot de passe et leur rôle (USER, ADMIN).
// #### Lecture
// #### https://docs.spring.io/spring-security/servlet/authentication/passwords/in-memory.html#page-title
// #### https://docs.spring.io/spring-security/servlet/authentication/index.html
package edu.uha.miage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "edu.uha.miage")
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
