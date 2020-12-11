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

    @Autowired
    public ExcelService(CurtainRepository curtainRepository, WallpaperRepository wallpaperRepository,
                        KeyHolderService keyHolderService, ImageService imageService) {
        this.curtainRepository = curtainRepository;
        this.wallpaperRepository = wallpaperRepository;
        this.keyHolderService = keyHolderService;
        this.imageService = imageService;
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
                        imageService.saveImage(product.getImageList(), wallpaper.getId(), ProductType.WALLPAPER);
                        break;
                    case "CURTAIN":
                        keyHolderService.saveKey(keyHolder, ProductType.CURTAIN);
                        Curtain curtain = new Curtain(product, keyHolder);
                        curtainRepository.save(curtain);
                        imageService.saveImage(product.getImageList(), curtain.getId(), ProductType.CURTAIN);
                        break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

}
