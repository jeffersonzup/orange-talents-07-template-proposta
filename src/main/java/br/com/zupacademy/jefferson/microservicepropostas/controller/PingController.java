package br.com.zupacademy.jefferson.microservicepropostas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class PingController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void ping(){
        System.out.println("Tudo funcionando!");
        System.out.println("Live Reload funcionando");
        System.out.println("Está rodando bonitinho kkkkkkkkkk");
    }
}
