package com.example.decorate.controller;

import com.example.decorate.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/public/home")
public class HomeController {

    private HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping
    public ResponseEntity getAllHomeImage() {
        CacheControl cacheControl = CacheControl.maxAge(31536000, TimeUnit.SECONDS)
                .noTransform()
                .mustRevalidate();
        return ResponseEntity.ok().cacheControl(cacheControl).body(homeService.getAllHomeImg());
    }
}
