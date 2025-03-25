package dev.jonkursani.restapigr3.configs;

import dev.jonkursani.restapigr3.entities.User;
import dev.jonkursani.restapigr3.repositories.UserRepository;
import dev.jonkursani.restapigr3.security.AppUserDetailsService;
import dev.jonkursani.restapigr3.security.JwtAuthenticationFilter;
import dev.jonkursani.restapigr3.services.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationService authenticationService) {
        return new JwtAuthenticationFilter(authenticationService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // per request qe nuk duhet auth vendosen te request matchers
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/departments/**").permitAll()
                        .anyRequest().authenticated() // per cdo request tjeter duhet me kon authenticated
                )
                .csrf(csrf -> csrf.disable()) // cross site request forgery
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        var user = new AppUserDetailsService(userRepository);

        String email = "user@test.com";
        userRepository.findByEmail(email)
                .orElseGet(() -> {
                    var newUser = User.builder()
                            .name("User")
                            .email(email)
                            .password(passwordEncoder().encode("password"))
                            .active(true)
                            .build();

                    return userRepository.save(newUser);
                });

        return user;
    }


}