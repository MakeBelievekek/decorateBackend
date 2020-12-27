package com.example.decorate.services;

import com.example.decorate.domain.*;
import com.example.decorate.domain.dto.*;
import com.example.decorate.mapper.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
@Transactional
public class ModelCreatorService {
    private final CurtainMapper curtainMapper;
    private final DecorationMapper decorationMapper;
    private final WallpaperMapper wallpaperMapper;
    private final FurnitureFabricMapper furnitureFabricMapper;
    private final ImageMapper imageMapper;
    private final AttributeMapper attributeMapper;
    private final AttributeItemMapper attributeItemMapper;

    public CurtainModel createCurtainModel(Curtain curtain) {
        CurtainModel curtainModel = curtainMapper.curtainToCurtainModel(curtain);
        List<AttributeModel> curtainAttributeModelList = curtain.getAttributeItems().stream()
                .map(AttributeItem::getAttribute)
                .map(attributeMapper::createAttributeModelFromAttribute)
                .collect(Collectors.toList());
        curtainModel.setAttributes(curtainAttributeModelList);

        return curtainModel;
    }

    public List<CurtainModel> curtainListToCurtainModelList(List<Curtain> curtains) {
        List<CurtainModel> result = new ArrayList<>();
        for (Curtain curtain : curtains) {
            result.add(createCurtainModel(curtain));
        }
        return result;
    }

    public List<ProductCreationFormData> curtainListToProductCreationFormData(List<Curtain> curtains) {
        List<ProductCreationFormData> result = new ArrayList<>();

        for (Curtain curtain : curtains) {
            ProductCreationFormData productCreationFormData = curtainMapper.curtainToFormData(curtain);

            List<AttributeCreationFormData> curtainAttributeModelList = curtain.getAttributeItems().stream()
                    .map(AttributeItem::getAttribute)
                    .map(attributeMapper::createAttributeFormData)
                    .collect(Collectors.toList());

            productCreationFormData.setAttributeCreationFormDataList(curtainAttributeModelList);

            result.add(productCreationFormData);
        }
        return result;
    }
    public WallpaperModel createWallpaperModel(Wallpaper wallpaper) {
        WallpaperModel wallpaperModel = wallpaperMapper.wallpaperToWallpaperModel(wallpaper);
        List<AttributeModel> wallpaperAttributeModelList = wallpaper.getAttributeItems().stream()
                .map(AttributeItem::getAttribute)
                .map(attributeMapper::createAttributeModelFromAttribute)
                .collect(Collectors.toList());
        wallpaperModel.setAttributes(wallpaperAttributeModelList);

        return wallpaperModel;
    }

    public List<WallpaperModel> wallpaperListToWallpaperModelList(List<Wallpaper> wallpapers) {
        List<WallpaperModel> result = new ArrayList<>();
        for (Wallpaper wallpaper : wallpapers) {
            result.add(createWallpaperModel(wallpaper));
        }
        return result;
    }

    public List<ProductCreationFormData> wallpaperListToProductCreationFormDataList(List<Wallpaper> wallpapers) {
        List<ProductCreationFormData> result = new ArrayList<>();

        for (Wallpaper wallpaper : wallpapers) {
            ProductCreationFormData productCreationFormData = wallpaperMapper.wallpaperToFormData(wallpaper);

            List<AttributeCreationFormData> wallpaperAttributeModelList = wallpaper.getAttributeItems().stream()
                    .map(AttributeItem::getAttribute)
                    .map(attributeMapper::createAttributeFormData)
                    .collect(Collectors.toList());

            productCreationFormData.setAttributeCreationFormDataList(wallpaperAttributeModelList);

            result.add(productCreationFormData);
        }
        return result;
    }

    public FurnitureFabricModel createFurnitureFabricModel(FurnitureFabric furnitureFabric) {
        FurnitureFabricModel furnitureFabricModel = furnitureFabricMapper.furnitureFabricToFurnitureFabricModel(furnitureFabric);
        List<AttributeModel> furnitureFabricAttributeModelList = furnitureFabric.getAttributeItems().stream()
                .map(AttributeItem::getAttribute)
                .map(attributeMapper::createAttributeModelFromAttribute)
                .collect(Collectors.toList());
        furnitureFabricModel.setAttributes(furnitureFabricAttributeModelList);

        return furnitureFabricModel;
    }

    public List<FurnitureFabricModel> furnitureFabricListToFurnitureFabricModelList(List<FurnitureFabric> furnitureFabrics) {
        List<FurnitureFabricModel> result = new ArrayList<>();
        for (FurnitureFabric furnitureFabric : furnitureFabrics) {
            result.add(createFurnitureFabricModel(furnitureFabric));
        }
        return result;
    }

    public List<ProductCreationFormData> furnitureFabricListToProductCreationFormDataList(List<FurnitureFabric> furnitureFabrics) {
        List<ProductCreationFormData> result = new ArrayList<>();

        for (FurnitureFabric furnitureFabric : furnitureFabrics) {
            ProductCreationFormData productCreationFormData = furnitureFabricMapper.furnitureFabricToFormData(furnitureFabric);

            List<AttributeCreationFormData> furnitureFabricAttributeModelList = furnitureFabric.getAttributeItems().stream()
                    .map(AttributeItem::getAttribute)
                    .map(attributeMapper::createAttributeFormData)
                    .collect(Collectors.toList());

            productCreationFormData.setAttributeCreationFormDataList(furnitureFabricAttributeModelList);

            result.add(productCreationFormData);
        }
        return result;
    }

    public DecorationModel createDecorationModel(Decoration decoration) {
        DecorationModel decorationModel = decorationMapper.decorationToDecorationModel(decoration);
        List<AttributeModel> decorationAttributeModelList = decoration.getAttributeItems().stream()
                .map(AttributeItem::getAttribute)
                .map(attributeMapper::createAttributeModelFromAttribute)
                .collect(Collectors.toList());
        decorationModel.setAttributes(decorationAttributeModelList);

        return decorationModel;
    }

    public List<DecorationModel> decorationListToDecorationModelList(List<Decoration> decorations) {
        List<DecorationModel> result = new ArrayList<>();
        for (Decoration decoration : decorations) {
            result.add(createDecorationModel(decoration));
        }
        return result;
    }

    public List<ProductCreationFormData> decorationListToProductCreationFormDataList(List<Decoration> decorations) {
        List<ProductCreationFormData> result = new ArrayList<>();

        for (Decoration decoration : decorations) {
            ProductCreationFormData productCreationFormData = decorationMapper.decorationToFormData(decoration);

            List<AttributeCreationFormData> decorationAttributeModelList = decoration.getAttributeItems().stream()
                    .map(AttributeItem::getAttribute)
                    .map(attributeMapper::createAttributeFormData)
                    .collect(Collectors.toList());

            productCreationFormData.setAttributeCreationFormDataList(decorationAttributeModelList);

            result.add(productCreationFormData);
        }
        return result;
    }

    public AttributeModel createAttributeModelFromAttribute(Attribute attribute) {
        return attributeMapper.createAttributeModelFromAttribute(attribute);
    }
}
