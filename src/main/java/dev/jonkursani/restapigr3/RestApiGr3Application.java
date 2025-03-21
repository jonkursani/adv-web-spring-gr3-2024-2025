package dev.jonkursani.restapigr3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RestApiGr3Application {

    public static void main(String[] args) {
        SpringApplication.run(RestApiGr3Application.class, args);
    }

}
