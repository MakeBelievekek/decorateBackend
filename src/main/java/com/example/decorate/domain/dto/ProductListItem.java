package com.example.decorate.domain.dto;

import com.example.decorate.domain.Product;

public class ProductListItem {

    Long id;
    String productDesc;
    Integer price;
    String productImg;

    public ProductListItem() {
    }

    public ProductListItem(Product product) {
        this.id = product.getId();
        this.productDesc = product.getProductDesc();
        this.price = product.getPrice();
        this.productImg = product.getImgUrl();
    }

    public ProductListItem(Long id, String productDesc, Integer price, String productImg) {
        this.id = id;
        this.productDesc = productDesc;
        this.price = price;
        this.productImg = productImg;
    }

    public Long getId() {
        return id;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public Integer getPrice() {
        return price;
    }

    public String getProductImg() {
        return productImg;
    }
}
