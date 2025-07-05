package com.microservice.product.service;

import com.microservice.product.model.Product;
import com.microservice.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DataLoaderTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private DataLoader dataLoader;

    @Test
    void testRun() throws Exception {
        dataLoader.run();
        verify(productRepository, atLeastOnce()).save(any(Product.class));
    }
}
