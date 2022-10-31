// #### V1.5 Chaque client s'affiche avec sa catégorie, si elle existe.
// ######### Deux choses à faire :
// #########    - redéfinir la méthode toString() de Customer.
// #########    - dans list.html choisir d'affichier customer plutôt que customer.name

package edu.uha.miage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "edu.uha.miage")
public class Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
