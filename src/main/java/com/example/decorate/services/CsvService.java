package com.example.decorate.services;

import com.example.decorate.domain.ProductType;
import com.example.decorate.domain.dto.ProductCreationFormData;
import com.example.decorate.services.curtain.CurtainService;
import com.example.decorate.services.furniture.FurnitureFabricService;
import com.example.decorate.services.wallpaper.WallpaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class CsvService {

    WallpaperService wallpaperService;
    CurtainService curtainService;
    FurnitureFabricService furnitureFabricService;

    @Autowired
    public CsvService(WallpaperService wallpaperService, CurtainService curtainService, FurnitureFabricService furnitureFabricService) {
        this.wallpaperService = wallpaperService;
        this.curtainService = curtainService;
        this.furnitureFabricService = furnitureFabricService;
    }

    public void save(MultipartFile file) {
        try {
            List<ProductCreationFormData> products = CsvResolver.csvPars(file.getInputStream());
            for (ProductCreationFormData product : products) {
                if (product.getProductType().equals(ProductType.WALLPAPER.toString()))
                    wallpaperService.saveWallpaper(product);
                if (product.getProductType().equals(ProductType.CURTAIN.toString()))
                    curtainService.saveCurtain(product);
                if (product.getProductType().equals(ProductType.FURNITURE_FABRIC.toString()))
                    furnitureFabricService.saveFurnitureFabric(product);
            }
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

}
