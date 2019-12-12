

package edu.uha.miage.sessions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = "edu.uha.miage")
// #### V0.0 "fr.miage" précise à partir d'où commence le scan des beans.
public class Application extends SpringBootServletInitializer implements WebMvcConfigurer {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
