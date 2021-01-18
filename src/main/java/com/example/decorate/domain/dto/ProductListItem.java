package com.example.decorate.domain.dto;

import com.example.decorate.domain.Curtain;
import com.example.decorate.domain.Product;
import com.example.decorate.domain.Wallpaper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductListItem {

    private Long id;

    private String name;

    private String typeOfSize;

    private String productDesc;

    private String itemNumber;

    private Integer price;

    private String image;
    private Integer qty;
    private List<ImageModel> images;

    public ProductListItem(Wallpaper wallpaper, ImageModel image) {
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

    public ProductListItem(Curtain curtain, ImageModel image) {
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

    public ProductListItem(Product product) {
        this.qty = product.getQuantity();
        this.productDesc = product.getProductDesc();
        this.itemNumber = product.getItemNumber();
        this.price = product.getPrice();
        this.name = product.getName();
        this.typeOfSize = product.getTypeOfSize();
    }
}
