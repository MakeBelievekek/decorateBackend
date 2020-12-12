package com.example.decorate.domain;

import com.example.decorate.domain.dto.ExcelData;
import com.example.decorate.domain.dto.ProductCreationFormData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

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

    @Column(name = "description",columnDefinition = "text")
    private String productDesc;

   /* @Column(name = "curtain_type")
    @Enumerated(EnumType.STRING)
    private CurtainType curtainType;
*/
    @Column(name = "width")
    private int width;

    @Column(name = "height")
    private int height;

    @Column(name = "itemNo")
    private String itemNumber;

    @Column(name = "pattern")
    private double patternRep;

    @Column(name = "price")
    private int price;

    @Column(name = "composition")
    private String composition;

    private String productFamily;

    @Column(name = "cleaning")
    private String cleaningInst;

    private String typeOfSize;

    @Column(name = "time_stamp")
    private Instant timeStamp;

    public Curtain(ProductCreationFormData productCreationFormData, KeyHolder keyHolder) {
        this.key = keyHolder;
        this.id = keyHolder.getId();
        this.name = productCreationFormData.getName();
     /*   for (CurtainType curtain : CurtainType.values()) {
            if (curtain.getType().equals(productCreationFormData.getCurtainType())) {
                this.curtainType = curtain;
            }
        }
        this.curtainType = CurtainType.valueOf(productCreationFormData.getCurtainType());*/
        this.productDesc = productCreationFormData.getProductDesc();
        this.itemNumber = productCreationFormData.getItemNumber();
        this.width = productCreationFormData.getWidth();
        this.height = productCreationFormData.getHeight();
        this.patternRep = productCreationFormData.getPatternRep();
        this.price = productCreationFormData.getPrice();
        this.composition = productCreationFormData.getComposition();
        this.productFamily = productCreationFormData.getProductFamily();
        this.cleaningInst = productCreationFormData.getCleaningInst();
        this.timeStamp = Instant.now();
    }


    public Curtain(ExcelData excelData, KeyHolder keyHolder) {
        this.key = keyHolder;
        this.id = keyHolder.getId();
        this.name = excelData.getName();
       /* for (CurtainType curtain : CurtainType.values()) {
            if (curtain.getType().equals(excelData.getCurtainType())) {
                this.curtainType = curtain;
            }
        }*/
        this.productDesc = excelData.getProductDesc();
        this.itemNumber = excelData.getItemNumber();
        this.width = excelData.getWidth();
        this.height = excelData.getHeight();
        this.patternRep = excelData.getPatternRep();
        this.price = excelData.getPrice();
        this.composition = excelData.getComposition();
        this.productFamily = excelData.getProductFamily();
        this.cleaningInst = excelData.getCleaningInst();
        this.typeOfSize = excelData.getTypeOfSize();
        this.timeStamp = Instant.now();
    }

}
