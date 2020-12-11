package com.example.decorate.domain;

import com.example.decorate.domain.dto.ExcelData;
import com.example.decorate.domain.dto.ProductCreationFormData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
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

    private int height;

    @Column(name = "pattern")
    private int patternRep;

    @Column(name = "price")
    private int price;

    @Column(name = "composition")
    private String composition;

    private String productFamily;

    private String annotation;

    private String recommendedGlue;

    @Column(name = "date")
    private java.sql.Date date;

    public Wallpaper(ProductCreationFormData productCreationFormData, KeyHolder keyHolder) {
        this.key = keyHolder;
        this.id = keyHolder.getId();
        this.name = productCreationFormData.getName();
        this.productDesc = productCreationFormData.getProductDesc();
        this.itemNumber = productCreationFormData.getItemNumber();
        this.width = productCreationFormData.getWidth();
        this.height = productCreationFormData.getHeight();
        this.patternRep = productCreationFormData.getPatternRep();
        this.price = productCreationFormData.getPrice();
        this.composition = productCreationFormData.getComposition();
        this.productFamily = productCreationFormData.getProductFamily();
        this.annotation = productCreationFormData.getAnnotation();
        this.recommendedGlue = productCreationFormData.getRecommendedGlue();
    }

    public Wallpaper(ExcelData excelData, KeyHolder keyHolder) {
        this.key = keyHolder;
        this.id = keyHolder.getId();
        this.name = excelData.getName();
        this.productDesc = excelData.getProductDesc();
        this.itemNumber = excelData.getItemNumber();
        this.width = excelData.getWidth();
        this.height = excelData.getHeight();
        this.patternRep = excelData.getPatternRep();
        this.price = excelData.getPrice();
        this.composition = excelData.getComposition();
        this.productFamily = excelData.getProductFamily();
        this.annotation = excelData.getAnnotation();
        this.recommendedGlue = excelData.getRecommendedGlue();
    }
}
