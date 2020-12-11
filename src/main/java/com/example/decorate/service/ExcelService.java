package com.example.decorate.service;

import com.example.decorate.domain.Curtain;
import com.example.decorate.domain.KeyHolder;
import com.example.decorate.domain.ProductType;
import com.example.decorate.domain.Wallpaper;
import com.example.decorate.domain.dto.ExcelData;
import com.example.decorate.repository.CurtainRepository;
import com.example.decorate.repository.WallpaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class ExcelService {


    CurtainRepository curtainRepository;
    WallpaperRepository wallpaperRepository;
    KeyHolderService keyHolderService;
    ImageService imageService;
    AttributeService attributeService;

    @Autowired
    public ExcelService(CurtainRepository curtainRepository, WallpaperRepository wallpaperRepository,
                        KeyHolderService keyHolderService, ImageService imageService, AttributeService attributeService) {
        this.curtainRepository = curtainRepository;
        this.wallpaperRepository = wallpaperRepository;
        this.keyHolderService = keyHolderService;
        this.imageService = imageService;
        this.attributeService = attributeService;
    }

    public void save(MultipartFile file) {
        try {
            List<ExcelData> products = ExcelHelper.excelToTutorials(file.getInputStream());
            System.out.println(products);
            for (ExcelData product : products) {
                KeyHolder keyHolder = new KeyHolder();
                switch (product.getTypeOfProduct()) {
                    case "WALLPAPER":
                        keyHolderService.saveKey(keyHolder, ProductType.WALLPAPER);
                        Wallpaper wallpaper = new Wallpaper(product, keyHolder);
                        wallpaperRepository.save(wallpaper);
                        imageService.saveImageList(product.getImageList(), wallpaper.getId(), ProductType.WALLPAPER);
                        attributeService.saveAttributesFromExcel(product.getAttributes(), keyHolder);
                        break;
                    case "CURTAIN":
                        keyHolderService.saveKey(keyHolder, ProductType.CURTAIN);
                        Curtain curtain = new Curtain(product, keyHolder);
                        curtainRepository.save(curtain);
                        imageService.saveImageList(product.getImageList(), curtain.getId(), ProductType.CURTAIN);
                        attributeService.saveAttributesFromExcel(product.getAttributes(), keyHolder);
                        break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

}
