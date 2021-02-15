package com.example.decorate;

import com.example.decorate.config.YAMLConfig;
import com.example.decorate.domain.dto.BarionConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.yaml.snakeyaml.Yaml;

@SpringBootApplication
@EnableAsync
public class DecorateApplication{

    public static void main(String[] args) {
        SpringApplication.run(DecorateApplication.class, args);
    }

}
