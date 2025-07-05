package com.microservice.product.service;

import com.microservice.product.model.Product;
import com.microservice.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testFindAll() {
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productRepository.findAll()).thenReturn(products);
        List<Product> result = productService.findAll();
        assertEquals(2, result.size());
    }

    @Test
    void testFindById() {
        Product product = new Product();
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Product result = productService.findById(1L);
        assertEquals(product, result);
    }

    @Test
    void testSave() {
        Product product = new Product();
        productService.save(product);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testDelete() {
        productService.delete(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void testFindProductByIdEcoMarket() {
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productRepository.findAllProduct(1L)).thenReturn(products);
        List<Product> result = productService.findProductByIdEcoMarket(1L);
        assertEquals(2, result.size());
    }
}
