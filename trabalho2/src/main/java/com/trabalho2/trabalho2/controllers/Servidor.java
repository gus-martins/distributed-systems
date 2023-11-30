package com.trabalho2.trabalho2.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.trabalho2.trabalho2", "com.trabalho2.trabalho2.entities" })
public class Servidor {

    public static void main(String[] args) {
        SpringApplication.run(Servidor.class, args);
    }

}
