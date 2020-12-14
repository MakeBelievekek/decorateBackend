package com.example.decorate.services;

import com.example.decorate.domain.*;
import com.example.decorate.domain.dto.CurtainModel;
import com.example.decorate.domain.dto.DecorationModel;
import com.example.decorate.domain.dto.FurnitureFabricModel;
import com.example.decorate.domain.dto.WallpaperModel;
import com.example.decorate.mapper.CurtainMapper;
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
    private final CurtainMapper curtainMapper;


    public CurtainModel createCurtainModel(Curtain curtain) {
        ProductKey curtainProductKey = curtain.getProductKey();
        CurtainModel curtainModel = curtainMapper.curtainToCurtainModel(curtain);
        curtainModel.setAttributes(dtoMapperService.getProductAllAttributesModel(curtainProductKey));
        curtainModel.setImageList(dtoMapperService.getProductAllImageModels(curtainProductKey));

        return curtainModel;
    }

    public WallpaperModel createWallpaperModel(Wallpaper wallpaper) {
        ProductKey wallpaperProductKey = wallpaper.getProductKey();

        WallpaperModel wallpaperModel = new WallpaperModel(wallpaper);
        wallpaperModel.setAttributes(dtoMapperService.getProductAllAttributesModel(wallpaperProductKey));
        wallpaperModel.setImageList(dtoMapperService.getProductAllImageModels(wallpaperProductKey));

        return wallpaperModel;
    }

    public FurnitureFabricModel createFurnitureFabricModel(FurnitureFabric furnitureFabric) {
        ProductKey furnitureFabricProductKey = furnitureFabric.getProductKey();

        FurnitureFabricModel furnitureFabricModel = new FurnitureFabricModel(furnitureFabric);
        furnitureFabricModel.setAttributes(dtoMapperService.getProductAllAttributesModel(furnitureFabricProductKey));
        furnitureFabricModel.setImageList(dtoMapperService.getProductAllImageModels(furnitureFabricProductKey));

        return furnitureFabricModel;
    }

    public DecorationModel createDecorationModel(Decoration decoration) {
        ProductKey decorationProductKey = decoration.getProductKey();

        DecorationModel decorationModel = new DecorationModel(decoration);
        decorationModel.setAttributes(dtoMapperService.getProductAllAttributesModel(decorationProductKey));
        decorationModel.setImageList(dtoMapperService.getProductAllImageModels(decorationProductKey));

        return decorationModel;
    }
}
