package com.miempresa.paymentsservice.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/payments")
public class PaymentsController {

    @Value("${mensaje}")
    private String message;

    @GetMapping("/message")
    public String getMessage(){

        return message;
    }
}
