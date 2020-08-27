package com.example.decorate.controller;

import com.example.decorate.domain.KeyHolder;
import com.example.decorate.domain.ProductType;
import com.example.decorate.domain.dto.ProductFormData;
import com.example.decorate.service.AttributeService;
import com.example.decorate.service.CurtainService;
import com.example.decorate.service.ImageService;
import com.example.decorate.service.KeyHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/curtain")
public class CurtainController {

    private CurtainService curtainService;
    private KeyHolderService keyHolderService;
    private AttributeService attributeService;
    private ImageService imageService;

    @Autowired
    public CurtainController(CurtainService curtainService, KeyHolderService keyHolderService, AttributeService attributeService, ImageService imageService) {
        this.curtainService = curtainService;
        this.keyHolderService = keyHolderService;
        this.attributeService = attributeService;
        this.imageService = imageService;
    }

    @PostMapping
    public ProductFormData saveWallpaper(@RequestBody ProductFormData productFormData) {
        System.out.println(productFormData);
        KeyHolder keyholder = new KeyHolder();
        keyHolderService.saveKey(keyholder, ProductType.CURTAIN);
        curtainService.saveCurtain(productFormData, keyholder);
        attributeService.saveAttributeListItem(productFormData.getAttributeListItemData(), keyholder.getId());
        imageService.saveImage(productFormData.getImageList(), keyholder.getId(), ProductType.CURTAIN);
        return new ProductFormData();
    }
}
