package com.anz.acorn.mts.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages={"com.anz.acorn.mts.domain"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
    }
  
}