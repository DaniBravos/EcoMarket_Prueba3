package com.microservice.product.controller;

import com.microservice.product.model.Product;
import com.microservice.product.service.IProductService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProductControllerTest {

    @Mock
    private IProductService iProductService;

    @InjectMocks
    private productController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test 1: Obtener todos los productos
    @Test
    public void testFindAllProducts() {
        List<Product> mockList = Arrays.asList(
            Product.builder()
                .id(1L)
                .name("Manzana")
                .description("Fruta roja")
                .price(500.0)
                .stock(100)
                .ecoMarketId(1L)
                .build(),
            Product.builder()
                .id(2L)
                .name("Plátano")
                .description("Fruta amarilla")
                .price(300.0)
                .stock(80)
                .ecoMarketId(1L)
                .build()
        );

        when(iProductService.findAll()).thenReturn(mockList);

        ResponseEntity<?> response = productController.findAllProduct();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockList, response.getBody());
    }

    // Test 2: Buscar producto por ID
    @Test
    public void testFindById() {
        Product producto = Product.builder()
            .id(1L)
            .name("Zanahoria")
            .description("Hortaliza")
            .price(200.0)
            .stock(50)
            .ecoMarketId(2L)
            .build();

        when(iProductService.findById(1L)).thenReturn(producto);

        ResponseEntity<?> response = productController.findById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(producto, response.getBody());
    }

    // Test 3: Guardar producto
    @Test
    public void testSaveProduct() {
        Product nuevo = Product.builder()
            .id(3L)
            .name("Pera")
            .description("Fruta jugosa")
            .price(400.0)
            .stock(60)
            .ecoMarketId(3L)
            .build();

        doNothing().when(iProductService).save(nuevo);

        productController.saveProduct(nuevo);

        verify(iProductService, times(1)).save(nuevo);
    }

    // Test 4: Eliminar producto
    @Test
    public void testDeleteProduct() {
        Long id = 1L;

        doNothing().when(iProductService).delete(id);

        productController.deleteProduct(id);

        verify(iProductService, times(1)).delete(id);
    }

    // Test 5: Buscar productos por ID de EcoMarket
    @Test
    public void testFindProductByEcoMarketId() {
        Long idEcoMarket = 1L;

        List<Product> productos = Arrays.asList(
            Product.builder().id(1L).name("Choclo").ecoMarketId(1L).build(),
            Product.builder().id(2L).name("Zapallo").ecoMarketId(1L).build()
        );

        when(iProductService.findProductByIdEcoMarket(idEcoMarket)).thenReturn(productos);

        ResponseEntity<?> response = productController.findProductByIdEcoMarket(idEcoMarket);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productos, response.getBody());
    }
}
