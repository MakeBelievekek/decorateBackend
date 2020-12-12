package com.example.decorate.services;

import com.example.decorate.domain.Curtain;
import com.example.decorate.domain.Wallpaper;
import com.example.decorate.domain.dto.CurtainModel;
import com.example.decorate.domain.dto.WallpaperModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    }
}
