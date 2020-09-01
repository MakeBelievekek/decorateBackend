package com.example.decorate.domain;

import com.example.decorate.domain.dto.ProductFormData;

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
    private String itemNumber;

    @Column(name = "width")
    private int width;

    @Column(name = "pattern")
    private int patternRep;

    @Column(name = "price")
    private int price;

    @Column(name = "composition")
    private String composition;

    private String productFamily;

    private String annotation;

    private String recommendedGlue;

    public Wallpaper() {
    }

    public Wallpaper(ProductFormData productFormData, KeyHolder keyHolder) {
        this.key = keyHolder;
        this.id = keyHolder.getId();
        this.name = productFormData.getName();
        this.productDesc = productFormData.getProductDesc();
        this.itemNumber = productFormData.getItemNumber();
        this.width = productFormData.getWidth();
        this.patternRep = productFormData.getPatternRep();
        this.price = productFormData.getPrice();
        this.composition = productFormData.getComposition();
        this.productFamily = productFormData.getProductFamily();
        this.annotation = productFormData.getAnnotation();
        this.recommendedGlue = productFormData.getRecommendedGlue();
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

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
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

    public String getProductFamily() {
        return productFamily;
    }

    public void setProductFamily(String productFamily) {
        this.productFamily = productFamily;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getRecommendedGlue() {
        return recommendedGlue;
    }

    public void setRecommendedGlue(String recommendedGlue) {
        this.recommendedGlue = recommendedGlue;
    }
}
