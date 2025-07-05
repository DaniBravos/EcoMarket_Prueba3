package com.microservice.ecomarket.service;

import com.microservice.ecomarket.model.EcoMarket;
import com.microservice.ecomarket.repository.IEcoMarketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EcoMarketServiceImplTest {
    @Mock
    private IEcoMarketRepository ecoMarketRepository;
    @InjectMocks
    private EcoMarketServiceImpl ecoMarketService;

    @Test
    void testFindAll() {
        List<EcoMarket> markets = Arrays.asList(new EcoMarket(), new EcoMarket());
        when(ecoMarketRepository.findAll()).thenReturn(markets);
        List<EcoMarket> result = ecoMarketService.findAll();
        assertEquals(2, result.size());
    }

    @Test
    void testFindById() {
        EcoMarket market = new EcoMarket();
        when(ecoMarketRepository.findById(1L)).thenReturn(Optional.of(market));
        EcoMarket result = ecoMarketService.findById(1L);
        assertEquals(market, result);
    }

    @Test
    void testSave() {
        EcoMarket market = new EcoMarket();
        ecoMarketService.save(market);
        verify(ecoMarketRepository, times(1)).save(market);
    }

    @Test
    void testDelete() {
        ecoMarketService.delete(1L);
        verify(ecoMarketRepository, times(1)).deleteById(1L);
    }
}
