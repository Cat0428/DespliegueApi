package com.festivos.presentacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

@EnableAsync
@SpringBootApplication
@ComponentScan(basePackages = "com.festivos")
@EnableJpaRepositories(basePackages = "com.festivos.infraestructura.repositorios")
@EntityScan(basePackages = "com.festivos.dominio.entidades")
public class ApiFestivosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiFestivosApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}