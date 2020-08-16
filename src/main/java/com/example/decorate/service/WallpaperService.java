package com.example.decorate.service;


import com.example.decorate.domain.KeyHolder;
import com.example.decorate.domain.Wallpaper;
import com.example.decorate.repository.WallpaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class WallpaperService {
    private WallpaperRepository wallpaperRepository;

    @Autowired
    public WallpaperService(WallpaperRepository wallpaperRepository) {
        this.wallpaperRepository = wallpaperRepository;
    }


    public void saveWallpaper(KeyHolder keyHolder) {
        Wallpaper wallpaper = new Wallpaper();
        wallpaper.setId(keyHolder.getId());
        wallpaper.setName("Valami");
        wallpaper.setProductDesc("nemtom");
        wallpaperRepository.save(wallpaper);
    }

}
