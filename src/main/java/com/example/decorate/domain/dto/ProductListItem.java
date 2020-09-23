package com.example.decorate.domain.dto;

import com.example.decorate.domain.Curtain;
import com.example.decorate.domain.Wallpaper;
import lombok.Data;

@Data
public class ProductListItem {

    private Long id;
    private String name;
    private String typeOfSize;
    private String productDesc;
    private String itemNumber;
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
        this.name = wallpaper.getName();
    }
    public ProductListItem(Wallpaper wallpaper) {
        this.id = wallpaper.getId();
        this.productDesc = wallpaper.getProductDesc();
        this.itemNumber = wallpaper.getItemNumber();
        this.price = wallpaper.getPrice();
        this.name = wallpaper.getName();
    }

    public ProductListItem(Curtain curtain, ImageData image) {
        this.id = curtain.getId();
        this.productDesc = curtain.getProductDesc();
        this.itemNumber = curtain.getItemNumber();
        this.price = curtain.getPrice();
        this.image = image.getImgUrl();
        this.name = curtain.getName();
        this.typeOfSize = curtain.getTypeOfSize();
    }
    public ProductListItem(Curtain curtain) {
        this.id = curtain.getId();
        this.productDesc = curtain.getProductDesc();
        this.itemNumber = curtain.getItemNumber();
        this.price = curtain.getPrice();
        this.name = curtain.getName();
        this.typeOfSize = curtain.getTypeOfSize();
    }

}
