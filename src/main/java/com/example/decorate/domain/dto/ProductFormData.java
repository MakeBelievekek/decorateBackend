package com.example.decorate.domain.dto;

public class ProductFormData {

    String productName;
    String productDesc;
    String productCategory;
    Long categoryId;
    String productImg;

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
