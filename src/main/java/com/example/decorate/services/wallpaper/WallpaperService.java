package com.example.decorate.services.wallpaper;


import com.example.decorate.domain.ProductKey;
import com.example.decorate.domain.Wallpaper;
import com.example.decorate.domain.dto.AttributeCreationFormData;
import com.example.decorate.domain.dto.ImageModel;
import com.example.decorate.domain.dto.ProductCreationFormData;
import com.example.decorate.domain.dto.WallpaperModel;
import com.example.decorate.exception.DecorateBackendException;
import com.example.decorate.repositorys.wallpaper.WallpaperRepository;
import com.example.decorate.services.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import static com.example.decorate.domain.ProductType.WALLPAPER;
import static com.example.decorate.exception.ExceptionMessages.WALLPAPER_NOT_EXISTS;

@Slf4j
@AllArgsConstructor
@Service
@Transactional

public class WallpaperService {
    private final WallpaperRepository wallpaperRepository;
    private final ImageService imageService;
    private final AttributeService attributeService;
    private final ProductKeyService productKeyService;
    private final ModelCreatorService modelCreatorService;
    private final EntityUpdateService entityUpdateService;
    private final EntityCreatorService entityCreatorService;


    public void saveWallpaper(ProductCreationFormData productCreationFormData) {
        ProductKey wallpaperProductKey = new ProductKey();
        productKeyService.saveKey(wallpaperProductKey, WALLPAPER);

        Wallpaper wallpaper = entityCreatorService
                .createWallpaperFromCreationModel(productCreationFormData, wallpaperProductKey);
        wallpaperRepository.save(wallpaper);

        List<AttributeCreationFormData> attributeCreationFormDataList = productCreationFormData
                .getAttributeCreationFormDataList();
        attributeService.createProductAttributeItems(attributeCreationFormDataList, wallpaperProductKey);

        List<ImageModel> imageList = productCreationFormData.getImageList();
        imageService.saveImageList(imageList, wallpaperProductKey);
    }

    public WallpaperModel getWallpaper(Long wallpaperId) {
        Wallpaper wallpaper = getWallpaperById(wallpaperId);
        return modelCreatorService.createWallpaperModel(wallpaper);
    }

    public List<WallpaperModel> getAllWallpapers() {
        List<Wallpaper> allWallpapers = wallpaperRepository.findAll();

        return allWallpapers.stream()
                .map(modelCreatorService::createWallpaperModel)
                .collect(Collectors.toList());
    }

    public void updateWallpaper(Long wallpaperId, WallpaperModel wallpaperModel) {
        Wallpaper wallpaper = getWallpaperById(wallpaperId);
        ProductKey wallpaperProductKey = wallpaper.getProductKey();

        entityUpdateService.setWallpaperUpdateValues(wallpaper, wallpaperModel);
        attributeService.updateProductAttributes(wallpaperProductKey, wallpaperModel.getAttributes());
        imageService.updateProductImages(wallpaperProductKey, wallpaperModel.getImageList());

        wallpaperRepository.save(wallpaper);
    }

    public void deleteWallpaper(Long wallpaperId) {
        Wallpaper wallpaper = getWallpaperById(wallpaperId);
        ProductKey wallpaperProductKey = wallpaper.getProductKey();

        imageService.deleteProductImages(wallpaperProductKey);
        attributeService.deleteProductAttributeItems(wallpaperProductKey);
        productKeyService.deleteKeyHolder(wallpaperProductKey);

        wallpaperRepository.delete(wallpaper);
    }

    private Wallpaper getWallpaperById(Long wallpaperId) {
        return wallpaperRepository.findById(wallpaperId)
                .orElseThrow(() -> new DecorateBackendException(WALLPAPER_NOT_EXISTS.getMessage()));
    }
}
