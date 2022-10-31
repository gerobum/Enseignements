// #### V1.2 Ajout de quelques tests unitaires.
// #### V1.2 Les modifications se voient "pom.xml" et surtout dans "Test Packages".
// #### V1.2 https://docs.spring.io/spring-batch/3.0.x/reference/html/testing.html
package edu.uha.miage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "edu.uha.miage")
public class Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
