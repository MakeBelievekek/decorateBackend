package com.example.decorate.domain.dto;

import com.example.decorate.domain.Wallpaper;

import java.util.List;

public class ProductListItem {

    private Long id;
    private String productDesc;
    private int itemNumber;
    private int price;
    private String image;

    public ProductListItem() {
    }

    public ProductListItem(Wallpaper wallpaper, ImageData image) {
        this.id = wallpaper.getId();
        this.productDesc = wallpaper.getProductDesc();
        this.itemNumber = wallpaper.getItemNumber();
        this.price = wallpaper.getPrice();
        this.image = image.getImgUrl();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
