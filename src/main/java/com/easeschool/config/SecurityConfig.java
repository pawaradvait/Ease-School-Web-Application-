package com.easeschool.config;

import lombok.val;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {





        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/dashboard").authenticated()
                .requestMatchers("/", "/home").permitAll()
                .requestMatchers("/displayMessages/**").hasRole("ADMIN")
                .requestMatchers("/closeMsg").hasRole("ADMIN")
                .requestMatchers("/admin/**").hasRole("ADMIN")

                .requestMatchers("/student/**").authenticated()
                .requestMatchers("/displayProfile").authenticated()
                .requestMatchers("/updateProfile").authenticated()

                .requestMatchers("/public/**").permitAll()

                .requestMatchers("/holidays/**").permitAll()
                .requestMatchers("/contact").permitAll()
                .requestMatchers("/saveMsg").permitAll()
                .requestMatchers("/courses").permitAll()
                .requestMatchers("/about").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/assets/**").permitAll()
                .requestMatchers("/logout").permitAll()
        );

        http.formLogin().loginPage("/login").defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll();
        http.logout(logout->logout.logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .permitAll()).httpBasic(Customizer.withDefaults());




        return http.build();
    }

     @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
     }




}