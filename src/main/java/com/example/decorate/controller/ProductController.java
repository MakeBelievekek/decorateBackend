
package com.example.decorate.controller;

import com.example.decorate.domain.KeyHolder;
import com.example.decorate.domain.dto.ProductListItem;
import com.example.decorate.service.CurtainService;
import com.example.decorate.service.KeyHolderService;
import com.example.decorate.service.ShippingOptionService;
import com.example.decorate.service.WallpaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public/product")
public class ProductController {

    private KeyHolderService keyHolderService;
    private WallpaperService wallpaperService;
    private CurtainService curtainService;
    private ShippingOptionService shippingOptionService;

    @Autowired
    public ProductController(KeyHolderService keyHolderService, WallpaperService wallpaperService, CurtainService curtainService, ShippingOptionService shippingOptionService) {

        this.keyHolderService = keyHolderService;
        this.wallpaperService = wallpaperService;
        this.curtainService = curtainService;
        this.shippingOptionService = shippingOptionService;
    }

    @GetMapping("/local/{ids}")
    public ResponseEntity getProductsForLocalStorage(@PathVariable("ids") String productsIds) {
        List<KeyHolder> keyHolders = keyHolderService.getProductsFromLocal(productsIds);
        List<ProductListItem> products = this.keyHolderService.getProducts(keyHolders);

        return new ResponseEntity(products, HttpStatus.OK);
    }

    @GetMapping("/shippingOptions")
    public ResponseEntity getAllShippingOptions() {
        return new ResponseEntity(this.shippingOptionService.getAllOption(), HttpStatus.OK);
    }


}

