/*
package com.example.decorate.controller;

import com.example.decorate.domain.dto.ProductListItem;
import com.example.decorate.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/local/{ids}")
    public ResponseEntity getProductsForLocalStorage(@PathVariable("ids") String productsIds) {

        List<ProductListItem> products = productService.getProductsFromLocal(productsIds);

        return new ResponseEntity(products, HttpStatus.OK);
    }


}
*/
