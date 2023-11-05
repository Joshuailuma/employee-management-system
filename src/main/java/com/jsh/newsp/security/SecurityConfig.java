//package com.jsh.newsp.security;
//
//import lombok.AllArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@AllArgsConstructor
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf -> csrf.disable())
//                .authorizeRequests(authorize -> authorize
//                       // .requestMatchers(new AntPathRequestMatcher("/employee", "POST")).hasRole("ADMIN")
//                       // .requestMatchers(new AntPathRequestMatcher("/employee/*", "GET")).hasRole("ADMIN")
//                       // .requestMatchers(new AntPathRequestMatcher("/user/register", "POST")).permitAll()
//                        .anyRequest().authenticated())
//                .httpBasic(withDefaults()).sessionManagement(sess->   sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        return http.build();
//    }
//
//    /*
//    *        http.csrf(csrf -> csrf.disable())
//                .authorizeRequests(authorize -> authorize
//                        .requestMatchers(HttpMethod.POST).hasRole("ADMIN")
//                        .anyRequest().authenticated())
//                .formLogin(withDefaults())
//                .httpBasic(withDefaults()).sessionManagement(sess->   sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        return http.build();
//    }*/
//
//    /*
//    @Bean
//    public UserDetailsService users(){
//        // Set admin's username and pword and store it
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("admin-pass"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder.encode("user-pass"))
//                .roles("USER")
//                .build();
//        return  new InMemoryUserDetailsManager(admin);
//    }
//    *
//     */
//}
