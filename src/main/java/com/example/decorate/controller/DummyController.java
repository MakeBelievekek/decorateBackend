package com.example.decorate.controller;

import com.example.decorate.domain.Dummy;
import com.example.decorate.services.DummyService;
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
@RequestMapping("/api/public/dummy")
public class DummyController {
    private final DummyService dummyService;


    @GetMapping
    public ResponseEntity<Dummy> getAllDummys() {
        log.info("--------------              Dummy item requested              --------------");
        return ResponseEntity
                .status(OK)
                .body(this.dummyService.getById());
    }
}
