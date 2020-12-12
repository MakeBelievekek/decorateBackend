package com.example.decorate.services;

import com.example.decorate.domain.*;
import com.example.decorate.domain.dto.ProductListItem;
import com.example.decorate.repositorys.curtain.CurtainRepository;
import com.example.decorate.repositorys.KeyHolderRepository;
import com.example.decorate.repositorys.wallpaper.WallpaperRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.decorate.domain.ProductType.CURTAIN;
import static com.example.decorate.domain.ProductType.WALLPAPER;

@AllArgsConstructor
@Slf4j
@Service
@Transactional

public class KeyHolderService {

    private final KeyHolderRepository keyHolderRepository;
    private final CurtainRepository curtainRepository;
    private final WallpaperRepository wallpaperRepository;
    private final ImageService imageService;

    public KeyHolder saveKey(KeyHolder keyHolder, ProductType productType) {
        keyHolder.setType(productType);
        keyHolderRepository.save(keyHolder);
        return keyHolder;
    }

    public List<KeyHolder> getProductsFromLocal(String productIds) {
        List<KeyHolder> keyHolders = new ArrayList<>();
        String[] ids = productIds.split(",");
        List<Long> productsLong = new ArrayList<>();
        for (String s : ids) {
            productsLong.add(Long.parseLong(s));
        }

        productsLong.stream().map(keyHolderRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(keyHolders::add);
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

        keys.stream().filter(keyHolder -> keyHolder.getType() == WALLPAPER)
                .map(keyHolder -> this.wallpaperRepository.findById(keyHolder.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(wallpaper -> new ProductListItem(wallpaper, imageService.getProductPrimaryImg(wallpaper.getId())))
                .forEach(items::add);
        keys.stream().filter(keyHolder -> keyHolder.getType() == CURTAIN)
                .map(keyHolder -> this.curtainRepository.findById(keyHolder.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(curtain -> new ProductListItem(curtain, imageService.getProductPrimaryImg(curtain.getId())))
                .forEach(items::add);
/*
        for (KeyHolder key : keys) {
            if (key.getType() == WALLPAPER) {
                Optional<Wallpaper> wallpaper = this.wallpaperRepository.findById(key.getId());
                if (wallpaper.isPresent()) {
                    ImageData image = new ImageData(imageRepository.findByProdKeyAndImageType(key.getId(), ImageType.PRIMARY_KEY));
                    items.add(new ProductListItem(wallpaper.get(), image));
                }
            }
            if (key.getType() == CURTAIN) {
                Optional<Curtain> curtain = this.curtainRepository.findById(key.getId());
                if (curtain.isPresent()) {
                    ImageData image = new ImageData(imageRepository.findByProdKeyAndImageType(key.getId(), ImageType.PRIMARY_KEY));
                    items.add(new ProductListItem(curtain.get(), image));
                }
            }
        }*/

        return items;
    }

    public ProductListItem getProd(Long id) {
        Optional<KeyHolder> keyHolder = this.keyHolderRepository.findById(id);
        if (keyHolder.isPresent()) {
            if (keyHolder.get().getType() == WALLPAPER) {
                Optional<Wallpaper> wallpaper = this.wallpaperRepository.findById(keyHolder.get().getId());
                if (wallpaper.isPresent()) {
                    return new ProductListItem(wallpaper.get());
                }
            }
            if (keyHolder.get().getType() == CURTAIN) {
                Optional<Curtain> curtain = this.curtainRepository.findById(keyHolder.get().getId());
                if (curtain.isPresent()) {
                    return new ProductListItem(curtain.get());
                }
            }
        }
        return null;
    }

    public void deleteKeyHolder(Long productId) {
        keyHolderRepository.deleteById(productId);
    }
}