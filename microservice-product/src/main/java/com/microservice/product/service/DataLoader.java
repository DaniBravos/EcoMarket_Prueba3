package com.microservice.product.service;

import com.microservice.product.model.Product;
import com.microservice.product.repository.ProductRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Profile("test")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        

        // Generar productos
        for (int i = 0; i < 20; i++) {
            Product product = new Product();
            product.setName(faker.commerce().productName());
            product.setDescription(faker.lorem().sentence());
            product.setPrice(Double.valueOf(faker.commerce().price()));
            product.setStock(faker.number().numberBetween(10, 100));
           
            productRepository.save(product);
        }
    }
}