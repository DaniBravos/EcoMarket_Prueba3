package com.microservice.ecomarket.service;

import com.microservice.ecomarket.model.EcoMarket;
import com.microservice.ecomarket.repository.IEcoMarketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DataLoaderTest {
    @Mock
    private IEcoMarketRepository ecoMarketRepository;
    @InjectMocks
    private DataLoader dataLoader;

    @Test
    void testRun() throws Exception {
        dataLoader.run();
        verify(ecoMarketRepository, atLeastOnce()).save(any(EcoMarket.class));
    }
}
