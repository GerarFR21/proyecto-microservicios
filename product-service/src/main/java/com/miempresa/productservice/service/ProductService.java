package com.miempresa.productservice.service;

import com.miempresa.productservice.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getProducts();

    Optional<Product> getProductById(Integer id);

    Product postProduct(Product product);

    void putProduct(Product product);

    void patchProduct(Product product);

    void deleteProduct(Integer id);
}
