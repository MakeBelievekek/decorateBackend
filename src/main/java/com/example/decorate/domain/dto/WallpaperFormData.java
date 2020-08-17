package com.example.decorate.domain.dto;

import com.example.decorate.domain.KeyHolder;

import java.util.List;

public class WallpaperFormData {

    private KeyHolder key;
    private String name;
    private String productDesc;
    private int itemNumber;
    private int width;
    private int patternRep;
    private int price;
    private String composition;
    private List<AttributeListItemData> attributeListItemData;

    public KeyHolder getKey() {
        return key;
    }

    public List<AttributeListItemData> getAttributeListItemData() {
        return attributeListItemData;
    }

    public void setAttributeListItemData(List<AttributeListItemData> attributeListItemData) {
        this.attributeListItemData = attributeListItemData;
    }

    public void setKey(KeyHolder key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getPatternRep() {
        return patternRep;
    }

    public void setPatternRep(int patternRep) {
        this.patternRep = patternRep;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }
}
