package com.example.decorate.services;

import com.example.decorate.domain.Curtain;
import com.example.decorate.domain.Decoration;
import com.example.decorate.domain.FurnitureFabric;
import com.example.decorate.domain.Wallpaper;
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

import java.time.Instant;

@Slf4j
@AllArgsConstructor
@Service
@Transactional

public class EntityUpdateService {

    private final CurtainMapper curtainMapper;
    private final DecorationMapper decorationMapper;
    private final WallpaperMapper wallpaperMapper;
    private final FurnitureFabricMapper furnitureFabricMapper;

    public void setCurtainUpdatedValues(Curtain curtain, CurtainModel curtainModel) {
        curtainMapper.updateCurtainFields(curtain, curtainModel);
    }

    public void setWallpaperUpdateValues(Wallpaper wallpaper, WallpaperModel wallpaperModel) {
        wallpaperMapper.updateWallpaperFields(wallpaper, wallpaperModel);
    }

    public void setFurnitureFabricUpdatedValues(FurnitureFabric furnitureFabric, FurnitureFabricModel furnitureFabricModel) {
       furnitureFabricMapper.updateFurnitureFabricFields(furnitureFabric, furnitureFabricModel);
    }

    public void setDecorationUpdatedValues(Decoration decoration, DecorationModel decorationModel) {
        decorationMapper.updateDecorationFields(decoration, decorationModel);
    }
}
