// #### V4.1 Modifiez l’application pour que tout le site soit accessible à tous sauf /category et
// #### /customer (et leurs sous branches) qui le sont seulement aux utilisateurs authentifiés.
// #### Lecture
// #### — https://docs.spring.io/spring-security/servlet/authentication/passwords/form.html#page-title
// #### — https://docs.spring.io/spring-security/servlet/authorization/authorize-requests.html
package edu.uha.miage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "edu.uha.miage")
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
