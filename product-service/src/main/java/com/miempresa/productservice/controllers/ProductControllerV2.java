package com.miempresa.productservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/products/v2")
public class ProductControllerV2 {

    private final RestTemplate restTemplate;

    @Autowired
    public ProductControllerV2(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/payments")
    public String getMessage(){

        String url = "http://payments-service/payments/message";

        return restTemplate.getForObject(url, String.class);
    }
}
