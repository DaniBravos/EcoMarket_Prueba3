package com.microservice.product.controller;

import com.microservice.product.model.Product;
import com.microservice.product.service.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(productController.class)
public class ProductControllerHateoasTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService iProductService;

    @Test
    void testFindAllProduct() throws Exception {
        Product product = Product.builder().id(1L).name("Test Product").build();
        when(iProductService.findAll()).thenReturn(Collections.singletonList(product));

        mockMvc.perform(get("/api/v1/product/all").accept(MediaTypes.HAL_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._links.self").exists());
    }

    @Test
    void testFindById() throws Exception {
        Product product = Product.builder().id(1L).name("Test Product").build();
        when(iProductService.findById(1L)).thenReturn(product);

        mockMvc.perform(get("/api/v1/product/search/1").accept(MediaTypes.HAL_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._links.self").exists())
                .andExpect(jsonPath("$.id").value(1));
    }
}
