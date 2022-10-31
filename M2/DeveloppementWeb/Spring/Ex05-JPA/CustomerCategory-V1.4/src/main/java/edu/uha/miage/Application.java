// #### V1.4 Un client appartient à 0 ou 1 catégorie, une catégorie est associée à 0, 1
// ######### ou plusieurs clients. 
// ######### Le formulaire de création et de modification de client offre maintenant
// ######### la possibilité d'affecter une catégorie à un client ou de la changer.

package edu.uha.miage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "edu.uha.miage")
public class Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
