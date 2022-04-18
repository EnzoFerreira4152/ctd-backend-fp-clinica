package com.backend.finalProject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/")
public class WelcomeController {

    @GetMapping
    public ResponseEntity<String> sayHi(){
        return ResponseEntity.ok("Hi Stranger, welcome to my Final Project's API");
    }

}
