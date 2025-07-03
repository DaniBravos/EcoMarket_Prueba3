package com.microservice.ecomarket.service;

import com.microservice.ecomarket.model.EcoMarket;
import com.microservice.ecomarket.repository.IEcoMarketRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Profile("test")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private IEcoMarketRepository ecoMarketRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        

        // Generar EcoMarkets
        for (int i = 0; i < 5; i++) {
            EcoMarket ecoMarket = new EcoMarket();
            ecoMarket.setNombre(faker.company().name());
            ecoMarket.setDireccion(faker.address().streetAddress());
            ecoMarket.setCiudad(faker.address().city());
            ecoMarket.setRegion(faker.address().state());
            ecoMarket.setPais(faker.address().country());
            ecoMarket.setJefeNombre(faker.name().fullName());
            ecoMarket.setJefeCorreo(faker.internet().emailAddress());
            ecoMarket.setJefeTelefono(faker.phoneNumber().cellPhone());
            ecoMarketRepository.save(ecoMarket);
       }
    
    }    
}
