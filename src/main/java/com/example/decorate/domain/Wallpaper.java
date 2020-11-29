package com.example.decorate.domain;

import com.example.decorate.domain.dto.ProductFormData;
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

    public Wallpaper(ProductFormData productFormData, KeyHolder keyHolder) {
        this.key = keyHolder;
        this.id = keyHolder.getId();
        this.name = productFormData.getName();
        this.productDesc = productFormData.getProductDesc();
        this.itemNumber = productFormData.getItemNumber();
        this.width = productFormData.getWidth();
        this.height = productFormData.getHeight();
        this.patternRep = productFormData.getPatternRep();
        this.price = productFormData.getPrice();
        this.composition = productFormData.getComposition();
        this.productFamily = productFormData.getProductFamily();
        this.annotation = productFormData.getAnnotation();
        this.recommendedGlue = productFormData.getRecommendedGlue();
    }
}
