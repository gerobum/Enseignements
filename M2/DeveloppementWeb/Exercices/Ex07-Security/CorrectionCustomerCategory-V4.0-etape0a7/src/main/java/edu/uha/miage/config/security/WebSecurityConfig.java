/*
 * Copyright (C) 2020 Yvan Maillot <yvan.maillot@uha.fr>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.uha.miage.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
@Configuration
@EnableWebSecurity
// #### V4.0-etape3
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // #### V4.0-etape4
        // http.csrf().disable(); // Easy but not secure

        // #### V4.0-etape6 (exo)
        http.authorizeRequests()
        // #### V4.0-etape7 (ex2) - 4 lignes
                .antMatchers("/customer/edit/**").hasRole("ADMIN")
                .antMatchers("/customer/create/**").hasRole("ADMIN")
                .antMatchers("/category/edit/**").hasRole("ADMIN")
                .antMatchers("/category/create/**").hasRole("ADMIN")
                .antMatchers("/customer/**").authenticated()
                .antMatchers("/category/**").authenticated()
                .antMatchers("/**").permitAll()
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("me").password("{noop}me").roles("USER")
                .and().withUser("you").password("{noop}you").roles("USER", "ADMIN");
        // #### V4.0 {noop} signifie pas d'encodeur. 
        // #### V4.0 Lire https://docs.spring.io/spring-security/site/docs/current/reference/html/jc-authentication.html#pe-dpe-format
    }

}
