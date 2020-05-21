package com.quince.rentingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class QuinceRentingApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuinceRentingApplication.class, args);
    }

}
