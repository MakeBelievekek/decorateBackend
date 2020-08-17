package com.example.decorate.controller;

import com.example.decorate.domain.KeyHolder;
import com.example.decorate.domain.ProductType;
import com.example.decorate.domain.dto.WallpaperFormData;
import com.example.decorate.service.AttributeService;
import com.example.decorate.service.KeyHolderService;
import com.example.decorate.service.WallpaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallpaper")

public class WallpaperController {
    private WallpaperService wallpaperService;
    private KeyHolderService keyHolderService;
    private AttributeService attributeService;

    @Autowired
    public WallpaperController(WallpaperService wallpaperService, KeyHolderService keyHolderService, AttributeService attributeService) {
        this.wallpaperService = wallpaperService;
        this.keyHolderService = keyHolderService;
        this.attributeService = attributeService;

    }

    @PostMapping
    public ResponseEntity saveWallpaper(WallpaperFormData wallpaperFormData) {
        System.out.println(wallpaperFormData.getAttributeListItemData());
        System.out.println(wallpaperFormData);
        KeyHolder keyholder = new KeyHolder();
        keyHolderService.saveKey(keyholder, ProductType.WALLPAPER);
        wallpaperService.saveWallpaper(wallpaperFormData, keyholder);
        attributeService.saveAttributeListItem(wallpaperFormData.getAttributeListItemData(), keyholder.getId());
        return new ResponseEntity(new WallpaperFormData(),HttpStatus.OK);
    }


}
