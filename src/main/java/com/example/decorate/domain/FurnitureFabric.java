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
public class FurnitureFabric {

    @Id
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "key_id")
    private KeyHolder key;

    @Column(name = "name",columnDefinition = "text")
    private String name;

    @Column(name = "description",columnDefinition = "text")
    private String productDesc;

    @Column(name = "width")
    private int width;

    @Column(name = "height")
    private int height;

    @Column(name = "itemNo",columnDefinition = "text")
    private String itemNumber;

    @Column(name = "pattern")
    private double patternRep;

    @Column(name = "price")
    private int price;

    @Column(name = "composition",columnDefinition = "text")
    private String composition;
    @Column(columnDefinition = "text")
    private String productFamily;

    @Column(name = "cleaning",columnDefinition = "text")
    private String cleaningInst;

    private int abrasionResistance;
    @Column(columnDefinition = "text")
    private String typeOfSize;

    @Column(name = "modified")
    private Instant modified;

    @Column(name = "created")
    private Instant created = Instant.now();

    public FurnitureFabric(ExcelData excelData, KeyHolder keyHolder) {
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
        this.cleaningInst = excelData.getCleaningInst();
        this.typeOfSize = excelData.getTypeOfSize();
        this.abrasionResistance = excelData.getAbrasionResistance();
        this.created = Instant.now();
    }

    public FurnitureFabric(ProductCreationFormData productCreationFormData, KeyHolder keyHolder) {
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
        this.cleaningInst = productCreationFormData.getCleaningInst();
        this.typeOfSize = productCreationFormData.getTypeOfSize();
        this.abrasionResistance = productCreationFormData.getAbrasionResistance();
        this.created = Instant.now();
    }
}
