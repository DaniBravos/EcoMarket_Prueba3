package com.microservice.ecomarket.controller;

import com.microservice.ecomarket.model.EcoMarket;
import com.microservice.ecomarket.service.IEcoMarketService;
import com.microservice.ecomarket.dto.ProductDTO;
import com.microservice.ecomarket.http.response.ProductByEcoMarketResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class EcoMarketControllerTest {

    @Mock
    private IEcoMarketService ecoMarketService;

    @InjectMocks
    private EcoMarketController ecoMarketController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test 1: Obtener todos los locales
    @Test
    public void testFindAllEcoMarkets() {
        List<EcoMarket> mockList = Arrays.asList(
            EcoMarket.builder()
                .id(1L)
                .nombre("Feria A")
                .direccion("Calle 1")
                .ciudad("Santiago")
                .region("RM")
                .pais("Chile")
                .jefeNombre("María")
                .jefeCorreo("maria@feria.cl")
                .jefeTelefono("12345678")
                .build(),
            EcoMarket.builder()
                .id(2L)
                .nombre("Feria B")
                .direccion("Calle 2")
                .ciudad("Valparaíso")
                .region("V")
                .pais("Chile")
                .jefeNombre("Carlos")
                .jefeCorreo("carlos@feria.cl")
                .jefeTelefono("98765432")
                .build()
        );
        when(ecoMarketService.findAll()).thenReturn(mockList);
        var response = ecoMarketController.findAllEcoMarkets();
        assertNotNull(response);
        assertTrue(response.getLinks().hasLink("self"));
        assertEquals(2, response.getContent().size());
    }

    // Test 2: Buscar por ID
    @Test
    public void testFindById() {
        EcoMarket mercado = EcoMarket.builder()
            .id(1L)
            .nombre("Mercado Central")
            .direccion("Calle Central")
            .ciudad("Santiago")
            .region("RM")
            .pais("Chile")
            .jefeNombre("Pedro")
            .jefeCorreo("pedro@mercado.cl")
            .jefeTelefono("5555555")
            .build();
        when(ecoMarketService.findById(1L)).thenReturn(mercado);
        var response = ecoMarketController.findById(1L);
        assertNotNull(response);
        assertEquals(mercado, response.getContent());
        assertTrue(response.getLinks().hasLink("self"));
    }

    // Test 3: Guardar nuevo local
    @Test
    public void testSaveEcoMarket() {
        EcoMarket nuevo = EcoMarket.builder()
            .id(3L)
            .nombre("Mercado Nuevo")
            .direccion("Nueva 123")
            .ciudad("Concepción")
            .region("VIII")
            .pais("Chile")
            .jefeNombre("Laura")
            .jefeCorreo("laura@nuevo.cl")
            .jefeTelefono("3333333")
            .build();
        doNothing().when(ecoMarketService).save(nuevo);
        var response = ecoMarketController.saveEcoMarket(nuevo);
        verify(ecoMarketService, times(1)).save(nuevo);
        assertNotNull(response);
        assertEquals(nuevo, response.getContent());
        assertTrue(response.getLinks().hasLink("self"));
    }

    // Test 4: Eliminar por ID
    @Test
    public void testDeleteEcoMarket() {
        Long id = 1L;
        doNothing().when(ecoMarketService).delete(id);
        var response = ecoMarketController.deleteEcoMarket(id);
        verify(ecoMarketService, times(1)).delete(id);
        assertNotNull(response);
        assertEquals("EcoMarket eliminado", response.getContent());
        assertTrue(response.getLinks().hasLink("all-ecomarkets"));
    }

    // Test 5: Buscar productos por ID de local
    @Test
    public void testFindProductByIdEcoMarket() {
        Long idEcoMarket = 1L;
        List<ProductDTO> productos = Arrays.asList(
            ProductDTO.builder().id(1L).nombre("Lechuga").build(),
            ProductDTO.builder().id(2L).nombre("Tomate").build()
        );
        ProductByEcoMarketResponse mockResponse = ProductByEcoMarketResponse.builder()
            .ecoMarketName("Feria Modelo")
            .jefeNombre("Juan Pérez")
            .productDTOList(productos)
            .build();
        when(ecoMarketService.findProductByIdEcoMarket(idEcoMarket)).thenReturn(mockResponse);
        var response = ecoMarketController.findProductByIdEcoMarket(idEcoMarket);
        assertNotNull(response);
        assertEquals(mockResponse, response.getContent());
        assertTrue(response.getLinks().hasLink("self"));
    }
}
