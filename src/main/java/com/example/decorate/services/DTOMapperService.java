package com.example.decorate.services;

import com.example.decorate.domain.*;
import com.example.decorate.domain.dto.AttributeModel;
import com.example.decorate.domain.dto.ImageModel;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
@Service
@Transactional
public class DTOMapperService {
    private final AttributeService attributeService;
    private final ImageService imageService;

    public <V> List<AttributeModel> getProductAllAttributesModel(V product) {
        List<AttributeModel> attributeModels = null;

        String productClass = product.getClass().toString();

        final String curtainClass = Curtain.class.toString();
        final String wallpaperClass = Wallpaper.class.toString();
        final String furnitureFabricClass = FurnitureFabric.class.toString();
        final String decorationClass = Decoration.class.toString();

        if (productClass.equals(curtainClass)) {
            Curtain curtain = (Curtain) product;
            Long curtainId = curtain.getId();
            List<CurtainAttribute> curtainAttributes = attributeService.fetchAllCurtainAttributes(curtainId);
            attributeModels = convertCurtainAttributesToDTO(curtainAttributes);
        } else if (productClass.equals(wallpaperClass)) {
            Wallpaper wallpaper = (Wallpaper) product;

            Long wallpaperId = wallpaper.getId();
            List<WallpaperAttribute> wallpaperAttributes = attributeService.fetchAllWallpaperAttributes(wallpaperId);

            attributeModels = convertWallpaperAttributesToDTO(wallpaperAttributes);
        } else if (productClass.equals(furnitureFabricClass)) {
            FurnitureFabric furnitureFabric = (FurnitureFabric) product;

            Long furnitureFabricId = furnitureFabric.getId();
            List<FurnitureFabricAttribute> furnitureFabricAttributes = attributeService.fetchAllFurnitureFabricAttributes(furnitureFabricId);

            attributeModels = convertFurnitrueFabricAttributesToDTO(furnitureFabricAttributes);
        } else if (productClass.equals(decorationClass)) {
            Decoration decoration = (Decoration) product;

            Long decorationId = decoration.getId();
            List<DecorationAttribute> decorationAttributes = attributeService.fetchAllDecorationAttributes(decorationId);

            attributeModels = convertDecorationAttributesToDTO(decorationAttributes);
        }
        return attributeModels;
    }

    private List<AttributeModel> convertDecorationAttributesToDTO(List<DecorationAttribute> decorationAttributes) {
        List<AttributeModel> attributes = new ArrayList<>();
        for (DecorationAttribute decorationAttribute : decorationAttributes) {
            attributes.add(new AttributeModel(decorationAttribute));
        }
        return attributes;
    }

    private List<AttributeModel> convertFurnitrueFabricAttributesToDTO(List<FurnitureFabricAttribute> furnitureFabricAttributes) {
        List<AttributeModel> attributes = new ArrayList<>();
        for (FurnitureFabricAttribute furnitureFabricAttribute : furnitureFabricAttributes) {
            attributes.add(new AttributeModel(furnitureFabricAttribute));
        }
        return attributes;
    }

    private List<AttributeModel> convertCurtainAttributesToDTO(List<CurtainAttribute> curtainAttributes) {
        List<AttributeModel> attributes = new ArrayList<>();
        for (CurtainAttribute curtainAttribute : curtainAttributes) {
            attributes.add(new AttributeModel(curtainAttribute));
        }
        return attributes;
    }

    private List<AttributeModel> convertWallpaperAttributesToDTO(List<WallpaperAttribute> wallpaperAttributes) {
        List<AttributeModel> attributes = new ArrayList<>();
        for (WallpaperAttribute wallpaperAttribute : wallpaperAttributes) {
            attributes.add(new AttributeModel(wallpaperAttribute));
        }
        return attributes;
    }

    public List<ImageModel> getProductAllImageModels(Long productId) {
        List<Image> imagesByProductId = getProductImagesByProductId(productId);
        return convertImagesToDTO(imagesByProductId);
    }

    private List<Image> getProductImagesByProductId(Long curtainId) {
        return imageService.getImagesByProductId(curtainId);
    }

    private List<ImageModel> convertImagesToDTO(List<Image> imagesByProductId) {
        List<ImageModel> images = new ArrayList<>();
        for (Image image : imagesByProductId) {
            images.add(new ImageModel(image));
        }
        return images;
    }
}
