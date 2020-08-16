package com.example.decorate.controller;

import com.example.decorate.service.KeyHolderService;
import com.example.decorate.service.WallpaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallpaper")

public class WallpaperController {
    private WallpaperService wallpaperService;
    private KeyHolderService keyHolderService;

    @Autowired
    public WallpaperController(WallpaperService wallpaperService, KeyHolderService keyHolderService) {
        this.wallpaperService = wallpaperService;
        this.keyHolderService = keyHolderService;
    }

    @GetMapping
    public ResponseEntity saveWallpaper() {
        wallpaperService.saveWallpaper(keyHolderService.saveKey());
        return new ResponseEntity(HttpStatus.OK);
    }

}
