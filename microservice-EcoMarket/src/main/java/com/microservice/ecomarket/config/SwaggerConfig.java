package com.microservice.ecomarket.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
            .title("EcoMarket Microservice API")
            .version("1.0")
            .description("Está es nuestra Documentación de la API del microservicio EcoMarket la cual contiene Datos de tienda"));
    }
}