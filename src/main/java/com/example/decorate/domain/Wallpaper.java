package com.example.decorate.domain;

import com.example.decorate.domain.dto.ExcelData;
import com.example.decorate.domain.dto.ProductCreationFormData;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder
public class Wallpaper {

    @Id
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "key_id")
    private KeyHolder key;

    @Column(name = "name",columnDefinition = "text")
    private String name;

    @Column(name = " description",columnDefinition = "text")
    private String productDesc;

    @Column(name = "itemNo",columnDefinition = "text")
    private String itemNumber;

    @Column(name = "width")
    private int width;

    private int height;

    @Column(name = "pattern")
    private double patternRep;

    @Column(name = "price")
    private int price;

    @Column(name = "composition",columnDefinition = "text")
    private String composition;
    @Column(columnDefinition = "text")
    private String productFamily;
    @Column(columnDefinition = "text")
    private String annotation;
    @Column(columnDefinition = "text")
    private String recommendedGlue;

    @Column(name = "modified")
    private Instant modified;

    @Column(name = "created")
    private Instant created = Instant.now();


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
        this.created = Instant.now();
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
        this.created = Instant.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallpaper wallpaper = (Wallpaper) o;
        return key.equals(wallpaper.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
