package com.microservice.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public void saveProduct(@RequestBody Product product){
        iProductService.save(product);
    }

    @GetMapping("/all")
    @Tag(name = "Controlador de Listado de Productos", description = "Proporciona el listado completo de productos disponibles en el sistema Eco-Market")
    public ResponseEntity<?> findAllProduct(){
        return ResponseEntity.ok(iProductService.findAll());
    }
    

    @GetMapping("/search/{id}") 
    @Tag(name = "Controlador de Búsqueda por ID", description = "Permite buscar productos específicos mediante su id único en Eco-Market")   
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(iProductService.findById(id));
    }

    //localhost:8090/api/v1/product/search-by-course/1
    @GetMapping("/search-EcoMarket/{idEcoMarket}")
    public ResponseEntity<?> findProductByIdEcoMarket(@PathVariable Long idEcoMarket){
         System.out.println("-------------------------------------------------------------------"+ idEcoMarket);
         return ResponseEntity.ok(iProductService.findProductByIdEcoMarket(idEcoMarket));
    }

    @DeleteMapping("/delete/{id}")
    @Tag(name = "Controlador de Eliminación de Producto", description = "Permite eliminar un producto específico del sistema Eco-Market mediante su ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
    iProductService.delete(id);
    } 

}
