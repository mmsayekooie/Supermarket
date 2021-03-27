package com.example.supermarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class SuperMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperMarketApplication.class, args);
    }

}
