package com.example.decorate.controller;

import com.example.decorate.domain.KeyHolder;
import com.example.decorate.domain.ProductType;
import com.example.decorate.domain.dto.ProductCreationFormData;
import com.example.decorate.service.AttributeService;
import com.example.decorate.service.ImageService;
import com.example.decorate.service.KeyHolderService;
import com.example.decorate.service.WallpaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/wallpaper")

public class WallpaperController {
    private WallpaperService wallpaperService;
    private KeyHolderService keyHolderService;
    private AttributeService attributeService;
    private ImageService imageService;

    @Autowired
    public WallpaperController(WallpaperService wallpaperService, KeyHolderService keyHolderService, AttributeService attributeService, ImageService imageService) {
        this.wallpaperService = wallpaperService;
        this.keyHolderService = keyHolderService;
        this.attributeService = attributeService;
        this.imageService = imageService;
    }

    @PostMapping
    public ProductCreationFormData saveWallpaper(@RequestBody ProductCreationFormData productCreationFormData) {
        System.out.println(productCreationFormData);
        KeyHolder keyholder = new KeyHolder();
        keyHolderService.saveKey(keyholder, ProductType.WALLPAPER);
        wallpaperService.saveWallpaper(productCreationFormData, keyholder);
        attributeService.saveAttributeListItem(productCreationFormData.getAttributeListItemData(), keyholder.getId());
        imageService.saveImageList(productCreationFormData.getImageList(), keyholder.getId(),ProductType.WALLPAPER);
        return new ProductCreationFormData();
    }


}
