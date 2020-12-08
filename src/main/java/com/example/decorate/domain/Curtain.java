package com.example.decorate.domain;

import com.example.decorate.domain.dto.ExcelData;
import com.example.decorate.domain.dto.ProductFormData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Curtain {

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

    @Column(name = "curtain_type")
    private CurtainType curtainType;

    @Column(name = "width")
    private int width;

    @Column(name = "height")
    private int height;

    @Column(name = "itemNo")
    private String itemNumber;

    @Column(name = "pattern")
    private int patternRep;

    @Column(name = "price")
    private int price;

    @Column(name = "composition")
    private String composition;

    private String productFamily;

    @Column(name = "cleaning")
    private String cleaningInst;

    private String typeOfSize;

    public Curtain(ProductFormData productFormData, KeyHolder keyHolder) {
        this.key = keyHolder;
        this.id = keyHolder.getId();
        this.name = productFormData.getName();
        for (CurtainType curtain : CurtainType.values()) {
            if (curtain.getType().equals(productFormData.getCurtainType())) {
                this.curtainType = curtain;
            }
        }
        this.productDesc = productFormData.getProductDesc();
        this.itemNumber = productFormData.getItemNumber();
        this.width = productFormData.getWidth();
        this.height = productFormData.getHeight();
        this.patternRep = productFormData.getPatternRep();
        this.price = productFormData.getPrice();
        this.composition = productFormData.getComposition();
        this.productFamily = productFormData.getProductFamily();
        this.cleaningInst = productFormData.getCleaningInst();
    }

    public Curtain(ExcelData excelData, KeyHolder keyHolder) {
        this.key = keyHolder;
        this.id = keyHolder.getId();
        this.name = excelData.getName();
        for (CurtainType curtain : CurtainType.values()) {
            if (curtain.getType().equals(excelData.getCurtainType())) {
                this.curtainType = curtain;
            }
        }
        this.productDesc = excelData.getProductDesc();
        this.itemNumber = excelData.getItemNumber();
        this.width = excelData.getWidth();
        this.height = excelData.getHeight();
        this.patternRep = excelData.getPatternRep();
        this.price = excelData.getPrice();
        this.composition = excelData.getComposition();
        this.productFamily = excelData.getProductFamily();
        this.cleaningInst = excelData.getCleaningInst();
    }

}
