package com.example.decorate.services;

import com.example.decorate.domain.Curtain;
import com.example.decorate.domain.Decoration;
import com.example.decorate.domain.FurnitureFabric;
import com.example.decorate.domain.Wallpaper;
import com.example.decorate.domain.dto.CurtainModel;
import com.example.decorate.domain.dto.DecorationModel;
import com.example.decorate.domain.dto.FurnitureFabricModel;
import com.example.decorate.domain.dto.WallpaperModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
@Service
@Transactional
public class ModelCreatorService {
    private final DTOMapperService dtoMapperService;

    public CurtainModel createCurtainModel(Curtain curtain) {
        Long curtainId = curtain.getId();
        CurtainModel curtainModel = new CurtainModel(curtain);
        curtainModel.setAttributes(dtoMapperService.getProductAllAttributesModel(curtain));
        curtainModel.setImageList(dtoMapperService.getProductAllImageModels(curtainId));
        return curtainModel;
    }

    public WallpaperModel createWallpaperModel(Wallpaper wallpaper) {
        Long wallpaperId = wallpaper.getId();
        WallpaperModel wallpaperModel = new WallpaperModel(wallpaper);
        wallpaperModel.setAttributes(dtoMapperService.getProductAllAttributesModel(wallpaper));
        wallpaperModel.setImageList(dtoMapperService.getProductAllImageModels(wallpaperId));
        return wallpaperModel;
    }

    public FurnitureFabricModel createFurnitureFabricModel(FurnitureFabric furnitureFabric) {
        Long furnitureFabricId = furnitureFabric.getId();
        FurnitureFabricModel furnitureFabricModel = new FurnitureFabricModel(furnitureFabric);
        furnitureFabricModel.setAttributes(dtoMapperService.getProductAllAttributesModel(furnitureFabric));
        furnitureFabricModel.setImageList(dtoMapperService.getProductAllImageModels(furnitureFabricId));
        return furnitureFabricModel;
    }

    public DecorationModel createDecorationModel(Decoration decoration) {
        Long decorationId = decoration.getId();
        DecorationModel decorationModel = new DecorationModel(decoration);
        decorationModel.setAttributes(dtoMapperService.getProductAllAttributesModel(decoration));
        decorationModel.setImageList(dtoMapperService.getProductAllImageModels(decorationId));
        return decorationModel;
    }
}
