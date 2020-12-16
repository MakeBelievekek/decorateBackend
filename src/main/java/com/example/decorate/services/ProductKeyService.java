package com.example.decorate.services;

import com.example.decorate.domain.*;
import com.example.decorate.domain.dto.ImageModel;
import com.example.decorate.domain.dto.ProductListItem;
import com.example.decorate.repositorys.ProductKeyRepository;
import com.example.decorate.repositorys.curtain.CurtainRepository;
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

public class ProductKeyService {

    private final ProductKeyRepository productKeyRepository;
    private final CurtainRepository curtainRepository;
    private final WallpaperRepository wallpaperRepository;
    private final ImageService imageService;

    public ProductKey saveKey(ProductKey productKey, ProductType productType) {
        productKey.setType(productType);
        productKeyRepository.save(productKey);
        return productKey;
    }

    public List<ProductKey> getProductsFromLocal(String productIds) {
        List<ProductKey> productKeys = new ArrayList<>();
        String[] ids = productIds.split(",");
        System.out.println(ids);
        List<Long> productsLong = new ArrayList<>();
        for (String s : ids) {
            productsLong.add(Long.parseLong(s));
        }

        productsLong.stream().map(productKeyRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(productKeys::add);
        for (Long aLong : productsLong) {
        }
        return productKeys;
    }

    public List<ProductKey> getKeyholders(List<Long> itemId) {

        List<ProductKey> keys = new ArrayList<>();

        for (Long aLong : itemId) {
            Optional<ProductKey> keyHolder = this.productKeyRepository.findById(aLong);
            keyHolder.ifPresent(keys::add);
        }
        return keys;
    }

    public List<ProductListItem> getProducts(List<ProductKey> keys) {
        List<ProductListItem> items = new ArrayList<>();

        for (ProductKey productKey : keys) {
            if (productKey.getType() == WALLPAPER) {
                Optional<Wallpaper> byId = this.wallpaperRepository.findById(productKey.getId());
                if (byId.isPresent()) {
                    Wallpaper wallpaper = byId.get();
                    List<Image> images = imageService.getImagesByProductId(productKey);
                    images.forEach(img -> {
                        if (img.getImageType() == ImageType.PRIMARY_IMG) {
                            ProductListItem productListItem = new ProductListItem(wallpaper, new ImageModel(img));
                            items.add(productListItem);
                        }
                    });
                }
            }
            if (productKey.getType() == CURTAIN){
                Optional<Curtain> byId = this.curtainRepository.findById(productKey.getId());
                if (byId.isPresent()) {
                    Curtain curtain = byId.get();
                    List<Image> images = imageService.getImagesByProductId(productKey);
                    images.forEach(img -> {
                        if (img.getImageType() == ImageType.PRIMARY_IMG) {
                            ProductListItem productListItem = new ProductListItem(curtain, new ImageModel(img));
                            items.add(productListItem);
                        }
                    });
                }
            }
        }
        return items;
    }

    public ProductListItem getProd(Long id) {
        Optional<ProductKey> keyHolder = this.productKeyRepository.findById(id);
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

    public void deleteKeyHolder(ProductKey productKey) {
        productKeyRepository.delete(productKey);
    }
}
