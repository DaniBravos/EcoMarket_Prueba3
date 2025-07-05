package com.microservice.ecomarket.repository;

import com.microservice.ecomarket.model.EcoMarket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class IEcoMarketRepositoryTest {

    @Autowired
    private IEcoMarketRepository ecoMarketRepository;

    @Test
    void testSaveAndFindById() {
        EcoMarket ecoMarket = EcoMarket.builder()
                .nombre("EcoMarket Central")
                .direccion("Calle Falsa 123")
                .ciudad("Ciudad X")
                .region("Región Y")
                .pais("País Z")
                .jefeNombre("Juan Perez")
                .jefeCorreo("juan@correo.com")
                .jefeTelefono("123456789")
                .build();

        EcoMarket saved = ecoMarketRepository.save(ecoMarket);
        Optional<EcoMarket> found = ecoMarketRepository.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getNombre()).isEqualTo("EcoMarket Central");
    }
}
