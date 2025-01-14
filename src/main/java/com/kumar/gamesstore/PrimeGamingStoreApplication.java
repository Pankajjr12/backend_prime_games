package com.kumar.gamesstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.kumar.gamesstore")
public class PrimeGamingStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrimeGamingStoreApplication.class, args);
    }

}
