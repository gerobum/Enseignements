// #### V4.4 Modifiez l’application pour tenir compte des profiles et
// #### 1. conserver vos utilisateurs en mode DEV
// #### 2. aller chercher les utilisateurs en bdd en mode PROD
package edu.uha.miage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "edu.uha.miage")
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
