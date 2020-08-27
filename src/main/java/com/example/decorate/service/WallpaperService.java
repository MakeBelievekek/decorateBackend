package com.example.decorate.service;


import com.example.decorate.domain.ImageType;
import com.example.decorate.domain.KeyHolder;
import com.example.decorate.domain.Wallpaper;
import com.example.decorate.domain.dto.ImageData;
import com.example.decorate.domain.dto.ProductFormData;
import com.example.decorate.domain.dto.ProductListItem;
import com.example.decorate.repository.ImageRepository;
import com.example.decorate.repository.WallpaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional

public class WallpaperService {
    private WallpaperRepository wallpaperRepository;
    private ImageRepository imageRepository;

    @Autowired
    public WallpaperService(WallpaperRepository wallpaperRepository, ImageRepository imageRepository) {
        this.wallpaperRepository = wallpaperRepository;
        this.imageRepository = imageRepository;
    }


    public void saveWallpaper(ProductFormData productFormData, KeyHolder keyHolder) {
        Wallpaper wallpaper = new Wallpaper(productFormData, keyHolder);
        wallpaperRepository.save(wallpaper);
    }

    public ProductListItem getWallpaper(KeyHolder keyHolder) {

        Optional<Wallpaper> wallpaper = wallpaperRepository.findById(keyHolder.getId());
        if (wallpaper.isPresent()) {
            ImageData image = new ImageData(imageRepository.findByProdKeyAndImageType(keyHolder.getId(), ImageType.PRIMARY_KEY));
            return new ProductListItem(wallpaper.get(), image);
        } else {
            return null;
        }
    }


}
