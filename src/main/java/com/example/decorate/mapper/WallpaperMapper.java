package com.example.decorate.mapper;

import com.example.decorate.domain.Wallpaper;
import com.example.decorate.domain.ProductKey;
import com.example.decorate.domain.dto.WallpaperModel;
import com.example.decorate.domain.dto.ProductCreationFormData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface WallpaperMapper {
    @Mapping(target = "attributes", ignore = true)
    @Mapping(target = "imageList", ignore = true)
    WallpaperModel wallpaperToWallpaperModel(Wallpaper wallpaper);

    Wallpaper productCreationToWallpaper(ProductCreationFormData productCreationFormData, ProductKey productKey);

    @Mapping(target = "modified", expression = "java(java.time.Instant.now())")
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "productKey", ignore = true)
    void updateWallpaperFields(@MappingTarget Wallpaper wallpaper, WallpaperModel wallpaperModel);
}