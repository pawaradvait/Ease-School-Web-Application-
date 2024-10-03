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
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

       http.csrf().ignoringRequestMatchers(PathRequest.toH2Console());





        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/dashboard").authenticated()
                .requestMatchers("/", "/home").permitAll()
                .requestMatchers("/displayMessages").hasRole("ADMIN")
                .requestMatchers("/closeMsg").hasRole("ADMIN")


                .requestMatchers("/holidays/**").permitAll()
                .requestMatchers("/contact").permitAll()
                .requestMatchers("/saveMsg").permitAll()
                .requestMatchers("/courses").permitAll()
                .requestMatchers("/about").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/assets/**").permitAll()
                .requestMatchers("/logout").permitAll()
                .requestMatchers(PathRequest.toH2Console()).permitAll()
        );

        http.formLogin().loginPage("/login").defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll();
        http.logout(logout->logout.logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .permitAll()).httpBasic(Customizer.withDefaults());


        http.headers().frameOptions().sameOrigin(); // Enable H2 Console in iframes

        return http.build();
    }
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("12345")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("54321")
                .roles( "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }




}