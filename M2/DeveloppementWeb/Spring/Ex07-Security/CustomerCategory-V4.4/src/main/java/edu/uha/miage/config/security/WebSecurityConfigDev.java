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

import edu.uha.miage.web.security.CustomAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yvan
 */


@Configuration
@EnableWebSecurity
@Profile("dev")
// #### V4.4
public class WebSecurityConfigDev extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().defaultSuccessUrl("/")
                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .and().logout().logoutSuccessUrl("/");

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
                .mvcMatchers("/logout").authenticated()
                .mvcMatchers("/access-denied").permitAll()
                .anyRequest().denyAll()
        );
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public UserDetailsService users() {
        // https://docs.spring.io/spring-security/site/docs/5.0.x/api/org/springframework/security/core/userdetails/User.html#withDefaultPasswordEncoder--
        // UserBuilder users = User.withDefaultPasswordEncoder();
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails user = User.withUsername("user")
                .password("{bcrypt}$2y$10$EuQV9uU0AoWxfzJQB7VBzOxjHqOyLIKqU5XIpaQiEG3mGrzWcx0e.")
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .username("admin")
                .password("{bcrypt}$2y$10$DQpW80yZPrGgD9CADFELAO./yR/v40YdmMYvtMAAcLmHU4cT3dGtO")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

}
