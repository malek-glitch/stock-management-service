package com.crocostaud.stockmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Controller {


    // 53 044 193 m. manel
    @GetMapping
    private ResponseEntity< String > testMethod(){
        return ResponseEntity.ok("hello world!");
    }

    @GetMapping("/home")
    private ResponseEntity<String> home() {
        return ResponseEntity.ok("hello home!");
    }

    @GetMapping("/dashboard")
    private ResponseEntity<String> dashboard() {
        return ResponseEntity.ok("hello dashboard!");
    }
}
