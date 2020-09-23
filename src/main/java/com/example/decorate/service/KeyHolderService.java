package com.example.decorate.service;

import com.example.decorate.domain.*;
import com.example.decorate.domain.dto.ImageData;
import com.example.decorate.domain.dto.ProductListItem;
import com.example.decorate.repository.CurtainRepository;
import com.example.decorate.repository.ImageRepository;
import com.example.decorate.repository.KeyHolderRepository;
import com.example.decorate.repository.WallpaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class KeyHolderService {

    private KeyHolderRepository keyHolderRepository;
    private CurtainRepository curtainRepository;
    private WallpaperRepository wallpaperRepository;
    private ImageRepository imageRepository;


    @Autowired
    public KeyHolderService(KeyHolderRepository keyHolderRepository, CurtainRepository curtainRepository, WallpaperRepository wallpaperRepository, ImageRepository imageRepository) {
        this.keyHolderRepository = keyHolderRepository;

        this.curtainRepository = curtainRepository;
        this.wallpaperRepository = wallpaperRepository;
        this.imageRepository = imageRepository;
    }

    public KeyHolder saveKey(KeyHolder keyHolder, ProductType productType) {
        keyHolder.setType(productType);
        keyHolderRepository.save(keyHolder);
        return keyHolder;
    }

    public List<KeyHolder> getProductsFromLocal(String productIds) {
        List<KeyHolder> keyHolders = new ArrayList<>();
        String[] ids = productIds.split(",");
        List<Long> productsLong = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            productsLong.add(Long.parseLong(ids[i]));
        }
        for (long id : productsLong) {
            Optional<KeyHolder> keyHolder = keyHolderRepository.findById(id);
            keyHolder.ifPresent(keyHolders::add);
        }
        return keyHolders;
    }

    public List<KeyHolder> getKeyholders(List<Long> itemId) {

        List<KeyHolder> keys = new ArrayList<>();
        for (Long aLong : itemId) {
            Optional<KeyHolder> keyHolder = this.keyHolderRepository.findById(aLong);
            keyHolder.ifPresent(keys::add);
        }
        return keys;
    }

    public List<ProductListItem> getProducts(List<KeyHolder> keys) {
        List<ProductListItem> items = new ArrayList<>();
        for (KeyHolder key : keys) {
            if (key.getType() == ProductType.WALLPAPER) {
                Optional<Wallpaper> wallpaper = this.wallpaperRepository.findById(key.getId());
                if (wallpaper.isPresent()) {
                    ImageData image = new ImageData(imageRepository.findByProdKeyAndImageType(key.getId(), ImageType.PRIMARY_KEY));
                    items.add(new ProductListItem(wallpaper.get(), image));
                }
            }
            if (key.getType() == ProductType.CURTAIN) {
                Optional<Curtain> curtain = this.curtainRepository.findById(key.getId());
                if (curtain.isPresent()) {
                    ImageData image = new ImageData(imageRepository.findByProdKeyAndImageType(key.getId(), ImageType.PRIMARY_KEY));
                    items.add(new ProductListItem(curtain.get(), image));
                }
            }
        }
        return items;
    }

    public ProductListItem getProd(Long id) {
        Optional<KeyHolder> keyHolder = this.keyHolderRepository.findById(id);
        if (keyHolder.isPresent()) {
            if (keyHolder.get().getType() == ProductType.WALLPAPER) {
                Optional<Wallpaper> wallpaper = this.wallpaperRepository.findById(keyHolder.get().getId());
                if (wallpaper.isPresent()) {
                    return new ProductListItem(wallpaper.get());
                }
            }
            if (keyHolder.get().getType() == ProductType.CURTAIN) {
                Optional<Curtain> curtain = this.curtainRepository.findById(keyHolder.get().getId());
                if (curtain.isPresent()) {
                    return new ProductListItem(curtain.get());
                }
            }
        }
        return null;
    }

}
