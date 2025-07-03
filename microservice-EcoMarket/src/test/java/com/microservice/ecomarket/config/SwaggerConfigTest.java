package com.microservice.ecomarket.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SwaggerConfigTest {

    @Test
    public void testCustomOpenAPI() {
        // Arrange
        SwaggerConfig swaggerConfig = new SwaggerConfig();

        // Act
        OpenAPI openAPI = swaggerConfig.customOpenAPI();

        // Assert
        assertNotNull(openAPI);
        Info info = openAPI.getInfo();
        assertNotNull(info);
        assertEquals("EcoMarket Microservice API", info.getTitle());
        assertEquals("1.0", info.getVersion());
        assertTrue(info.getDescription().contains("Documentación de la API"));
    }
}

