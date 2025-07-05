package com.microservice.ecomarket.controller;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.ecomarket.model.EcoMarket;
import com.microservice.ecomarket.service.IEcoMarketService;

@RestController
@RequestMapping("/api/v1/ecomarket")
public class EcoMarketController {

    @Autowired
    private IEcoMarketService ecoMarketService;


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<EcoMarket> saveEcoMarket(@RequestBody EcoMarket ecoMarket) {
        ecoMarketService.save(ecoMarket);
        EntityModel<EcoMarket> resource = EntityModel.of(ecoMarket,
            linkTo(methodOn(EcoMarketController.class).findById(ecoMarket.getId())).withSelfRel(),
            linkTo(methodOn(EcoMarketController.class).findAllEcoMarkets()).withRel("all-ecomarkets")
        );
        return resource;
    }


    @GetMapping("/all")
    public CollectionModel<EntityModel<EcoMarket>> findAllEcoMarkets() {
        List<EntityModel<EcoMarket>> ecoMarkets = ecoMarketService.findAll().stream()
            .map(ecoMarket -> EntityModel.of(ecoMarket,
                linkTo(methodOn(EcoMarketController.class).findById(ecoMarket.getId())).withSelfRel(),
                linkTo(methodOn(EcoMarketController.class).deleteEcoMarket(ecoMarket.getId())).withRel("delete"),
                linkTo(methodOn(EcoMarketController.class).findProductByIdEcoMarket(ecoMarket.getId())).withRel("products")
            ))
            .collect(Collectors.toList());

        return CollectionModel.of(ecoMarkets,
            linkTo(methodOn(EcoMarketController.class).findAllEcoMarkets()).withSelfRel()
        );
    }


    @GetMapping("/search/{id}")
    public EntityModel<EcoMarket> findById(@PathVariable Long id) {
        EcoMarket ecoMarket = ecoMarketService.findById(id);
        return EntityModel.of(ecoMarket,
            linkTo(methodOn(EcoMarketController.class).findById(id)).withSelfRel(),
            linkTo(methodOn(EcoMarketController.class).findAllEcoMarkets()).withRel("all-ecomarkets"),
            linkTo(methodOn(EcoMarketController.class).deleteEcoMarket(id)).withRel("delete"),
            linkTo(methodOn(EcoMarketController.class).findProductByIdEcoMarket(id)).withRel("products")
        );
    }


    @GetMapping("/search-product/{idEcoMarket}")
    public EntityModel<Object> findProductByIdEcoMarket(@PathVariable Long idEcoMarket) {
        Object response = ecoMarketService.findProductByIdEcoMarket(idEcoMarket);
        return EntityModel.of(response,
            linkTo(methodOn(EcoMarketController.class).findProductByIdEcoMarket(idEcoMarket)).withSelfRel(),
            linkTo(methodOn(EcoMarketController.class).findById(idEcoMarket)).withRel("ecomarket")
        );
    }
    

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public EntityModel<String> deleteEcoMarket(@PathVariable Long id) {
        ecoMarketService.delete(id);
        return EntityModel.of("EcoMarket eliminado",
            linkTo(methodOn(EcoMarketController.class).findAllEcoMarkets()).withRel("all-ecomarkets")
        );
    }



}
