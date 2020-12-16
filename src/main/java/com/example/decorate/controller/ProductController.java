
package com.example.decorate.controller;

import com.example.decorate.domain.ProductKey;
import com.example.decorate.domain.dto.ProductListItem;
import com.example.decorate.services.curtain.CurtainService;
import com.example.decorate.services.ProductKeyService;
import com.example.decorate.services.ShippingOptionService;
import com.example.decorate.services.wallpaper.WallpaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/product")
public class ProductController {

    private ProductKeyService productKeyService;
    private WallpaperService wallpaperService;
    private CurtainService curtainService;
    private ShippingOptionService shippingOptionService;

    @Autowired
    public ProductController(ProductKeyService productKeyService, WallpaperService wallpaperService, CurtainService curtainService, ShippingOptionService shippingOptionService) {

        this.productKeyService = productKeyService;
        this.wallpaperService = wallpaperService;
        this.curtainService = curtainService;
        this.shippingOptionService = shippingOptionService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity getProducts(@RequestParam String productCategory,
                                      @RequestParam String attrType, @RequestParam String attr) {
        System.out.println(attr);
        return new ResponseEntity(productCategory+"  "+attrType+"  "+attr, HttpStatus.OK);
    }

    @GetMapping("/local/{ids}")
    public ResponseEntity getProductsForLocalStorage(@PathVariable("ids") String productsIds) {
        System.out.println(productsIds);
        List<ProductKey> productKeys = productKeyService.getProductsFromLocal(productsIds);
        List<ProductListItem> products = this.productKeyService.getProducts(productKeys);
        for (ProductListItem product : products) {
            System.out.println(product);
        }
        return new ResponseEntity(products, HttpStatus.OK);
    }

    @GetMapping("/shippingOptions")
    public ResponseEntity getAllShippingOptions() {
        return new ResponseEntity(this.shippingOptionService.getAllOption(), HttpStatus.OK);
    }


}

