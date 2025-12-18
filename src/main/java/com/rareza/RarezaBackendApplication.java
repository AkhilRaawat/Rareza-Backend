package com.rareza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RarezaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(RarezaBackendApplication.class, args);
    }
}

