/*
 * Creative commons CC BY-NC-SA 2020 Yvan Maillot <yvan.maillot@uha.fr>
 *
 *     Share - You can copy and redistribute the material in any medium or format
 * 
 *     Adapt - You can remix, transform, and build upon the material 
 * 
 * Under the following terms :
 * 
 *     Attribution - You must give appropriate credit, provide a link to the license, 
 *     and indicate if changes were made. You may do so in any reasonable manner, 
 *     but not in any way that suggests the licensor endorses you or your use. 
 * 
 *     NonCommercial — You may not use the material for commercial purposes. 
 * 
 *     ShareAlike — If you remix, transform, or build upon the material, 
 *     you must distribute your contributions under the same license as the original. 
 * 
 * Notices:    You do not have to comply with the license for elements of 
 *             the material in the public domain or where your use is permitted 
 *             by an applicable exception or limitation. 
 * 
 * No warranties are given. The license may not give you all of the permissions 
 * necessary for your intended use. For example, other rights such as publicity, 
 * privacy, or moral rights may limit how you use the material. 
 * 
 * See <https://creativecommons.org/licenses/by-nc-sa/4.0/>.
 */
package edu.uha.miage.config.security;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author yvan
 */
@Configuration
@EnableWebSecurity
@Profile("prod")
// #### V4.4





public class WebSecurityConfigProd extends
        WebSecurityConfigurerAdapter {
    
    
    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                //.withDefaultSchema()
                .usersByUsernameQuery("select username,password,enabled from users where username = ?")
                .authoritiesByUsernameQuery("select username,authority from authorities where username = ?");
        // #### V4.0 Injected dataSource must have users and authorities tables
        // #### V4.0 (https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#user-schema)
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        
        http.authorizeRequests(authorize -> authorize
                .mvcMatchers("/customer/edit/**").hasAnyRole("ADMIN")
                .mvcMatchers("/customer/delete/**").hasAnyRole("ADMIN")
                .mvcMatchers("/customer/create/**").hasAnyRole("ADMIN")
                .mvcMatchers("/category/edit/**").hasAnyRole("ADMIN")
                .mvcMatchers("/category/delete/**").hasAnyRole("ADMIN")
                .mvcMatchers("/category/create/**").hasAnyRole("ADMIN")
                .mvcMatchers("/customer").hasAnyRole("USER", "ADMIN")
                .mvcMatchers("/category").hasAnyRole("USER", "ADMIN")
                .mvcMatchers("/resources/**", "/**").permitAll()
                .mvcMatchers("/login").permitAll()
                .anyRequest().denyAll()
                
                
        );
    }
}
