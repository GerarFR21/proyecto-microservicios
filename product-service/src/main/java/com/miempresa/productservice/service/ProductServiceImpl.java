package com.miempresa.productservice.service;

import com.miempresa.productservice.domain.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProductServiceImpl implements ProductService{

    private List<Product> products = new ArrayList<>(Arrays.asList(
            new Product(1, "Laptop Dell", 700.0, 20),
            new Product(2, "Mouse Inalambrico", 10.0, 50),
            new Product(3, "Smartphone Samsung A15", 200.0, 30),
            new Product(4, "SmartTV Panasonic", 500.0, 45)
    ));

    private final AtomicInteger newId;

    public ProductServiceImpl(){
        this.newId = new AtomicInteger(products.size());
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public Optional<Product> getProductById(Integer id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public Product postProduct(Product product) {

        product.setId(newId.incrementAndGet());
        products.add(product);
        return product;
    }

    @Override
    public void putProduct(Product product) {
        Product productFound = products.stream()
                .filter(p -> p.getId().equals(product.getId()))
                .findFirst()
                .orElseThrow();

        productFound.setName(product.getName());
        productFound.setPrice(product.getPrice());
        productFound.setStock(product.getStock());
    }

    @Override
    public void patchProduct(Product product) {
        Product productFound = products.stream()
                .filter(p -> p.getId().equals(product.getId()))
                .findFirst()
                .orElseThrow();

        Optional.ofNullable(product.getName())
                .ifPresent(productFound::setName);

        Optional.ofNullable(product.getPrice())
                .ifPresent(productFound::setPrice);

        Optional.ofNullable(product.getStock())
                .ifPresent(productFound::setStock);
    }

    @Override
    public void deleteProduct(Integer id) {
        Product productFound = products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow();

        products.remove(productFound);
    }
}
