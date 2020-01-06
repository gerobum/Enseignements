package edu.uha.miage.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Profile("dev")
// #### V4.0 Config security for paths, users and authorities
public class WebSecurityConfigDev extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("me").password("{noop}me").roles("USER")
                .and().withUser("you").password("{noop}you").roles("USER", "ADMIN");
        // #### V4.0 {noop} signifie pas d'encodeur. 
        // #### V4.0 Lire https://docs.spring.io/spring-security/site/docs/current/reference/html/jc-authentication.html#pe-dpe-format
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // http.csrf().disable(); // Easy but not secure

        http.authorizeRequests()
                .antMatchers("/customer/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/category/**").hasRole("ADMIN")
                .antMatchers("/**").permitAll()
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutSuccessUrl("/");
    }

}
