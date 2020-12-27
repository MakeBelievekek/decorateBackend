package com.example.decorate.mapper;

import com.example.decorate.domain.FurnitureFabric;
import com.example.decorate.domain.ProductKey;
import com.example.decorate.domain.Wallpaper;
import com.example.decorate.domain.dto.FurnitureFabricModel;
import com.example.decorate.domain.dto.ProductCreationFormData;
import com.example.decorate.domain.dto.WallpaperModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface WallpaperMapper {
    @Mapping(source = "images", target = "imageList")
    @Mapping(source = "attributeItems", target = "attributes")
    WallpaperModel wallpaperToWallpaperModel(Wallpaper wallpaper);

    @Mapping(target = "images", ignore = true)
    @Mapping(target = "attributeItems", ignore = true)
    @Mapping(source = "productKey.id", target = "id")
    @Mapping(source = "productKey", target = "productKey")
    Wallpaper productCreationToWallpaper(ProductCreationFormData productCreationFormData, ProductKey productKey);

    @Mapping(target = "images", ignore = true)
    @Mapping(target = "attributeItems", ignore = true)
    @Mapping(target = "typeOfSize", ignore = true)
    @Mapping(target = "modified", expression = "java(java.time.Instant.now())")
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "productKey", ignore = true)
    void updateWallpaperFields(@MappingTarget Wallpaper wallpaper, WallpaperModel wallpaperModel);

    @Mapping(target = "productType", ignore = true)
    @Mapping(target = "curtainType", ignore = true)
    @Mapping(target = "cleaningInst", ignore = true)
    @Mapping(target = "abrasionResistance", ignore = true)

    ProductCreationFormData wallpaperToFormData(Wallpaper wallpaper,
                                                @MappingTarget ProductCreationFormData productCreationFormData);

    @Mapping(target = "productType", ignore = true)
    @Mapping(target = "curtainType", ignore = true)
    @Mapping(target = "cleaningInst", ignore = true)
    @Mapping(target = "abrasionResistance", ignore = true)
    @Mapping(source = "images", target = "imageList")
    @Mapping(source = "attributeItems", target = "attributeCreationFormDataList")
    ProductCreationFormData wallpaperToFormData(Wallpaper wallpaper);

    List<ProductCreationFormData> wallpaperListToFormDataList(List<Wallpaper> wallpaper);

    List<WallpaperModel> wallpaperListToWallpaperModelList(List<Wallpaper> wallpapers);

}
