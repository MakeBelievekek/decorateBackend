package com.example.decorate.domain;

import com.example.decorate.domain.dto.WallpaperFormData;

import javax.persistence.*;

@Entity
public class Wallpaper {

    @Id
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "key_id")
    private KeyHolder key;

    @Column(name = "name")
    private String name;

    @Column(name = " description")
    private String productDesc;

    @Column(name = "itemNo")
    private int itemNumber;

    @Column(name = "width")
    private int width;

    @Column(name = "pattern")
    private int patternRep;

    @Column(name = "price")
    private int price;

    @Column(name = "composition")
    private String composition;

    public Wallpaper() {
    }

    public Wallpaper(WallpaperFormData wallpaperFormData, KeyHolder keyHolder) {
        this.key = keyHolder;
        this.id = keyHolder.getId();
        this.name = wallpaperFormData.getName();
        this.productDesc = wallpaperFormData.getProductDesc();
        this.itemNumber = wallpaperFormData.getItemNumber();
        this.width = wallpaperFormData.getWidth();
        this.patternRep = wallpaperFormData.getPatternRep();
        this.price = wallpaperFormData.getPrice();
        this.composition = wallpaperFormData.getComposition();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public KeyHolder getKey() {
        return key;
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
