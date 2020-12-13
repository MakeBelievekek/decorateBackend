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

import java.time.Instant;

@Slf4j
@AllArgsConstructor
@Service
@Transactional

public class EntityUpdateService {

    public void setCurtainUpdatedValues(Curtain curtain, CurtainModel curtainModel) {
        curtain.setName(curtainModel.getName());
        curtain.setProductDesc(curtainModel.getProductDesc());
        curtain.setWidth(curtainModel.getWidth());
        curtain.setHeight(curtainModel.getHeight());
        curtain.setItemNumber(curtainModel.getItemNumber());
        curtain.setPatternRep(curtainModel.getPatternRep());
        curtain.setPrice(curtainModel.getPrice());
        curtain.setComposition(curtainModel.getComposition());
        curtain.setProductFamily(curtainModel.getProductFamily());
        curtain.setCleaningInst(curtainModel.getCleaningInst());
        curtain.setTypeOfSize(curtainModel.getTypeOfSize());
        curtain.setModified(Instant.now());
    }

    public void setWallpaperUpdateValues(Wallpaper wallpaper, WallpaperModel wallpaperModel) {
        wallpaper.setId(wallpaperModel.getId());
        wallpaper.setName(wallpaperModel.getName());
        wallpaper.setProductDesc(wallpaperModel.getProductDesc());
        wallpaper.setItemNumber(wallpaperModel.getItemNumber());
        wallpaper.setWidth(wallpaperModel.getWidth());
        wallpaper.setHeight(wallpaperModel.getHeight());
        wallpaper.setPatternRep(wallpaperModel.getPatternRep());
        wallpaper.setPrice(wallpaperModel.getPrice());
        wallpaper.setComposition(wallpaperModel.getComposition());
        wallpaper.setProductFamily(wallpaperModel.getProductFamily());
        wallpaper.setAnnotation(wallpaperModel.getAnnotation());
        wallpaper.setRecommendedGlue(wallpaperModel.getRecommendedGlue());
        wallpaper.setModified(Instant.now());
    }

    public void setFurnitureFabricUpdatedValues(FurnitureFabric furnitureFabric, FurnitureFabricModel furnitureFabricModel) {
        furnitureFabric.setId(furnitureFabricModel.getId());
        furnitureFabric.setName(furnitureFabricModel.getName());
        furnitureFabric.setProductDesc(furnitureFabricModel.getProductDesc());
        furnitureFabric.setWidth(furnitureFabricModel.getWidth());
        furnitureFabric.setHeight(furnitureFabricModel.getHeight());
        furnitureFabric.setItemNumber(furnitureFabricModel.getItemNumber());
        furnitureFabric.setPatternRep(furnitureFabricModel.getPatternRep());
        furnitureFabric.setPrice(furnitureFabricModel.getPrice());
        furnitureFabric.setComposition(furnitureFabricModel.getComposition());
        furnitureFabric.setProductFamily(furnitureFabricModel.getProductFamily());
        furnitureFabric.setCleaningInst(furnitureFabricModel.getCleaningInst());
        furnitureFabric.setAbrasionResistance(furnitureFabricModel.getAbrasionResistance());
        furnitureFabric.setTypeOfSize(furnitureFabricModel.getTypeOfSize());
        furnitureFabric.setModified(Instant.now());
    }

    public void setDecorationUpdatedValues(Decoration decoration, DecorationModel decorationModel) {
        decoration.setId(decorationModel.getId());
        decoration.setName(decorationModel.getName());
        decoration.setProductDesc(decorationModel.getProductDesc());
        decoration.setItemNumber(decorationModel.getItemNumber());
        decoration.setWidth(decorationModel.getWidth());
        decoration.setHeight(decorationModel.getHeight());
        decoration.setPatternRep(decorationModel.getPatternRep());
        decoration.setPrice(decorationModel.getPrice());
        decoration.setComposition(decorationModel.getComposition());
        decoration.setProductFamily(decorationModel.getProductFamily());
        decoration.setAnnotation(decorationModel.getAnnotation());
        decoration.setRecommendedGlue(decorationModel.getRecommendedGlue());
        decoration.setModified(Instant.now());
    }
}
