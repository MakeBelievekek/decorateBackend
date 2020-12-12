package com.example.decorate.services.wallpaper;


import com.example.decorate.domain.Attribute;
import com.example.decorate.domain.KeyHolder;
import com.example.decorate.domain.ProductType;
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
    private final KeyHolderService keyHolderService;
    private final ModelCreatorService modelCreatorService;
    private final EntityUpdateService entityUpdateService;


    public void saveWallpaper(ProductCreationFormData productCreationFormData) {
        KeyHolder keyHolder = new KeyHolder();
        keyHolderService.saveKey(keyHolder, WALLPAPER);

        Wallpaper wallpaper = new Wallpaper(productCreationFormData, keyHolder);
        wallpaperRepository.save(wallpaper);

        List<AttributeCreationFormData> attributeCreationFormDataList = productCreationFormData.getAttributeCreationFormDataList();
        attributeService.createWallpaperAttributes(wallpaper, attributeCreationFormDataList);

        List<ImageModel> imageList = productCreationFormData.getImageList();
        imageService.saveImageList(imageList, keyHolder.getId(), WALLPAPER);
    }

    public WallpaperModel getWallpaper(Long wallpaperId) {
        Wallpaper wallpaper = getWallpaperById(wallpaperId);
        return modelCreatorService.createWallpaperModel(wallpaper);
    }

    public List<WallpaperModel> getAllWallpapers() {
        List<Wallpaper> allWallpapers = wallpaperRepository.findAll();
        List<WallpaperModel> wallpaperModels = new ArrayList<>();

        for (Wallpaper wallpaper : allWallpapers) {
            wallpaperModels.add(modelCreatorService.createWallpaperModel(wallpaper));
        }
        return wallpaperModels;
    }

    public void updateWallpaper(Long wallpaperId, WallpaperModel wallpaperModel) {
        Wallpaper wallpaper = getWallpaperById(wallpaperId);

        entityUpdateService.setWallpaperUpdateValues(wallpaper, wallpaperModel);
        attributeService.updateWallpaperAttributes(wallpaper, wallpaperModel.getAttributes());
        imageService.updateProductImages(wallpaperId, wallpaperModel.getImageList(), WALLPAPER);

        wallpaperRepository.save(wallpaper);
    }

    public void deleteWallpaper(Long wallpaperId) {
        Wallpaper wallpaper = getWallpaperById(wallpaperId);

        imageService.deleteProductImages(wallpaperId);
        attributeService.deleteProductAttributeItems(wallpaper);
        keyHolderService.deleteKeyHolder(wallpaperId);

        wallpaperRepository.delete(wallpaper);
    }

    private Wallpaper getWallpaperById(Long wallpaperId) {
        return wallpaperRepository.findById(wallpaperId)
                .orElseThrow(() -> new DecorateBackendException(WALLPAPER_NOT_EXISTS.getMessage()));
    }


}
