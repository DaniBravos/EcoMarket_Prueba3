package com.microservice.product.controller;

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

import com.microservice.product.model.Product;
import com.microservice.product.service.IProductService;

import io.swagger.v3.oas.annotations.tags.Tag;






@RestController
@RequestMapping("/api/v1/product")
@Tag(name = "Controlador de Productos", description = "Operaciones del microservicio de productos de Eco-Market")
public class productController {


    @Autowired
    private IProductService iProductService;

    

    @PostMapping("/create")  //Agregar de Producto
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<Product> saveProduct(@RequestBody Product product){
        iProductService.save(product);
        return EntityModel.of(product,
            linkTo(methodOn(productController.class).findById(product.getId())).withSelfRel(),
            linkTo(methodOn(productController.class).findAllProduct()).withRel("all-products")
        );
    }


    @GetMapping("/all")
    @Tag(name = "Controlador de Listado de Productos", description = "Proporciona el listado completo de productos disponibles en el sistema Eco-Market")
    public CollectionModel<EntityModel<Product>> findAllProduct(){
        List<EntityModel<Product>> products = iProductService.findAll().stream()
            .map(product -> EntityModel.of(product,
                linkTo(methodOn(productController.class).findById(product.getId())).withSelfRel(),
                linkTo(methodOn(productController.class).deleteProduct(product.getId())).withRel("delete")
            ))
            .collect(Collectors.toList());
        return CollectionModel.of(products,
            linkTo(methodOn(productController.class).findAllProduct()).withSelfRel()
        );
    }
    


    @GetMapping("/search/{id}") 
    @Tag(name = "Controlador de Búsqueda por ID", description = "Permite buscar productos específicos mediante su id único en Eco-Market")   
    public EntityModel<Product> findById(@PathVariable Long id){
        Product product = iProductService.findById(id);
        return EntityModel.of(product,
            linkTo(methodOn(productController.class).findById(id)).withSelfRel(),
            linkTo(methodOn(productController.class).findAllProduct()).withRel("all-products"),
            linkTo(methodOn(productController.class).deleteProduct(id)).withRel("delete")
        );
    }

    //localhost:8090/api/v1/product/search{idEcoMarket}


    @GetMapping("/search-EcoMarket/{idEcoMarket}")
    public CollectionModel<EntityModel<Product>> findProductByIdEcoMarket(@PathVariable Long idEcoMarket) {
        List<Product> products = iProductService.findProductByIdEcoMarket(idEcoMarket);
        List<EntityModel<Product>> productModels = products.stream()
            .map(product -> EntityModel.of(product,
                linkTo(methodOn(productController.class).findById(product.getId())).withSelfRel(),
                linkTo(methodOn(productController.class).findAllProduct()).withRel("all-products")
            ))
            .collect(Collectors.toList());
        return CollectionModel.of(productModels,
            linkTo(methodOn(productController.class).findProductByIdEcoMarket(idEcoMarket)).withSelfRel(),
            linkTo(methodOn(productController.class).findAllProduct()).withRel("all-products")
        );
    }


    @DeleteMapping("/delete/{id}")
    @Tag(name = "Controlador de Eliminación de Producto", description = "Permite eliminar un producto específico del sistema Eco-Market mediante su ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public EntityModel<String> deleteProduct(@PathVariable Long id) {
        iProductService.delete(id);
        return EntityModel.of("Producto eliminado",
            linkTo(methodOn(productController.class).findAllProduct()).withRel("all-products")
        );
    }

}
