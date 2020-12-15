package com.example.decorate.services;

import com.example.decorate.domain.*;
import com.example.decorate.domain.dto.ProductCreationFormData;
import com.example.decorate.mapper.CurtainMapper;
import com.example.decorate.mapper.DecorationMapper;
import com.example.decorate.mapper.FurnitureFabricMapper;
import com.example.decorate.mapper.WallpaperMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EntityCreatorService {
    private final CurtainMapper curtainMapper;
    private final DecorationMapper decorationMapper;
    private final WallpaperMapper wallpaperMapper;
    private final FurnitureFabricMapper furnitureFabricMapper;

    public Curtain createCurtainFromCreationModel(ProductCreationFormData productCreationFormData, ProductKey productKey) {
        return curtainMapper.productCreationToCurtain(productCreationFormData, productKey);
    }

    public Decoration createDecorationFromCreationModel(ProductCreationFormData productCreationFormData, ProductKey productKey) {
        return decorationMapper.productCreationToDecoration(productCreationFormData, productKey);
    }

    public Wallpaper createWallpaperFromCreationModel(ProductCreationFormData productCreationFormData, ProductKey productKey) {
        return wallpaperMapper.productCreationToWallpaper(productCreationFormData, productKey);
    }

    public FurnitureFabric createFurnitureFabricFromCreationModel(ProductCreationFormData productCreationFormData, ProductKey productKey) {
        return furnitureFabricMapper.productCreationToFurnitureFabric(productCreationFormData, productKey);
    }
}
