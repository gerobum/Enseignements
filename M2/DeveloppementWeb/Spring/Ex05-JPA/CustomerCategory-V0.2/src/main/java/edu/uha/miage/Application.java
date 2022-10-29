// # V0.2 Ajoute la possibilité de basculer entre l'anglais et le français
// ###### à l'aide d'un paramètre dans l'url (?lang=en ou ?lang=fr)
// ###### Mais également à l'aide d'un menu.
package edu.uha.miage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "edu.uha.miage")
public class Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
