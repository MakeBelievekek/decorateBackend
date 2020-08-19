
package com.example.decorate.controller;

import com.example.decorate.domain.KeyHolder;
import com.example.decorate.domain.ProductType;
import com.example.decorate.domain.dto.ProductListItem;
import com.example.decorate.service.KeyHolderService;
import com.example.decorate.service.WallpaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private KeyHolderService keyHolderService;
    private WallpaperService wallpaperService;

    @Autowired
    public ProductController(KeyHolderService keyHolderService, WallpaperService wallpaperService) {

        this.keyHolderService = keyHolderService;
        this.wallpaperService = wallpaperService;
    }

    @GetMapping("/local/{ids}")
    public ResponseEntity getProductsForLocalStorage(@PathVariable("ids") String productsIds) {
        List<ProductListItem> products = new ArrayList<>();
        List<KeyHolder> keyHolders = keyHolderService.getProductsFromLocal(productsIds);
        for (KeyHolder keyHolder : keyHolders) {
            if (keyHolder.getType() == ProductType.WALLPAPER) {
                products.add(wallpaperService.getWallpaper(keyHolder));
            }
        }
        return new ResponseEntity(products, HttpStatus.OK);
    }


}

