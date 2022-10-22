// #### V0.2 Pour visualiser le changement de localisation des "resolvers"
// ####      sont ajoutés pour intercepter les changements.
package edu.uha.miage.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.*;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"edu.uha.miage.web"})
@EnableWebMvc
public class SpringWebConfig implements WebMvcConfigurer {

    // #### V0.2 Demande à Spring d'intercepter les urls 
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
    }

    // #### V0.2 Afin de rendre la localisation possible, il faut ajouter un 
    // ####      bean "LocaleResolver"
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver result = new SessionLocaleResolver();
        //CookieLocaleResolver result = new CookieLocaleResolver();
        //result.setLocaleAttributeName("lang");
        //result.setDefaultLocale(Locale.FRENCH);
        return result;
    }
    // #### V0.2 Des implémentations de LocaleResolver permettre de connaître 
    // ####      la localisation par défaut à partir de la session, de cookies...

    // #### V0.2 Dans notre cas, la localisation par défaut est associé à la session.
}
