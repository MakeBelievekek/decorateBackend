
package com.example.decorate.controller;

import com.example.decorate.domain.ProductKey;
import com.example.decorate.domain.dto.ProductCategoryModalDto;
import com.example.decorate.domain.dto.ProductCreationFormData;
import com.example.decorate.domain.dto.ProductListItem;
import com.example.decorate.services.ProductKeyService;
import com.example.decorate.services.ShippingOptionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/public/product")
public class ProductController {

    private final ProductKeyService productKeyService;
    private final ShippingOptionService shippingOptionService;

    @GetMapping("/productsWithQuery")
    @ResponseBody
    public ResponseEntity<List<ProductCreationFormData>> getProductsWithQuery(@RequestParam String productCategory,
                                                                              @RequestParam List<Long> attrs) {
        List<ProductCreationFormData> productsByFilter = productKeyService.getProductsByFilter(productCategory, attrs);
        return new ResponseEntity<>(productsByFilter, HttpStatus.OK);
    }

    @GetMapping("/products")
    @ResponseBody
    public ResponseEntity<List<ProductCreationFormData>> getProducts(@RequestParam String productCategory) {
        List<ProductCreationFormData> productsWithType = productKeyService.getProductsWithCurtainSubType(productCategory);

        return new ResponseEntity<>(productsWithType, HttpStatus.OK);
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

    @GetMapping("/productTypes")
    public ResponseEntity<List<ProductCategoryModalDto>> getProductTypesAndAttributes() {

        return ResponseEntity.status(HttpStatus.OK).body(productKeyService.getAllProductTypeWithAttributes());
    }

}

