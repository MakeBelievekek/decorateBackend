package com.example.decorate.service;


import com.example.decorate.domain.KeyHolder;
import com.example.decorate.domain.Wallpaper;
import com.example.decorate.domain.dto.WallpaperFormData;
import com.example.decorate.repository.WallpaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional

public class WallpaperService {
    private WallpaperRepository wallpaperRepository;

    @Autowired
    public WallpaperService(WallpaperRepository wallpaperRepository) {
        this.wallpaperRepository = wallpaperRepository;
    }


    public void saveWallpaper(WallpaperFormData wallpaperFormData,KeyHolder keyHolder) {
        Wallpaper wallpaper = new Wallpaper(wallpaperFormData,keyHolder);
        wallpaperRepository.save(wallpaper);

    }



}
