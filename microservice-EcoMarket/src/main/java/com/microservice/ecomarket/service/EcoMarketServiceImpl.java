package com.microservice.ecomarket.service;


import com.microservice.ecomarket.model.EcoMarket;
import com.microservice.ecomarket.http.response.ProductByEcoMarketResponse;
import com.microservice.ecomarket.repository.IEcoMarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EcoMarketServiceImpl implements IEcoMarketService {

    @Autowired
    private IEcoMarketRepository ecoMarketRepository;

    @Override
    public List<EcoMarket> findAll() {
        return ecoMarketRepository.findAll();
    }

    @Override
    public EcoMarket findById(Long id) {
        return ecoMarketRepository.findById(id).orElse(null);
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
        // Implementa aquí la lógica real según tu modelo y necesidades
        return new ProductByEcoMarketResponse();
    }
}
