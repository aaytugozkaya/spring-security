package com.aaytugozkaya.springsecurity.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class BasicAuthSecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                auth -> {auth.anyRequest().authenticated();}
        );
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        //http.formLogin();
        http.httpBasic();
         return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        var user =User.withUsername("aaytugozkaya")
                .password("12345")
                .roles("USER")
                .build();
        var admin = User.withUsername("admin")
                .password("root")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user,admin);
    }


    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            public void addCorsMappings(CorsRegistry registry){
                registry.addMapping("/**").
                        allowedMethods("*").
                        allowedOrigins("http://localhost:3000");
            }
        };
    }
}
