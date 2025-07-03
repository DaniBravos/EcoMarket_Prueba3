package com.microservice.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceproductApplication {
//prueba
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceproductApplication.class, args);
	}

}
