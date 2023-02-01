// #### V4.3 Modifiez l’application pour que
// #### 1. / et /list soient accessibles à tous,
// #### 2. /category et /customer soit accessibles aux USER et ADMIN,
// #### 3. que les modifications soient accessibles seulement aux ADMIN.
package edu.uha.miage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "edu.uha.miage")
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
