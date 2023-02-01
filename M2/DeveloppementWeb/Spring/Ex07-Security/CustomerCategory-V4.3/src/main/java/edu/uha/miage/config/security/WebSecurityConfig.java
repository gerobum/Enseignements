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
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author yvan
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends
        WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().defaultSuccessUrl("/")
                // ##### Pour gérer les erreurs d'accès
                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .and().logout().logoutSuccessUrl("/");
        http.authorizeRequests(authorize -> authorize
                // #### V4.3

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
                .anyRequest().denyAll()


        );
    }

    // ##### Pour gérer les erreurs d'accès
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public UserDetailsService users() {
        // The builder will ensure the passwords are encoded before saving in memory
        UserBuilder users = User.withDefaultPasswordEncoder();

        UserDetails user = users
                .username("user")
                .password("user")
                .roles("USER")
                .build();
        UserDetails admin = users
                .username("admin")
                .password("admin")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

}
