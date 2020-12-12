package com.example.decorate.controller;

import com.example.decorate.services.CalculatorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/public/calculator")
public class CalculatorController {

    private final CalculatorService calculatorService;

    @GetMapping
    public ResponseEntity<Integer> getResult() {
        return ResponseEntity
                .status(OK)
                .body(this.calculatorService.getOneToTwoGore());
    }
}
