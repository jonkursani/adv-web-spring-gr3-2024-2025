package dev.jonkursani.restapigr3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableScheduling
@EnableMethodSecurity // enable annotation based security (authorization)
public class RestApiGr3Application {

    public static void main(String[] args) {
        SpringApplication.run(RestApiGr3Application.class, args);
    }

}
