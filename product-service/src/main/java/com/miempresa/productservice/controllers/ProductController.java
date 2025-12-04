package com.miempresa.productservice.controllers;

import com.miempresa.productservice.domain.Product;
import com.miempresa.productservice.exceptions.ResourceNotFoundException;
import com.miempresa.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<?> getProducts(){

        return productService.getProducts().isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Integer id){

        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Product with id " + id + " not found"));
    }

    @PostMapping
    public ResponseEntity<?> postProduct(@RequestBody Product product, UriComponentsBuilder builder){

        Product productCreated = productService.postProduct(product);

        URI location = builder.path("/products/{id}")
                .buildAndExpand(productCreated.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<?> putProduct(@RequestBody Product product){

        if (productService.getProductById(product.getId()).isPresent()){
            productService.putProduct(product);

            return ResponseEntity.noContent().build();
        }

        throw new ResourceNotFoundException("Product with id " + product.getId() + " not found");
    }

    @PatchMapping
    public ResponseEntity<?> patchProduct(@RequestBody Product product){

        if (productService.getProductById(product.getId()).isPresent()){
            productService.patchProduct(product);

            return ResponseEntity.noContent().build();
        }

        throw new ResourceNotFoundException("Product with id " + product.getId() + " not found");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id){

        if (productService.getProductById(id).isPresent()){
            productService.deleteProduct(id);

            return ResponseEntity.noContent().build();
        }

        throw new ResourceNotFoundException("Product with id " + id + " not found");
    }
}
