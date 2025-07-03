package com.microservice.ecomarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.ecomarket.client.ProductClient;
import com.microservice.ecomarket.dto.ProductDTO;
import com.microservice.ecomarket.http.response.ProductByEcoMarketResponse;
import com.microservice.ecomarket.model.EcoMarket;
import com.microservice.ecomarket.repository.IEcoMarketRepository;

@Service
public class EcoMarketServiceImpl implements IEcoMarketService {

    @Autowired
    private IEcoMarketRepository ecoMarketRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    public List<EcoMarket> findAll() {
        return (List<EcoMarket>) ecoMarketRepository.findAll();
    }

    @Override
    public EcoMarket findById(Long id) {
        return ecoMarketRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(EcoMarket ecoMarket) {
        ecoMarketRepository.save(ecoMarket);
    }
    
    @Override
    public void delete(Long id) {
    ecoMarketRepository.deleteById(id);
    }

    @Override
    public ProductByEcoMarketResponse findProductByIdEcoMarket(Long idEcoMarket) {
        // Consultar la sucursal (antes EcoMarket)
        EcoMarket sucursal = ecoMarketRepository.findById(idEcoMarket).orElse(new EcoMarket());

        // Obtener los productos asociados a la sucursal
        List<ProductDTO> productDTOList = productClient.findAllProductByEcoMarket(idEcoMarket);

        return ProductByEcoMarketResponse.builder()
                .ecoMarketName(sucursal.getNombre()) // Antes getName()
                .jefeNombre(sucursal.getJefeNombre())          // Antes getTeacher()
                .productDTOList(productDTOList)
                .build();
    }
}
