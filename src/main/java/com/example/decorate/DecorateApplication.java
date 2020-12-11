package com.example.decorate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class DecorateApplication {

    public static void main(String[] args) {
        SpringApplication.run(DecorateApplication.class, args);
    }

}
