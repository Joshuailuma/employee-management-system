package com.jsh.newsp.security;

import com.jsh.newsp.filter.AuthenticationFilter;
import com.jsh.newsp.filter.ExceptionHandlerFilter;
import com.jsh.newsp.manager.CustomAuthenticationManager;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    CustomAuthenticationManager customAuthenticationManager; //Inject this inside the filter

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
        authenticationFilter.setFilterProcessesUrl("/user/authenticate");


        http.csrf(csrf -> csrf.disable())
                .authorizeRequests(authorize -> authorize
                        .requestMatchers(new AntPathRequestMatcher("/employee", "POST")).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/employee/*", "GET")).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/user/register", "POST")).permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
                .addFilter(authenticationFilter)
                .httpBasic(withDefaults()).sessionManagement(sess->   sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    /*
    implement login and register
    *        http.csrf(csrf -> csrf.disable())
                .authorizeRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST).hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(withDefaults())
                .httpBasic(withDefaults()).sessionManagement(sess->   sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }*/

    /*
    @Bean
    public UserDetailsService users(){
        // Set admin's username and pword and store it
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin-pass"))
                .roles("ADMIN")
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("user-pass"))
                .roles("USER")
                .build();
        return  new InMemoryUserDetailsManager(admin);
    }
    *
     */
}
