package com.example.decorate.services.wallpaper;

import com.example.decorate.domain.*;
import com.example.decorate.repositorys.wallpaper.WallpaperAttributeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
@Transactional
public class WallpaperAttributeService {
    private final WallpaperAttributeRepository wallpaperAttributeRepository;

    public void saveWallpaperAttributes(Wallpaper wallpaper, List<Attribute> attributeList) {
        KeyHolder keyHolder = wallpaper.getKey();
        for (Attribute attribute : attributeList) {
            wallpaperAttributeRepository.save(new WallpaperAttribute(attribute, wallpaper, keyHolder));
        }
    }

    public List<WallpaperAttribute> findAllWallpaperAttributeByWallpaperId(Long wallpaperId) {
        return wallpaperAttributeRepository.fetchAllByWallpaperId(wallpaperId);
    }

    public void updateWallpaperAttributes(Wallpaper wallpaper, List<Attribute> attributeList) {
        List<Long> activeWallpaperAttributeIdList = new ArrayList<>();
        Long wallpaperId = wallpaper.getId();
        for (Attribute attribute : attributeList) {
            KeyHolder key = wallpaper.getKey();
            Long attributeId = attribute.getId();

            Optional<WallpaperAttribute> wallpaperAttribute = wallpaperAttributeRepository
                    .fetchByAttributeIdAndWallpaperId(attributeId, wallpaperId);

            WallpaperAttribute persistentWallpaperAttribute = wallpaperAttribute
                    .orElseGet(this::createNewPersistentWallpaperAttribute);
            updateWallpaperAttribute(wallpaper, attribute, key, persistentWallpaperAttribute);

            Long activeWallpaperAttributeId = persistentWallpaperAttribute.getId();
            activeWallpaperAttributeIdList.add(activeWallpaperAttributeId);
        }
        wallpaperAttributeRepository.deleteWallpaperNotUsedAttributes(activeWallpaperAttributeIdList, wallpaperId);
    }

    private void updateWallpaperAttribute(Wallpaper wallpaper, Attribute attribute, KeyHolder key, WallpaperAttribute persistentWallpaperAttribute) {
        persistentWallpaperAttribute.setAttribute(attribute);
        persistentWallpaperAttribute.setWallpaper(wallpaper);
        persistentWallpaperAttribute.setKey(key);
        persistentWallpaperAttribute.setModified(Instant.now());
    }

    private WallpaperAttribute createNewPersistentWallpaperAttribute() {
        WallpaperAttribute wallpaperAttribute = new WallpaperAttribute();
        wallpaperAttributeRepository.save(wallpaperAttribute);
        return wallpaperAttribute;
    }

    public void deleteAllByWallpaperId(Long wallpaperId) {
        wallpaperAttributeRepository.deleteAllByWallpaperId(wallpaperId);
    }
}
