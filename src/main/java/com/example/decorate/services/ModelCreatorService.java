package com.example.decorate.services;

import com.example.decorate.domain.*;
import com.example.decorate.domain.dto.CurtainModel;
import com.example.decorate.domain.dto.DecorationModel;
import com.example.decorate.domain.dto.FurnitureFabricModel;
import com.example.decorate.domain.dto.WallpaperModel;
import com.example.decorate.mapper.CurtainMapper;
import com.example.decorate.mapper.DecorationMapper;
import com.example.decorate.mapper.FurnitureFabricMapper;
import com.example.decorate.mapper.WallpaperMapper;
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
    private final DecorationMapper decorationMapper;
    private final WallpaperMapper wallpaperMapper;
    private final FurnitureFabricMapper furnitureFabricMapper;


    public CurtainModel createCurtainModel(Curtain curtain) {
        ProductKey curtainProductKey = curtain.getProductKey();
        CurtainModel curtainModel = curtainMapper.curtainToCurtainModel(curtain);

        curtainModel.setAttributes(dtoMapperService.getProductAllAttributesModel(curtainProductKey));
        curtainModel.setImageList(dtoMapperService.getProductAllImageModels(curtainProductKey));

        return curtainModel;
    }

    public WallpaperModel createWallpaperModel(Wallpaper wallpaper) {
        ProductKey wallpaperProductKey = wallpaper.getProductKey();
        WallpaperModel wallpaperModel = wallpaperMapper.wallpaperToWallpaperModel(wallpaper);

        wallpaperModel.setAttributes(dtoMapperService.getProductAllAttributesModel(wallpaperProductKey));
        wallpaperModel.setImageList(dtoMapperService.getProductAllImageModels(wallpaperProductKey));

        return wallpaperModel;
    }

    public FurnitureFabricModel createFurnitureFabricModel(FurnitureFabric furnitureFabric) {
        ProductKey furnitureFabricProductKey = furnitureFabric.getProductKey();
        FurnitureFabricModel furnitureFabricModel = furnitureFabricMapper
                .furnitureFabricToFurnitureFabricModel(furnitureFabric);

        furnitureFabricModel.setAttributes(dtoMapperService.getProductAllAttributesModel(furnitureFabricProductKey));
        furnitureFabricModel.setImageList(dtoMapperService.getProductAllImageModels(furnitureFabricProductKey));

        return furnitureFabricModel;
    }

    public DecorationModel createDecorationModel(Decoration decoration) {
        ProductKey decorationProductKey = decoration.getProductKey();
        DecorationModel decorationModel = decorationMapper.decorationToDecorationModel(decoration);

        decorationModel.setAttributes(dtoMapperService.getProductAllAttributesModel(decorationProductKey));
        decorationModel.setImageList(dtoMapperService.getProductAllImageModels(decorationProductKey));

        return decorationModel;
    }
}
