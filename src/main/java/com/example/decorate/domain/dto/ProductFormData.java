package com.example.decorate.domain.dto;

import com.example.decorate.domain.Product;

public class ProductFormData {

    String productName;
    String productDesc;
    String productCategory;
    Long categoryId;
    String price;
    String productImg;



    public String getPrice() {
        return price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public String getProductImg() {
        return productImg;
    }
}
