package com.master.gestion_ticket.config;

import com.master.gestion_ticket.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.userDetailsService(userDetailsService);
        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests((requests) -> {
                //requests.requestMatchers("/api/**").permitAll();
                requests.requestMatchers("/api/tickets").hasRole("APPRENANT");
                requests.requestMatchers("/api/utilisateurs/**").hasRole("ADMIN");
            }
        ).httpBasic(withDefaults());

        return http.build();
    }

    //@Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .build();

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("{noop}user")
                .roles("USER")
                .build();

        UserDetails formateur = User.withDefaultPasswordEncoder()
                .username("formateur")
                .password("{noop}formateur")
                .roles("FORMATEUR")
                .build();

        UserDetails apprenant = User.withDefaultPasswordEncoder()
                .username("apprenant")
                .password("{noop}apprenant")
                .roles("APPRENANT")
                .build();

        return new InMemoryUserDetailsManager(admin, user, formateur, apprenant);
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
