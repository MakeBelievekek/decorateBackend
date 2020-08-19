package com.example.decorate.controller;

import com.example.decorate.domain.KeyHolder;
import com.example.decorate.domain.ProductType;
import com.example.decorate.domain.dto.WallpaperFormData;
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
@RequestMapping("/wallpaper")

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
    public WallpaperFormData saveWallpaper(@RequestBody WallpaperFormData wallpaperFormData) {
        KeyHolder keyholder = new KeyHolder();
        keyHolderService.saveKey(keyholder, ProductType.WALLPAPER);
        wallpaperService.saveWallpaper(wallpaperFormData, keyholder);
        attributeService.saveAttributeListItem(wallpaperFormData.getAttributeListItemData(), keyholder.getId());
        imageService.saveImage(wallpaperFormData.getImageList(), keyholder.getId(),ProductType.WALLPAPER);
        return new WallpaperFormData();
    }


}
