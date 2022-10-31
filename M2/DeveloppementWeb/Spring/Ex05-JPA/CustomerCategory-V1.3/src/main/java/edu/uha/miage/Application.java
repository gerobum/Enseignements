// #### V1.3 La liste des clients comme des catégories doit s'afficher dans l'ordre alphabétique.
// #### V1.3 C'est seulement le findAll de la couche service qui sera modifié.
package edu.uha.miage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "edu.uha.miage")
public class Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
