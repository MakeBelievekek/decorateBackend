package com.example.decorate.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/hello")
public class Hello {


    @GetMapping
    public ResponseEntity<String> greetings() {
        return new ResponseEntity<>("hello world", OK);
    }

}
