package edu.uha.miage.security;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Profile("prod")
// #### V4.0 Config security for paths, users and authorities
public class WebSecurityConfigProd extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;
    
  

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                //.withDefaultSchema()
                .usersByUsernameQuery("select username,password,true from users where username = ?")
                .authoritiesByUsernameQuery("select username,authority from authorities where username = ?");
        // #### V4.0 Injected dataSource must have users and authorities tables
        // #### V4.0 (https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#user-schema)
        auth
                .inMemoryAuthentication()
                .withUser("me").password("{noop}me").roles("USER")
                .and().withUser("you").password("{noop}you").roles("USER", "ADMIN");
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
