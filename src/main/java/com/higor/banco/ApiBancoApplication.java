package com.higor.banco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.higor.banco.repository"})
public class ApiBancoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiBancoApplication.class, args);
    }

}
